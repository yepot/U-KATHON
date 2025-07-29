package com.ukathon.newsTo.news.dto.response;

import com.ukathon.newsTo.news.domain.News;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewsResponse {
    private String title;
    private String description;
    private String link;
    private String image;
    private String pubDate;
    private String groupName;

    public static NewsResponse from(News news) {
        return NewsResponse.builder()
                .title(news.getTitle())
                .description(news.getDescription())
                .link(news.getLink())
                .image(news.getImage())
                .pubDate(news.getPubDate())
                .groupName(news.getGroupName())
                .build();
    }
}
