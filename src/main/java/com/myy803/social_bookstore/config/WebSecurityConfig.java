package com.myy803.social_bookstore.config;

import static com.myy803.social_bookstore.config.EndpointConfig.*;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final CustomSecuritySuccessHandler customSecuritySuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin
                        .loginPage(LOGIN_PATH)
                        .failureUrl(LOGIN_PATH + "?error=true")
                        .successHandler(customSecuritySuccessHandler)
                        .usernameParameter("username")
                        .passwordParameter("password"))
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_PATH))
                        .logoutSuccessUrl("/"))
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/", LOGIN_PATH, LOGOUT_PATH, REGISTER_PATH)
                                .permitAll()
                                .requestMatchers(
                                        HOMEPAGE_PATH,
                                        USER_PROFILE_PATH,
                                        BOOK_OFFERS_SAVE_PATH,
                                        BOOK_OFFERS_VIEW_PATH,
                                        BOOK_OFFERS_VIEW_REQUESTS_PATH,
                                        ACCEPT_REQUESTS_PATH,
                                        BOOK_OFFERS_DELETE_PATH)
                                .authenticated())
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }
}
