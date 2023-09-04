package com.redmath.bankingapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration implements WebMvcConfigurer {

    @Value("${spring.web.security.ignored:/error,/ui/**,/favicon.ico,/swagger-ui/**,/v3/api-docs,/v3/api-docs/**}")
    private String[] ignored = { "/error", "/ui/**", "/favicon.ico", "/swagger-ui/**", "/v3/api-docs",
            "/v3/api-docs/**" };

    @Value("${spring.web.security.ignored.get:/api/v1/news}")
    private String[] ignoredGet = { "/api/v1/Users" };

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            for (String ignore : ignored) {
                web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher(ignore));
            }
            for (String ignore : ignoredGet) {
                web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, ignore));
            }
            web.ignoring().requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "GET"))
                    .requestMatchers(new AntPathRequestMatcher("/api/v1/Users/{Id}/all", "GET"))
                    .requestMatchers(new AntPathRequestMatcher("/api/v1/Transaction/**", "POST"));

        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(config -> config.successHandler((request, response, auth) -> {
        }));

        http.logout(config -> config.logoutSuccessHandler((request, response, auth) -> {
        }));

        CookieCsrfTokenRepository csrfRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        csrfRepository.setCookiePath("/");
        http.csrf(config -> config.csrfTokenRepository(csrfRepository)
                .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()));

        http.authorizeHttpRequests(config -> config
                .requestMatchers(AntPathRequestMatcher.antMatcher("/actuator/**")).hasAnyAuthority("ACTUATOR")
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/Users/{id}/all")).hasAuthority("USER")
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/Users")).hasAuthority("ADMIN")
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/Users/**")).hasAuthority("ADMIN")
                .anyRequest().authenticated());

        return http.build();
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("api/v1/**")
//                .allowedOrigins("http://localhost:8080") // Replace with your frontend's origin
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("Authorization", "Content-Type")
//                .allowCredentials(true); // Allow cookies
//    }

//    public WebSecurityCustomizer webSecurityCustomizer() throws Exception
//    {
//        return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "GET"));
//
//    }

//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        //http.csrf(config -> config.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
//
//        http.authorizeHttpRequests(config -> config
//                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "DELETE"))
//                .hasAuthority("ADMIN")
//                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "POST"))
//                .hasAuthority("ADMIN")
//                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users", "PUT"))
//                .hasAuthority("ADMIN")
//                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users/{id}", "GET"))
//                .hasAuthority("ADMIN")
//                .requestMatchers(new AntPathRequestMatcher("/api/v1/Users/{Id}/all", "GET"))
//                .hasAuthority("USER")
//                .requestMatchers(new AntPathRequestMatcher("/api/v1/Transaction/{user_id}", "POST"))
//                .hasAuthority("USER")
//                .anyRequest().authenticated());
//
//        http.formLogin(Customizer.withDefaults());
//        return http.build();
//    }
}

