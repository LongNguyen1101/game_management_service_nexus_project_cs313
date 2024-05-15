package com.example.gamemanagementservice.repository;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;


import java.util.ArrayList;
import java.util.List;



public class DynamicAggregation {
    public static Aggregation buildDynamicAggregation(String title, List<String> genres, List<String> developers,
                                                      List<String> publishers, List<String> operatingSystems,
                                                      String minYear, String maxYear, String minPrice, String maxPrice) {
        List<MatchOperation> matchOperations = new ArrayList<>();

        if (title != null) {
            matchOperations.add(Aggregation.match(Criteria.where("title").regex(title, "i")));
        }

        if (genres != null && !genres.isEmpty()) {
            matchOperations.add(Aggregation.match(Criteria.where("genre").in(genres)));
        }

        if (developers != null && !developers.isEmpty()) {
            matchOperations.add(Aggregation.match(Criteria.where("developer").in(developers)));
        }

        if (publishers != null && !publishers.isEmpty()) {
            matchOperations.add(Aggregation.match(Criteria.where("publisher").in(publishers)));
        }

        if (operatingSystems != null && !operatingSystems.isEmpty()) {
            matchOperations.add(Aggregation.match(Criteria.where("operatingSystem").in(operatingSystems)));
        }

        if (minYear != null && maxYear != null) {
            String minYearDate = minYear + "-01-01";
            String maxYearDate = maxYear + "-12-31";
            matchOperations.add(Aggregation.match(
                    Criteria.where("releaseDate")
                            .gte(minYearDate)
                            .lte((maxYearDate))));
        }

        if (minPrice != null && maxPrice != null) {
            matchOperations.add(Aggregation.match(
                    Criteria.where("price")
                            .gte(minPrice)
                            .lte((maxPrice))));
        }

        return Aggregation.newAggregation(matchOperations);
    }
}
