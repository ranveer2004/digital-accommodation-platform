package com.stayen.casa.userservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.userservice.constant.Endpoints;
import com.stayen.casa.userservice.constant.HeaderConstant;
import com.stayen.casa.userservice.dto.ErrorResponseDTO;
import com.stayen.casa.userservice.enums.CommonError;
import com.stayen.casa.userservice.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserHeaderFilter extends OncePerRequestFilter {

    private static final Map<String, String> PATH_METHOD_ALLOWED_WITHOUT_USER_HEADER;
    static {
        PATH_METHOD_ALLOWED_WITHOUT_USER_HEADER = new HashMap<>();
        PATH_METHOD_ALLOWED_WITHOUT_USER_HEADER.put((Endpoints.USER_BASE_URL + Endpoints.PROFILE), "POST"); // creating new profile

        // /api/v1/users/profile/{uid}/exists
        PATH_METHOD_ALLOWED_WITHOUT_USER_HEADER.put((
                Endpoints.USER_BASE_URL
                    + Endpoints.PROFILE
                    + Endpoints.PROFILE_EXISTS
        ), "GET");

//        // /api/v1/users/profile/{uid}/property/{propertyId}/exists
//        PATH_METHOD_ALLOWED_WITHOUT_USER_HEADER.put((
//                Endpoints.USER_BASE_URL
//                    + Endpoints.PROFILE
//                    + Endpoints.PROPERTY_EXISTS
//        ), "GET");

        // /api/v1/users/profile/add-property-id
        PATH_METHOD_ALLOWED_WITHOUT_USER_HEADER.put((
                Endpoints.USER_BASE_URL
                        + Endpoints.PROFILE
                        + Endpoints.ADD_NEW_PROPERTY_ID
        ), "PUT");

        // /api/v1/users/profile/delete-property-id
        PATH_METHOD_ALLOWED_WITHOUT_USER_HEADER.put((
                Endpoints.USER_BASE_URL
                        + Endpoints.PROFILE
                        + Endpoints.DELETE_PROPERTY_ID
        ), "PUT");
    }

    private ObjectMapper mapper;
    private AntPathMatcher antPathMatcher;

    @Autowired
    public UserHeaderFilter(ObjectMapper mapper, AntPathMatcher antPathMatcher) {
        this.mapper = mapper;
        this.antPathMatcher = antPathMatcher;
    }

    /**
     * Setting the response header, status, and message to return
     * only when request header does not have user specific details
     */
    private void setErrorResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        try(PrintWriter writer = response.getWriter()) {
            writer.write(mapper.writeValueAsString(new ErrorResponseDTO(CommonError.USER_DETAIL_NOT_FOUND)));
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        String method = request.getMethod();
        System.out.println("Path and Method intercepted :: ");
        System.out.println(path);
        System.out.println(method);

        return PATH_METHOD_ALLOWED_WITHOUT_USER_HEADER
                .entrySet()
                .stream()
                .anyMatch((entry) -> {
                    String allowedPath = entry.getKey();
                    String allowedMethod = entry.getValue();

                    if(antPathMatcher.match(allowedPath, path)) {
                        return method.equalsIgnoreCase(allowedMethod);
                    }
                    return false;
                });
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uid = request.getHeader(HeaderConstant.USER_ID);
        String email = request.getHeader(HeaderConstant.USER_EMAIL);
        String deviceId = request.getHeader(HeaderConstant.USER_DEVICE_ID);

        if(uid != null && email != null && deviceId != null && !uid.isBlank() && !email.isBlank() && !deviceId.isBlank()) {
            User user = new User(uid, email, deviceId);

            UsernamePasswordAuthenticationToken verifiedUser = new UsernamePasswordAuthenticationToken(uid, null, null);
            verifiedUser.setDetails(user);

            SecurityContextHolder.getContext().setAuthentication(verifiedUser);

            filterChain.doFilter(request, response);
        } else {
            setErrorResponse(response);
        }
    }
}
