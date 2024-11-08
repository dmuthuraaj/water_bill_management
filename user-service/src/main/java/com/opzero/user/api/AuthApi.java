package com.opzero.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opzero.core.dto.CommonResponse;
import com.opzero.user.dto.request.LoginRequest;
import com.opzero.user.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<Boolean>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new CommonResponse<>(authService.login(loginRequest)));
    }

    // @DeleteMapping(value = "/logout", produces =
    // MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<CommonResponse<Boolean>> logout(
    // @RequestHeader(value = "Authorization") String authorizationHeader) {
    // return ResponseEntity.ok(new
    // CommonResponse<>(authService.logout(authorizationHeader)));
    // }
}
