package com.opzero.user.service;

import org.springframework.stereotype.Service;

import com.opzero.user.dto.request.LoginRequest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

    // private final UserRepository userRepository;
    // @Autowired
    // private PasswordEncoder passwordEncoder;
    public boolean login(LoginRequest request) {
        log.info("Got Request from user {} password {}", request.getUserName(), request.getPassword());
        // if (request.getPassword() == null || request.getUserName() == null) {
        // log.error("mobile number or password is null");
        // throw new BadRequestException("mobile number or password is null");
        // }

        // User user = userRepository.findByMobile(request.getUserName())
        // .orElseThrow(() -> new BadRequestException("User not found"));
        // if
        // (!passwordEncoder.encode(request.getPassword()).equals(user.getPassword())) {
        // throw new BadRequestException("wrong password");
        // }
        return true;
    }

    // public boolean logout(String token) {
    // log.info("Logout request");
    // return true;
    // }
}
