package com.ukathon.newsTo.test.service;

import com.ukathon.newsTo.test.domain.TestResult;
import com.ukathon.newsTo.test.dto.response.TestResultResponse;
import com.ukathon.newsTo.test.repository.TestResultRepository;
import com.ukathon.newsTo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestResultService {

    private final TestResultRepository testResultRepository;
    private final UserRepository userRepository;

    public TestResultResponse evaluateTest(Long userId, List<Boolean> answers) {
        int score = (int) answers.stream().filter(Boolean::booleanValue).count();

        String resultType;
        if (score <= 2) resultType = "보수";
        else if (score <= 4) resultType = "중도";
        else resultType = "진보";

        // DB에는 resultType만 저장
        TestResult result = TestResult.builder()
                .user(userRepository.findById(userId).orElseThrow())
                .resultType(resultType)
                .build();
        testResultRepository.save(result);

        // score는 응답에만 포함
        return new TestResultResponse(resultType, score);
    }
}
