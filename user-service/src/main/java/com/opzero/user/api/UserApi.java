package com.opzero.user.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opzero.core.dto.CommonResponse;
import com.opzero.user.dto.request.SignupRequest;
import com.opzero.user.dto.request.UserAddRequest;
import com.opzero.user.dto.request.UserUpdateRequest;
import com.opzero.user.dto.response.UserDetailResponse;
import com.opzero.user.dto.response.UserSummaryResponse;
import com.opzero.user.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<Boolean>> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(new CommonResponse<>(userService.signup(request), "User signed up successfully", 200));
    }

    @PostMapping("/add")
    public ResponseEntity<CommonResponse<Boolean>> addUser(@RequestBody UserAddRequest request) {
        return ResponseEntity.ok(new CommonResponse<>(userService.addUser(request), "User added successfully", 200));
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<List<UserSummaryResponse>>> getAllUser() {
        return ResponseEntity.ok(new CommonResponse<>(userService.getAllUser(), "User listed successfully", 200));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CommonResponse<Boolean>> updateUser(@PathVariable(name = "userId") String id,
            @RequestBody UserUpdateRequest request) {
        return ResponseEntity
                .ok(new CommonResponse<>(userService.update(id, request), "Profile updated successfully", 200));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponse<UserDetailResponse>> getUser(@PathVariable(name = "userId") String id) {
        return ResponseEntity
                .ok(new CommonResponse<>(userService.getUserInfo(id), "Profile fetched successfully", 200));
    }
}
