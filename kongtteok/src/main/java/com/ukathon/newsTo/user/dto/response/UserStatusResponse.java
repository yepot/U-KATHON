package com.ukathon.newsTo.user.dto.response;

import com.ukathon.newsTo.user.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserStatusResponse {

    private Long id;
    private UserStatus status;
}
