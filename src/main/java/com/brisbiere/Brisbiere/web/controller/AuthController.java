package com.brisbiere.Brisbiere.web.controller;


import com.brisbiere.Brisbiere.domain.User;
import com.brisbiere.Brisbiere.domain.UserRole;
import com.brisbiere.Brisbiere.domain.service.UserRoleService;
import com.brisbiere.Brisbiere.domain.service.UserService;
import com.brisbiere.Brisbiere.domain.service.dto.LoginDto;
import com.brisbiere.Brisbiere.web.config.JwtUtil;
import com.brisbiere.Brisbiere.web.config.SecurityConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final SecurityConfig securityConfig;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, SecurityConfig securityConfig, UserService userService, UserRoleService userRoleService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.securityConfig = securityConfig;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody @NotNull LoginDto loginDto){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        String jwt = this.jwtUtil.create(loginDto.getUsername());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION).body(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> register(@RequestBody User user){
        System.out.println(user);
        UserRole userRole = UserRole.builder().role("CUSTOMER").username(user.getEmail()).grantedDate(LocalDateTime.now()).build();
        user.setPassword(this.securityConfig.passwordEncoder().encode(user.getPassword()));
        this.userService.save(user);
        this.userRoleService.saveUserRole(userRole);
        return ResponseEntity.ok().build();
    }

}

