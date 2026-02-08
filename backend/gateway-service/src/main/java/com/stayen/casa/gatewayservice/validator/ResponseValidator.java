package com.stayen.casa.gatewayservice.validator;

import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.constant.HeaderConstant;
import com.stayen.casa.gatewayservice.dto.AuthTokenResponseDTO;
import org.springframework.http.*;

import java.time.Duration;

public class ResponseValidator {

    /**
     * <pre>
     *     default cookie setting
     *     {
     *         cookieName: "refresh-token",
     *         httpOnly: true,
     *         secure: true,
     *         sameSite: Strict,
     *         path: "/api/v1/auth/token/refresh",
     *     }
     * </pre>
     *
     * @return HttpHeader header
     */
    public static HttpHeaders getHeaderWithJwtRefreshTokenCookies(String appDomain, ResponseEntity<AuthTokenResponseDTO> response) {
        String tokenCookiePath = (Endpoints.Token.BASE_URL + Endpoints.Token.REFRESH_TOKEN);

        System.out.println("TokenCookiePath : " + tokenCookiePath);

        ResponseCookie cookie = ResponseCookie
                .from(HeaderConstant.REFRESH_TOKEN_COOKIE, response.getBody().getRefreshToken())
                .httpOnly(true)
                .maxAge(Duration.ofDays(HeaderConstant.REFRESH_TOKEN_COOKIE_AGE_IN_DAYS))
                // TODO: uncomment .secure() after enabling SSL on domain
                // TODO: dev-mode { Lax - secure(false) }  ||  prod-mode { None - secure(true) }
                /**
                 * SameSite ==>
                 * {
                 *  None = Cross Site (all methods)
                 *  Lax = Cross Site (but Only GET request)
                 *  Strict = Same Site Only (A -> A)
                 * }
                 */
                .secure(false)
                .sameSite("Lax")  // None, Lax, Strict
                .domain(appDomain)  // stayen.casa
                .path(tokenCookiePath) // scope/path of cookie
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.SET_COOKIE, cookie.toString());
        return headers;
    }

}
