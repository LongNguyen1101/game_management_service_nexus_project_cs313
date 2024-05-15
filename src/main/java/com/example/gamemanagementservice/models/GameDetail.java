package com.example.gamemanagementservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("game_detail")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameDetail {
    @Id
    private String gameId;
    private String title;
    private String developer;
    private String publisher;
    private String genre;
    private String operatingSystem;
    private String releaseDate;
    private String plotAndGameplay;
    private List<String> url;
    private String price;
}
