package no.noroff.HvZ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

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
}
