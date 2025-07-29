package com.ukathon.newsTo.user.dto.request;

import com.ukathon.newsTo.global.validator.ValidPassword;
import com.ukathon.newsTo.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    @NotBlank
    @Size(min = 4, max = 12)
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 영문 소문자와 숫자만 가능합니다.")
    private String handle;

    @NotBlank
    @Size(min = 6, max = 20)
    @ValidPassword
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .handle(handle)
                .password(password)
                .build();
    }
}
