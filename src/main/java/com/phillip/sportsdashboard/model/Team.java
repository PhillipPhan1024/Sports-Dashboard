package com.phillip.sportsdashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team implements Serializable {

    private Long id;
    private String name;
    private String city;
    private String abbreviation;
    private String conference;
    private String division;
}