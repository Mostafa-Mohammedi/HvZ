package no.noroff.HvZ.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

/*

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                .and()
                .sessionManagement()
                .disable()
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated()
                ).oauth2ResourceServer().jwt();

        return httpSecurity.build();
    }






    @Bean
    public UserDetailsService userDetailsService(){
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().sessionManagement().disable()
                .securityMatcher("/api/v1/users/**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("ADMIN"));
                //.httpBasic(withDefaults()).oauth2ResourceServer().jwt();
        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain generalApiFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("USER"))
                .formLogin(withDefaults());
        return httpSecurity.build();
    }


 */


}
