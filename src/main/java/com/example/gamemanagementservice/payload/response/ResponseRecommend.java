package com.example.gamemanagementservice.payload.response;

import com.example.gamemanagementservice.models.GameDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ResponseRecommend {
    private String genre;
    private List<GameDetail> gameDetailList;
}
