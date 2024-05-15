package com.example.gamemanagementservice.service;

import com.example.gamemanagementservice.models.GameDetail;
import com.example.gamemanagementservice.payload.response.ResponseSearch;
import com.example.gamemanagementservice.payload.response.ResponseWrapper;
import com.example.gamemanagementservice.payload.resquest.RequestSearch;
import com.example.gamemanagementservice.repository.DynamicAggregation;
import com.example.gamemanagementservice.repository.IGameRepository;
import com.example.gamemanagementservice.service.interfaceservice.IGameDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameDetailServiceImpl implements IGameDetail {
    private final IGameRepository gameRepository;
    private final MongoOperations mongoOperations;

    private List<String> findUnique(List<String> list){
        HashSet<String> unique = new HashSet<>();

        for (String component: list) {
            String[] arr = component.split(", ");
            for (String str: arr) {
                unique.add(str.trim());
            }
        }

        return new ArrayList<>(unique);
    }
    @Override
    public ResponseWrapper<List<GameDetail>> getAllGame() {
        try {
            List<GameDetail> gameDetailList = gameRepository.findAll();

            return gameDetailList.isEmpty() ?
                    ResponseWrapper.fromError("Not found any games", 404) :
                    ResponseWrapper.fromData(gameDetailList, 200);

        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }

    @Override
    public ResponseWrapper<GameDetail> getGameById(String id) {
        try {
            Optional<GameDetail> gameDetailOptional = gameRepository.findById(id);

            return gameDetailOptional.map(gameDetail -> ResponseWrapper.fromData(gameDetail, 200))
                    .orElseGet(() -> ResponseWrapper.fromError("Game not found", 404));

        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }

    @Override
    public ResponseWrapper<List<GameDetail>> get15NewestGames() {
        try {
            List<GameDetail> gameDetailList = gameRepository.findTop15ByOrderByReleaseDateDesc();

            return gameDetailList.isEmpty() ?
                    ResponseWrapper.fromError("Not found any games", 404) :
                    ResponseWrapper.fromData(gameDetailList, 200);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }

    @Override
    public ResponseWrapper<List<GameDetail>> getGameFeatured() {
        try {
            List<GameDetail> gameDetailList = gameRepository.findRandom5Games();

            return gameDetailList.isEmpty() ?
                    ResponseWrapper.fromError("Not found any games", 404) :
                    ResponseWrapper.fromData(gameDetailList, 200);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }

    @Override
    public ResponseWrapper<List<String>> getGenres() {
        try {
            List<String> genresList = gameRepository.findAllGenres();

            if (genresList.isEmpty()) {
                return ResponseWrapper.fromError("Not found any genres", 404);
            }

            List<String> uniqueGenres = findUnique(genresList);
            Collections.sort(uniqueGenres);

            return ResponseWrapper.fromData(uniqueGenres, 200);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }

    @Override
    public ResponseWrapper<List<String>> getDeveloper() {
        try {
            List<String> developerList = gameRepository.findAllDeveloper();

            if (developerList.isEmpty()) {
                return ResponseWrapper.fromError("Not found any developers", 404);
            }

            Collections.sort(developerList);

            return ResponseWrapper.fromData(developerList, 200);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }

    @Override
    public ResponseWrapper<List<String>> getPublisher() {
        try {
            List<String> publisherList = gameRepository.findAllPublisher();

            if (publisherList.isEmpty()) {
                return ResponseWrapper.fromError("Not found any publishers", 404);
            }

            Collections.sort(publisherList);

            return ResponseWrapper.fromData(publisherList, 200);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }

    @Override
    public ResponseWrapper<List<String>> getOperatingSystem() {
        try {
            List<String> operatingSystemList = gameRepository.findAllOperatingSystem();

            if (operatingSystemList.isEmpty()) {
                return ResponseWrapper.fromError("Not found any operating systems", 404);
            }

            List<String> uniqueOperatingSystem = findUnique(operatingSystemList);
            Collections.sort(uniqueOperatingSystem);

            return ResponseWrapper.fromData(uniqueOperatingSystem, 200);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }

    @Override
    public ResponseWrapper<List<ResponseSearch>> getGameBySearch(RequestSearch requestSearch) {
        try {
            Aggregation aggregation = DynamicAggregation.buildDynamicAggregation(
                    requestSearch.getTitle(),
                    requestSearch.getGenres(),
                    requestSearch.getDevelopers(),
                    requestSearch.getPublishers(),
                    requestSearch.getOperatingSystems(),
                    requestSearch.getMinYear(),
                    requestSearch.getMaxYear(),
                    requestSearch.getMinPrice(),
                    requestSearch.getMaxPrice());

            List<ResponseSearch> searchList = mongoOperations.aggregate(aggregation,
                    "game_detail", ResponseSearch.class).getMappedResults();

            if (searchList.isEmpty()) {
                return ResponseWrapper.fromError("Not found any games", 404);
            }

            return ResponseWrapper.fromData(searchList, 200);
        } catch (Exception e) {
            return new ResponseWrapper<>(null, 500, e.getMessage());
        }
    }
}
