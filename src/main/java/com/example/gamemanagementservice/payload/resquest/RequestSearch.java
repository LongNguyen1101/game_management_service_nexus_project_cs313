package com.example.gamemanagementservice.payload.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class RequestSearch {
    private String title;
    private List<String> genres;
    private List<String> developers;
    private List<String> publishers;
    private List<String> operatingSystems;
    private String minYear;
    private String maxYear;
    private String minPrice;
    private String maxPrice;
}
