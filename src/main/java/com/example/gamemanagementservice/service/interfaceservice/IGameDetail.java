package com.example.gamemanagementservice.service.interfaceservice;

import com.example.gamemanagementservice.models.GameDetail;
import com.example.gamemanagementservice.payload.response.ResponseRecommend;
import com.example.gamemanagementservice.payload.response.ResponseSearch;
import com.example.gamemanagementservice.payload.response.ResponseWrapper;
import com.example.gamemanagementservice.payload.resquest.RequestSearch;

import java.util.List;


public interface IGameDetail{
    ResponseWrapper<List<GameDetail>> getAllGame();
    ResponseWrapper<GameDetail> getGameById(String id);
    ResponseWrapper<List<GameDetail>> get15NewestGames();

    ResponseWrapper<List<GameDetail>> getGameFeatured();

    ResponseWrapper<List<String>> getGenres();

    ResponseWrapper<List<String>> getDeveloper();

    ResponseWrapper<List<String>> getPublisher();

    ResponseWrapper<List<String>> getOperatingSystem();

    ResponseWrapper<List<ResponseSearch>> getGameBySearch(RequestSearch requestSearch);

    ResponseWrapper<List<ResponseRecommend>> getRecommendGameByGenre(List<String> genres);
}
