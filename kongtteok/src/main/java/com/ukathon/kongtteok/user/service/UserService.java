package com.ukathon.kongtteok.user.service;

import com.ukathon.kongtteok.global.exception.ClientExceptionCode;
import com.ukathon.kongtteok.global.exception.ExceptionCode;
import com.ukathon.kongtteok.global.exception.KongtteokException;
import com.ukathon.kongtteok.user.domain.User;
import com.ukathon.kongtteok.user.dto.request.CreateUserRequest;
import com.ukathon.kongtteok.user.dto.request.LoginRequest;
import com.ukathon.kongtteok.user.dto.response.CreateUserResponse;
import com.ukathon.kongtteok.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByHandle(request.getHandle())) {
            throw new KongtteokException(ExceptionCode.HANDLE_ALREADY_EXISTS);
        }
        User user = request.toEntity();
        return CreateUserResponse.from(userRepository.save(user));
    }

    // 로그인
    public User login(LoginRequest request) {

        User user = userRepository.findByHandle(request.getHandle())
                .orElseThrow(() -> new KongtteokException(ExceptionCode.USER_NOT_FOUND));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new KongtteokException(ExceptionCode.PASSWORD_MISMATCH);
        }

        return user;
    }
}
