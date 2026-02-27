package com.phillip.sportsdashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game implements Serializable {

    private Long id;
    private String date;
    private String status;

    @JsonProperty("home_team")
    private Team homeTeam;

    @JsonProperty("visitor_team")
    private Team visitorTeam;

    @JsonProperty("home_team_score")
    private int homeTeamScore;

    @JsonProperty("visitor_team_score")
    private int visitorTeamScore;

    private String period;
    private String time;
}