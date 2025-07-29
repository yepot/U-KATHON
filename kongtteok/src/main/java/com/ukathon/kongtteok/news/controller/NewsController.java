package com.ukathon.kongtteok.news.controller;

import com.ukathon.kongtteok.news.dto.response.NewsResponse;
import com.ukathon.kongtteok.news.repository.NewsRepository;
import com.ukathon.kongtteok.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class NewsController {

    private final NewsRepository newsRepository;
    private final NewsService newsService;

    // 키워드별 최근 뉴스 조회
    @GetMapping("/alarms")
    public List<NewsResponse> getLatestNewsByGroup(@RequestParam("group") String groupName) {
        return newsService.getNewsByGroup(groupName);
    }

    // 전체 뉴스 최신순으로 조회
    @GetMapping("/news/latest")
    public List<NewsResponse> getLatestAllNews() {
        return newsService.getLatestNewsAll();
    }

    // 전체 뉴스 무작위로 조회
    @GetMapping("/news/random")
    public List<NewsResponse> getRandomAllNews() {
        return newsService.getRandomNewsAll();
    }
}
