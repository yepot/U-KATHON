package com.ukathon.newsTo.user.repository;

import com.ukathon.newsTo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByHandle(String handle);
    Optional<User> findByHandle(String handle);
}
