package com.myy803.social_bookstore.config;

import static com.myy803.social_bookstore.config.EndpointConfig.HOMEPAGE_PATH;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws java.io.IOException {
        if (response.isCommitted()) return;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, HOMEPAGE_PATH);
    }
}
