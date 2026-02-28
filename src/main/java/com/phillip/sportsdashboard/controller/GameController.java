package com.phillip.sportsdashboard.controller;

import com.phillip.sportsdashboard.model.Game;
import com.phillip.sportsdashboard.service.NbaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {
    "http://localhost:3000",
    "https://pps-sports-dashboard.vercel.app/"
})
@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final NbaService nbaService;

    @GetMapping
    public List<Game> getGames(@RequestParam String date) {
        return nbaService.getGamesByDate(date);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}