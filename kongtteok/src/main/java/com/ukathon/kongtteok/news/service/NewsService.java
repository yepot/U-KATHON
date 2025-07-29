package com.ukathon.kongtteok.news.service;

import com.ukathon.kongtteok.news.domain.News;
import com.ukathon.kongtteok.news.dto.response.NewsResponse;
import com.ukathon.kongtteok.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<NewsResponse> getNewsByGroup(String groupName) {
        List<News> newsList = newsRepository.findTop10ByGroupNameOrderByPubDateDesc(groupName);
        return toResponseList(newsList);
    }

    public List<NewsResponse> getRandomNewsByGroup(String groupName) {
        List<News> newsList = newsRepository.findByGroupName(groupName);
        Collections.shuffle(newsList);
        return newsList.stream()
                .limit(10)
                .map(NewsResponse::from)
                .collect(Collectors.toList());
    }

    private List<NewsResponse> toResponseList(List<News> list) {
        return list.stream()
                .map(NewsResponse::from)
                .collect(Collectors.toList());
    }
}
