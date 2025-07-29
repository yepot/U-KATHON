package com.ukathon.newsTo.test.controller;

import com.ukathon.newsTo.test.dto.request.TestResultRequest;
import com.ukathon.newsTo.test.dto.response.TestResultResponse;
import com.ukathon.newsTo.test.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestResultController {

    private final TestResultService testResultService;

    @PostMapping("/submit")
    public ResponseEntity<TestResultResponse> submitTest(@RequestBody TestResultRequest request) {
        TestResultResponse response = testResultService.evaluateTest(request.getUserId(), request.getAnswers());
        return ResponseEntity.ok(response);
    }
}
