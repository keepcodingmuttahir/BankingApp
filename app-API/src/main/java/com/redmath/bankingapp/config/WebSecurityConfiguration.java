package com.redmath.bankingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfiguration {

    public WebSecurityCustomizer webSecurityCustomizer() throws Exception
    {
        return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "GET"));

    }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.csrf(config -> config.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        http.authorizeHttpRequests(config -> config
                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "DELETE"))
                .hasAuthority("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "POST"))
                .hasAuthority("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "PUT"))
                .hasAuthority("ADMIN")
//                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "GET"))
//                .hasAuthority("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users/{id}", "GET"))
                .hasAuthority("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users/{Id}/all", "GET"))
                .hasAuthority("USER")
                .requestMatchers(new AntPathRequestMatcher("/api/v1/Transaction/{user_id}", "POST"))
                .hasAuthority("USER")
                .anyRequest().authenticated());

        http.formLogin(Customizer.withDefaults());
        return http.build();
    }
}

