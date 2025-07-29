package com.ukathon.newsTo.news.repository;

import com.ukathon.newsTo.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByTitleAndPubDate(String title, String pubDate);
    List<News> findTop10ByGroupNameOrderByPubDateDesc(String groupName);
    List<News> findByGroupName(String groupName);
    List<News> findTop10ByOrderByPubDateDesc();
    @Query(value = "SELECT * FROM news ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<News> findRandomNews();
}
