//package com.kubapiecuch.springbootwithdatabase.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Value("${auth0.audience}")
//    private String audience;
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String issuer;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET,"/v1/users").permitAll()
//                .anyRequest().authenticated()
//                .and().cors()
//                .and().oauth2ResourceServer().jwt();
//        return http.build();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder(){
//        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
//                JwtDecoders.fromOidcIssuerLocation(issuer);
//
//        OAuth2TokenValidator<Jwt> AudienceValidator = new AudienceValidator(audience);
//        OAuth2TokenValidator<Jwt> withIssuer = new JwtIssuerValidator(issuer);
//        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<Jwt>(withIssuer, AudienceValidator);
//
//        jwtDecoder.setJwtValidator(withAudience);
//
//        return jwtDecoder;
//    }
//}
