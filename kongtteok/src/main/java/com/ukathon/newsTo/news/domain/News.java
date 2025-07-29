package com.ukathon.newsTo.news.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long newsId;

    private String title;

    @Column(length = 1000)
    private String link;

    @Column(length = 1000)
    private String description;

    private LocalDateTime pubDate;

    @Column(length = 1000)
    private String image;

    @Column(nullable = false)
    private String groupName;
}
