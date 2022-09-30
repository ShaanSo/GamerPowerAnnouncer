package ru.katkova.gamerpowerannouncer.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
public class GPRequestService {
    @Autowired
    private RestTemplate restTemplate;
    @Getter
    @Value("${bot.url}")
    private final String url;

    @Autowired
    public GPRequestService(
            @Value("${bot.url}") String url, RestTemplate restTemplate)  {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @SneakyThrows
    public String handleRequest() {

        HttpEntity<String> entityRq = new HttpEntity<>("", new org.springframework.http.HttpHeaders());

        var entityRs = restTemplate.getForEntity(url, String.class);
        var headersRs = entityRs.getHeaders();
        var bodyRs = entityRs.getBody();
        log.info("[EGService] Response received. Headers: " + headersRs.toString() + "\nBody: " + bodyRs);

        return bodyRs;
    }
}
