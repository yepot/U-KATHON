package com.ukathon.kongtteok.news.repository;

import com.ukathon.kongtteok.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByTitleAndPubDate(String title, String pubDate);

    List<News> findTop10ByGroupNameOrderByPubDateDesc(String groupName);

    List<News> findByGroupName(String groupName);
}
