package com.ukathon.newsTo.user.service;

import com.ukathon.newsTo.global.exception.ExceptionCode;
import com.ukathon.newsTo.global.exception.NewsToException;
import com.ukathon.newsTo.user.domain.User;
import com.ukathon.newsTo.user.dto.request.CreateUserRequest;
import com.ukathon.newsTo.user.dto.request.LoginRequest;
import com.ukathon.newsTo.user.dto.response.CreateUserResponse;
import com.ukathon.newsTo.user.repository.UserRepository;
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
            throw new NewsToException(ExceptionCode.HANDLE_ALREADY_EXISTS);
        }
        User user = request.toEntity();
        return CreateUserResponse.from(userRepository.save(user));
    }

    // 로그인
    public User login(LoginRequest request) {

        User user = userRepository.findByHandle(request.getHandle())
                .orElseThrow(() -> new NewsToException(ExceptionCode.USER_NOT_FOUND));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new NewsToException(ExceptionCode.PASSWORD_MISMATCH);
        }

        return user;
    }
}
