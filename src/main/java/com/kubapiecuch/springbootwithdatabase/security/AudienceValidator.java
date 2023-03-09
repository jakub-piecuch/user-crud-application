//package com.kubapiecuch.springbootwithdatabase.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.core.OAuth2Error;
//import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import java.util.List;
//
//
//public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
//
//    private final String audience;
//
//    public AudienceValidator(String audience) {
//        this.audience = audience;
//    }
//
//    @Override
//    public OAuth2TokenValidatorResult validate(Jwt token) {
//        OAuth2Error error = new OAuth2Error("invalid_token", "The required audience is missing", null);
//        if (token.getAudience().contains(audience)) {
//            return OAuth2TokenValidatorResult.success();
//        }
//        return OAuth2TokenValidatorResult.failure(error);
//    }
//}
