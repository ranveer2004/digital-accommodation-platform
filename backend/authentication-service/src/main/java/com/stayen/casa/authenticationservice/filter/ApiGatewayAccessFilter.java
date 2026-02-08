package com.stayen.casa.authenticationservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.authenticationservice.constant.EnvConstant;
import com.stayen.casa.authenticationservice.constant.HeaderConstant;
import com.stayen.casa.authenticationservice.dto.ErrorResponseDTO;
import com.stayen.casa.authenticationservice.enums.CommonError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiGatewayAccessFilter extends OncePerRequestFilter {

    private final String internalServiceAuthKey;

    private final ObjectMapper mapper;

    @Autowired
    public ApiGatewayAccessFilter(EnvConstant envConstant, ObjectMapper mapper) {
        this.internalServiceAuthKey = envConstant.getInternalServiceAuthKey();
        this.mapper = mapper;
    }

	/**
	 * Setting the response header, status, and message to return
	 * only when request origin is unauthorised
	 */
	private void setErrorResponse(HttpServletResponse response, CommonError commonError) throws IOException {
		response.setContentType("application/json");

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter().write(mapper.writeValueAsString(new ErrorResponseDTO(commonError)));
	}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO: uncomment below code for CORS
        String authKey = request.getHeader(HeaderConstant.AUTH_KEY);
        System.out.println("ApiGatewayAccess filter :: authKey = " + authKey);

        if(authKey != null && authKey.equals(internalServiceAuthKey)) {
            filterChain.doFilter(request, response);
        } else {
            setErrorResponse(response, CommonError.ORIGIN_NOT_ALLOWED);
        }
    }
}
