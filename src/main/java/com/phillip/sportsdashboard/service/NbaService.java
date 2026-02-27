package com.phillip.sportsdashboard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phillip.sportsdashboard.model.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaService {

    @Value("${balldontlie.api.key}")
    private String apiKey;

    @Value("${balldontlie.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Cacheable(value = "games", key = "#date")
    public List<Game> getGamesByDate(String date) {
        log.info("Fetching games from BallDontLie API for date: {}", date);

        String url = baseUrl + "/games?dates[]=" + date;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
            url, HttpMethod.GET, entity, String.class
        );

        List<Game> games = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode data = root.get("data");

            if (data != null && data.isArray()) {
                for (JsonNode node : data) {
                    Game game = objectMapper.treeToValue(node, Game.class);
                    games.add(game);
                }
            }
        } catch (Exception e) {
            log.error("Error parsing games response", e);
        }

        return games;
    }
}