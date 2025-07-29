package com.ukathon.newsTo.test.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TestResultRequest {
    private Long userId;
    private List<Boolean> answers;

}
