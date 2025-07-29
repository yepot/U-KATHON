package com.ukathon.kongtteok.news.service;

import com.ukathon.kongtteok.news.domain.News;
import com.ukathon.kongtteok.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsCrawlerService {

    private final NewsRepository newsRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String CLIENT_ID = "EfbvjVJFDS9CTKC927v1";
    private static final String CLIENT_SECRET = "3SR0AXuwKM";

    private static final Map<String, String> KEYWORD_GROUPS = Map.ofEntries(
            // 대통령실
            Map.entry("이재명", "president"),
            Map.entry("강유정", "president"),
            Map.entry("대통령 지지율", "president"),

            // 국회
            Map.entry("법안", "congress"),
            Map.entry("국정감사", "congress"),
            Map.entry("처리 지연", "congress"),

            // 정당
            Map.entry("국민의 힘", "party"),
            Map.entry("더불어민주당", "party"),
            Map.entry("당대표", "party"),

            // 선거
            Map.entry("총선", "election"),
            Map.entry("지방선거", "election"),
            Map.entry("투표율", "election"),

            // 정부부처
            Map.entry("기획재정부", "ministry"),
            Map.entry("고용노동부", "ministry"),
            Map.entry("보건복지부", "ministry"),

            // 안보/국방
            Map.entry("병사 월급", "defense"),
            Map.entry("북한", "defense"),
            Map.entry("주한미군", "defense"),

            // 외교
            Map.entry("정상회담", "diplomacy"),
            Map.entry("트럼프", "diplomacy"),
            Map.entry("관세", "diplomacy"),

            // 사회정책
            Map.entry("연금개혁", "policy"),
            Map.entry("고령화", "policy"),
            Map.entry("집값", "policy"),

            // 정치일반
            Map.entry("법인세율", "politics"),
            Map.entry("공기업 이전", "politics"),
            Map.entry("자녀 특혜", "politics")
    );


    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void fetchNewsByKeywords() {
        for (Map.Entry<String, String> entry : KEYWORD_GROUPS.entrySet()) {
            String keyword = entry.getKey();
            String groupName = entry.getValue();
            fetchAndSaveNews(keyword, groupName);
        }
    }

    public void fetchAndSaveNews(String keyword, String groupName) {
        try {
            String apiUrl = "https://openapi.naver.com/v1/search/news.json?query=" + keyword +
                    "&display=50&start=1&sort=date";

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", CLIENT_ID);
            headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request, String.class);

            JSONObject json = new JSONObject(response.getBody());
            JSONArray items = json.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);

                String title = item.getString("title").replaceAll("<.*?>", "");
                String description = item.getString("description").replaceAll("<.*?>", "");
                String link = item.getString("link");
                String originallink = item.optString("originallink", link);
                String pubDate = item.getString("pubDate");

                if (newsRepository.existsByTitleAndPubDate(title, pubDate)) {
                    continue;
                }

                String image = fetchImageFrom(originallink);

                News news = News.builder()
                        .title(title)
                        .description(description)
                        .link(link)
                        .pubDate(pubDate)
                        .image(image)
                        .groupName(groupName)
                        .build();

                newsRepository.save(news);
                System.out.println("저장됨 [" + groupName + "] : " + title);
            }

        } catch (Exception e) {
            System.out.println("크롤링 실패 [" + keyword + "] : " + e.getMessage());
        }
    }

    private String fetchImageFrom(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(3000)
                    .get();

            Element ogImage = doc.selectFirst("meta[property=og:image]");
            if (ogImage != null) {
                return ogImage.attr("content");
            }
        } catch (Exception e) {
            System.out.println("이미지 파싱 실패: " + url);
        }
        return "https://yourdomain.com/default-thumbnail.png";
    }
}