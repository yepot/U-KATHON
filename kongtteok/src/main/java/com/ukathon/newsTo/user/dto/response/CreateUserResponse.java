package com.ukathon.newsTo.user.dto.response;

import com.ukathon.newsTo.user.domain.User;
import com.ukathon.newsTo.user.domain.UserStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserResponse {
    private Long userId;
    private String username;
    private String handle;
    private String password;
    private UserStatus status;

    public static CreateUserResponse from(User user) {
        return CreateUserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .handle(user.getHandle())
                .password(user.getPassword())
                .status(user.getStatus())
                .build();
    }

}
