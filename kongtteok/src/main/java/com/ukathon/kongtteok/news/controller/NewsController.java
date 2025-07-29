package com.ukathon.kongtteok.news.controller;

import com.ukathon.kongtteok.news.domain.News;
import com.ukathon.kongtteok.news.dto.response.NewsResponse;
import com.ukathon.kongtteok.news.repository.NewsRepository;
import com.ukathon.kongtteok.news.service.NewsCrawlerService;
import com.ukathon.kongtteok.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsRepository newsRepository;
    private final NewsService newsService;

    // 최신순 뉴스 조회
    @GetMapping
    public List<NewsResponse> getLatestNewsByGroup(@RequestParam("group") String groupName) {
        return newsService.getNewsByGroup(groupName);
    }

    // 무작위 뉴스 조회
    @GetMapping("/random")
    public List<NewsResponse> getRandomNewsByGroup(@RequestParam("group") String groupName) {
        return newsService.getRandomNewsByGroup(groupName);
    }
}
