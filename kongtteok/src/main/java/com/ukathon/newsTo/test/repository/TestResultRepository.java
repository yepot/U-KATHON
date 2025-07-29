package com.ukathon.newsTo.test.repository;

import com.ukathon.newsTo.test.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findByUserId(Long userId);
}
