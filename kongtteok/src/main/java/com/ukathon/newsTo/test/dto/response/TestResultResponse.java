package com.ukathon.newsTo.test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TestResultResponse {

    private String resultType;
    private int score;

}
