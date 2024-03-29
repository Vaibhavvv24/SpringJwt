package com.example.jwtt.controller;

import com.example.jwtt.config.JwtService;
import com.example.jwtt.entity.Role;
import com.example.jwtt.entity.User;
import com.example.jwtt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.register(request.getFirstName(), request.getLastName(), request.getEmail(), passwordEncoder.encode(request.getPwd()));
//                .lastname(request.getLastName())
//                .email(request.getEmail())
//                .role(Role.USER)
//                .password(passwordEncoder.encode(request.getPwd()))
//                .build();
        userRepo.save(user);
        var jwt=jwtService.generateToken(user);
        return AuthenticationResponse.authenticate(jwt);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest registerRequest) throws AuthenticationException {
        System.out.println(registerRequest.getEmail());
        System.out.println(registerRequest.getPwd());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getEmail(),
                        registerRequest.getPwd()
                )
        );
        System.out.println("dmkkfkf");
        var user=userRepo.findByEmail(registerRequest.getEmail()).orElseThrow();
        System.out.println(user);
        var jwtToken=jwtService.generateToken(user);
        System.out.println(jwtToken);
//        return AuthenticationResponse.builder().token(jwtToken).build();
        return AuthenticationResponse.authenticate(jwtToken);

    }
}
