package com.example.gamemanagementservice.repository;

import com.example.gamemanagementservice.models.GameDetail;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IGameRepository extends MongoRepository<GameDetail, String> {
    @Aggregation(pipeline = {
            "{ $project: { gameId: 1, title: 1, operatingSystem: 1, releaseDate: 1, " +
                    "price:  1, url: { $arrayElemAt: [ '$url', 0 ] } } }",
            "{ $sort: { releaseDate: -1 } }"
    })
    List<GameDetail> findAllGames();

    @Aggregation(pipeline = {
            "{ $project: { gameId: 1, title: 1, operatingSystem: 1, releaseDate: 1, " +
                    "price:  1, url: { $arrayElemAt: [ '$url', 0 ] } } }",
            "{ $sort: { releaseDate: -1 } }",
            "{ $limit: 15 }"
    })
    List<GameDetail> findTop15ByOrderByReleaseDateDesc();

    @Aggregation(pipeline = {
            "{ $unwind: '$genre' }",
            "{ $group: { _id: '$genre' } }",
            "{ $project: { _id: 0, genre: '$_id' } }"
    })
    List<String> findAllGenres();

    @Aggregation(pipeline = {
            "{ $unwind: '$developer' }",
            "{ $group: { _id: '$developer' } }",
            "{ $project: { _id: 0, developer: '$_id' } }"
    })
    List<String> findAllDeveloper();

    @Aggregation(pipeline = {
            "{ $unwind: '$publisher' }",
            "{ $group: { _id: '$publisher' } }",
            "{ $project: { _id: 0, publisher: '$_id' } }"
    })
    List<String> findAllPublisher();

    @Aggregation(pipeline = {
            "{ $unwind: '$operatingSystem' }",
            "{ $group: { _id: '$operatingSystem' } }",
            "{ $project: { _id: 0, operatingSystem: '$_id' } }"
    })
    List<String> findAllOperatingSystem();

    @Aggregation(pipeline = {
            "{ $sample: { size: 5 } }",
            "{ $project: { gameId: 1, title: 1, genre: 1, operatingSystem: 1, plotAndGameplay: 1," +
                    "url: 1, price: 1 } }"
    })
    List<GameDetail> findRandom5Games();
}
