package com.example.bookstore.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true) // you can use method level security
public class WebSecurityConfig {

        // @Autowired
        private UserDetailsService userDetailsService; // type of attribute -> interface

        // Constructor injection
        public WebSecurityConfig(UserDetailsService userDetailsService) {
                this.userDetailsService = userDetailsService;
        }

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize // / and /home paths are configured to not
                                                .requestMatchers("/", "/home").permitAll() // require any
                                                                                           // authentication.
                                                .anyRequest().authenticated() // All other paths must be authenticated.
                                ) // If you don't give loginPage
                                .formLogin(formlogin -> formlogin // your application will use the
                                                .loginPage("/login") // spring boot default login page.
                                                .defaultSuccessUrl("/booklist", true) // <-- Tells where to go after
                                                .permitAll() // successful login.
                                )
                                .logout(logout -> logout
                                                .permitAll());
                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        }

}
