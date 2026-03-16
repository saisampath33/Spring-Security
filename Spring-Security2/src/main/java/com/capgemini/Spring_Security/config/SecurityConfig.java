package com.capgemini.Spring_Security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFiltersChain(HttpSecurity http) throws Exception{
        http.csrf(customizer->customizer.disable());
//        http.formLogin(Customizer.withDefaults());
        //Any one can acess the ad-user endpoint with any request get ,post,etc
        http.authorizeHttpRequests(authorizeHttp->
                authorizeHttp.requestMatchers("/add-user").permitAll().anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetails(){
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("Sampath")
//                .password("sampath@123")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("roa")
//                .password("roa@123")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }

    @Bean
    public AuthenticationProvider authProvider(){
        //In spring security 6 DaoAuthenticationProvider expects a UserDetailsService in the constructor.
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider(userDetailsService);
//        daoProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //plain text as password
        daoProvider.setPasswordEncoder(new BCryptPasswordEncoder(12)); //In authentication also we need to use Bcrypt Encoder

        return daoProvider;
    }
}
