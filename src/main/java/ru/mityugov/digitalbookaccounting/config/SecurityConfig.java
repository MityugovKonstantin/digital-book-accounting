package ru.mityugov.digitalbookaccounting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.mityugov.digitalbookaccounting.services.EmployeeDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final EmployeeDetailService employeeDetailService;

    @Autowired
    public SecurityConfig(EmployeeDetailService employeeDetailService) {
        this.employeeDetailService = employeeDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        // Everyone has access to these addresses
                        .requestMatchers(
                                "/auth/login",
                                "/auth/registration"
                        ).permitAll()
                        // Only a persons with the ADMIN and USER roles have access to other URLs.
                        .anyRequest().authenticated()
                        //.anyRequest().hasAnyRole("USER", "ADMIN")
                )
                .formLogin((formLogin) -> formLogin
                        // Login page which will be shown
                        .loginPage("/auth/login")
                        // The URL where the spring expects data for authentication
                        .loginProcessingUrl("/process_login")
                        // The URL to which the user will be directed after successful authorization
                        .defaultSuccessUrl("/", true)
                        // The URL to which the user will be directed after unsuccessful authorization
                        .failureUrl("/auth/login?error")
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login")
                );

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(employeeDetailService)
                .passwordEncoder(getPasswordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
