package com.opzero.user.config;

import java.util.Arrays;
import java.util.Collections;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                                // .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .requestMatchers("/**").permitAll().anyRequest().authenticated())
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .permitAll())
                        .csrf(AbstractHttpConfigurer::disable)
                                .logout(LogoutConfigurer::permitAll);
                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                UserDetails user = User.builder()
                                .username("user")
                                .password(passwordEncoder().encode("user123"))
                                .roles("USER")
                                .build();
                UserDetails admin = User.builder()
                                .username("admin")
                                .password(passwordEncoder().encode("admin123"))
                                .roles("ADMIN", "USER")
                                .build();
                return new InMemoryUserDetailsManager(user, admin);
        }

//        @Bean
//        public CorsConfigurationSource corsConfigurationSource() {
//                final CorsConfiguration configuration = new CorsConfiguration();
//                configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
//                configuration.setAllowedMethods(
//                                Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTION"));
//                configuration.setAllowCredentials(true);
//                // configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//                configuration.setAllowedHeaders(Collections.singletonList("*"));
//                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//                source.registerCorsConfiguration("/**", configuration);
//                return source;
//        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
