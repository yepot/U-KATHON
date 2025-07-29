package com.ukathon.newsTo.user.controller;

import com.ukathon.newsTo.user.domain.User;
import com.ukathon.newsTo.user.dto.request.CreateUserRequest;
import com.ukathon.newsTo.user.dto.request.LoginRequest;
import com.ukathon.newsTo.user.dto.response.CreateUserResponse;
import com.ukathon.newsTo.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.login(request);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

//    @PatchMapping("/{userId}/status")
//    public ResponseEntity<UserStatusResponse> updateStatus(@PathVariable Long id,
//                                                           @RequestBody StatusUpdateRequest request) {
//        return ResponseEntity.ok(userService.updateUserStatus(id, request.getStatus()));
//    }
}
