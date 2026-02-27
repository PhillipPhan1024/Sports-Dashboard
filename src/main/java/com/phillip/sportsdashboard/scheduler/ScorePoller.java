package com.phillip.sportsdashboard.scheduler;

import com.phillip.sportsdashboard.model.Game;
import com.phillip.sportsdashboard.service.NbaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScorePoller {

    private final NbaService nbaService;

    @CacheEvict(value = "games", key = "#date")
    public void evictCache(String date) {
        log.info("Evicting cache for date: {}", date);
    }

    @Scheduled(fixedRate = 30000) // runs every 30 seconds
    public void refreshTodaysGames() {
        String today = LocalDate.now().toString(); // e.g. "2026-02-27"
        log.info("Polling BallDontLie for today's games: {}", today);

        try {
            evictCache(today);                          // clear stale cache
            List<Game> games = nbaService.getGamesByDate(today); // fetch fresh + re-cache
            log.info("Refreshed {} games for {}", games.size(), today);
        } catch (Exception e) {
            log.error("Failed to refresh games: {}", e.getMessage());
        }
    }
}