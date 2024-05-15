package com.example.gamemanagementservice.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Setter
@Getter
public class ResponseSearch {
    @Id
    private String gameId;
    private String title;
    private String developer;
    private String publisher;
    private String genre;
    private String operatingSystem;
    private String releaseDate;
    private List<String> url;
    private String price;
}
