package com.opzero.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.opzero.core.exception.BadRequestException;
import com.opzero.core.exception.ResourceNotExistException;
import com.opzero.user.dto.request.SignupRequest;
import com.opzero.user.dto.request.UserAddRequest;
import com.opzero.user.dto.request.UserUpdateRequest;
import com.opzero.user.dto.response.UserDetailResponse;
import com.opzero.user.dto.response.UserSummaryResponse;
import com.opzero.user.mapper.UserMapper;
import com.opzero.user.mongo.entity.User;
import com.opzero.user.mongo.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public User findByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotExistException("User does not exists with mobile " + mobileNumber));
    }

    public boolean signup(SignupRequest request) {
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            log.info("Mobile Id is already exist");
            throw new BadRequestException("Email or Mobile already exists, try to login.");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUserName(request.getName());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);
        user.setDeleted(false);
        userRepository.save(user);
        return true;
    }

    public boolean addUser(UserAddRequest request) {
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            log.info("Mobile Id is already exist");
            throw new BadRequestException("Email or Mobile already exists, try to login.");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUserName(request.getName());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setActive(true);
        user.setDeleted(false);
        userRepository.save(user);
        return true;
    }

    public boolean update(String id, UserUpdateRequest request) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotExistException("user not found"));

        user.setName(request.getName());
        user.setModifiedAt(LocalDateTime.now());

        userRepository.save(user);
        return true;

    }

    public UserDetailResponse getUserInfo(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotExistException("User not found"));
        return UserMapper.toDetailResponse(user);
    }

    public List<UserSummaryResponse> getAllUser() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            log.info("User is not available");
            throw new ResourceNotExistException("User List does not exist");
        }
        return userList.stream().map(UserMapper::toSummaryResponse).collect(Collectors.toList());
    }
}
