package com.security.eventify.security;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService , PasswordEncoder passwordEncoder){
        this.customUserDetailsService  = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String username = authentication.getName();
        String rawPassword = (String) authentication.getCredentials();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if(!passwordEncoder.matches(rawPassword , userDetails.getPassword())){
            throw new BadCredentialsException("mot de pass invalide ");
        }

        // if password matches, create an authenticated token including authorities

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails , null,userDetails.getAuthorities());

        // mark as authenticated (constructor sets authenticated = true)
        return authToken;
    }

    // TELLS SPRING WHICH AUTHENTICATION TYPE THIS PROVIDER SUPPORTS
    @Override
    public boolean supports(Class<?> authentication){
        return  UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
