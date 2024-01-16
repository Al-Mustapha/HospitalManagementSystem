package com.example.HMS.Authentication;

import com.example.HMS.Users.Patient.CustomServiceClass;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtOncePerRequestClass extends OncePerRequestFilter {

    @Autowired
    public CustomServiceClass customServiceClass;

    @Autowired
    public JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token;
        String user;

        try {
            String header = request.getHeader("authorization");
            if (header == null || !header.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }
            token = header.substring(7);
            user = jwtUtils.extractUsername(token);

            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails applicationUser = customServiceClass.loadUserByUsername(user);

                if(jwtUtils.isTokenValid(token, applicationUser)) {
                    UsernamePasswordAuthenticationToken us =
                            new UsernamePasswordAuthenticationToken(applicationUser, null,
                                    applicationUser.getAuthorities());
                    us.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(us);
                }
            }

        } catch (UsernameNotFoundException e) {
            throw new RuntimeException(e);
        }
     filterChain.doFilter(request, response);
    }
}
