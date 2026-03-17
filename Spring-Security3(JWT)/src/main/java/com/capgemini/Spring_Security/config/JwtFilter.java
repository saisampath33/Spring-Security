package com.capgemini.Spring_Security.config;

import com.capgemini.Spring_Security.Service.JWTTokenService;
import com.capgemini.Spring_Security.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTokenService service;
    @Autowired
    private MyUserDetailsService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String name = null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            name = service.extractUserName(token);
        }
        if(name!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            //authentication is not happened here
            UserDetails userDetails = userService.loadUserByUsername(name); //coming from DB
            Boolean status = service.validateToken(token,userDetails);
            if (status) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                System.out.println("auth token: " + authToken);

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
