package com.vascomm.demo;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


@Configuration
@EnableWebSecurity
public class CustomConfig {
    @Value("${key}")
    String key;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authz ->
//                        authz.requestMatchers(HttpMethod.POST,"/access/generate").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/user").hasAuthority("user")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/user")
//                        .hasAuthority("admin")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/user")
//                        .hasAuthority("admin")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/user")
//                        .hasAuthority("admin")
//                        .anyRequest()
//                        .authenticated())
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

                http.authorizeHttpRequests(authz ->
                        authz.anyRequest().permitAll());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

//    @Bean
//    public JwtDecoder jwtDecoder() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeySpecException {
//        byte[] publicKeyBytes = Base64.getDecoder().decode(key);
//
//        // Convert to RSAPublicKey object
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
//
//        return NimbusJwtDecoder.withPublicKey(publicKey).build();
//    }


}
