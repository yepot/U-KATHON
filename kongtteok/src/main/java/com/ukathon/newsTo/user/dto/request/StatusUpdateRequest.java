package com.ukathon.newsTo.user.dto.request;

import com.ukathon.newsTo.user.domain.UserStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatusUpdateRequest {

    @NotNull
    private UserStatus status;
}