package com.c_s_boot.service;

import com.c_s_boot.config.JwtUtils;
import com.c_s_boot.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import com.c_s_boot.DTO.JwtResponse;
import com.c_s_boot.DTO.LoginRequest;
import com.c_s_boot.DTO.SignupRequest;
import com.c_s_boot.models.Role;
import com.c_s_boot.models.RoleType;
import com.c_s_boot.models.User;
import com.c_s_boot.repository.RoleRepository;
import com.c_s_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public void registerUser(SignupRequest signupRequest) {
        User user = UserMapper.INSTANCE.signupRequestToUser(signupRequest);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // Assign default role (USER)
        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);
    }

    public JwtResponse loginUser(LoginRequest loginRequest) {
        // Authenticate user with email and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        // Set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        // Fetch user from email and return JWT response
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new JwtResponse(jwtToken, user.getId(), user.getEmail(), userDetails.getAuthorities());
    }

    public UserDetails getCurrentUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

