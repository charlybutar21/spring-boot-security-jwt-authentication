package com.charly.perpetuostudio.service.user;

import com.charly.perpetuostudio.dto.request.user.LoginRequest;
import com.charly.perpetuostudio.dto.response.user.LoginResponse;
import com.charly.perpetuostudio.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService{
    private static final String BEARER_TYPE = "Bearer";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public LoginResponse process(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return LoginResponse.builder()
                .token(jwt)
                .type(BEARER_TYPE)
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .name(userDetails.getName())
                .roles(roles)
                .build();
    }
}
