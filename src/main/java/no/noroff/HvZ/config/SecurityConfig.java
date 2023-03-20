package no.noroff.HvZ.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
/*
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;
    @Value("${auth0.audience}")
    private String audience;


    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)JwtDecoders.fromOidcIssuerLocation(issuer);
        AudienceValidator audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> defaultWithIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        DelegatingOAuth2TokenValidator<Jwt> jwtDelegatingOAuth2TokenValidator = new DelegatingOAuth2TokenValidator<>(defaultWithIssuer, audienceValidator);
        jwtDecoder.setJwtValidator(jwtDelegatingOAuth2TokenValidator);
        return jwtDecoder;
    }
*/


    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors()
                .and()
                .sessionManagement()
                .disable()
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/v1/users/**" ).hasRole("HUMAN")
                                /*.requestMatchers("/api/v1/games/**").permitAll()
                                .requestMatchers("/api/v1/mission/**").permitAll()
                                .requestMatchers("/api/v1/players/**").permitAll()
                                .requestMatchers("/api/v1/squad/**").permitAll()
                                .requestMatchers("/api/v1/squadMember/**").permitAll()
                                .requestMatchers("/api/v1/chat/**").permitAll()

                                 */
                                .anyRequest().permitAll()
                ).oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());

        return httpSecurity.build();

    }




    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }



}

