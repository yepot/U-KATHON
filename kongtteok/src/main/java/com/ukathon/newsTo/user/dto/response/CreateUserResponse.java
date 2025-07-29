package com.ukathon.newsTo.user.dto.response;

import com.ukathon.newsTo.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserResponse {
    private Long userId;
    private String username;
    private String handle;
    private String password;

    public static CreateUserResponse from(User user) {
        return CreateUserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .handle(user.getHandle())
                .password(user.getPassword())
                .build();
    }

}
