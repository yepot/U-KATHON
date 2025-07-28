package com.ukathon.kongtteok.user.repository;

import com.ukathon.kongtteok.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 아이디 중복 검사
    boolean existsByHandle(String handle);
}
