package com.example.HMS.Security;

import com.example.HMS.Authentication.JwtOncePerRequestClass;
import com.example.HMS.Users.Patient.CustomServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class ServiceConfig {

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomServiceClass customServiceClass;

    @Autowired
    public JwtOncePerRequestClass jwtOncePerRequestClass;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authenticationProvider(myDaoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/patient/createProfile").permitAll()
//                .requestMatchers("/patient/viewProfile/**").hasRole(PATIENT.name())
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtOncePerRequestClass, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails =
//                User.builder()
//                        .username("musa")
//                        .password(passwordEncoder.encode("123456"))
//                        .roles("ADMIN")
//                        .build();
//        return new InMemoryUserDetailsManager(
//                userDetails
//        );
//    }

    @Bean
    public DaoAuthenticationProvider myDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider
                = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customServiceClass);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomServiceClass customServiceClass){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customServiceClass);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

}
