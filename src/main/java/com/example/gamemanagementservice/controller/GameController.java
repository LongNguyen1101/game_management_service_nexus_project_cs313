package com.example.gamemanagementservice.controller;

import com.example.gamemanagementservice.payload.resquest.RequestSearch;
import com.example.gamemanagementservice.service.GameDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {
    private final GameDetailServiceImpl gameDetailService;

    @GetMapping("/get-all-games")
    public ResponseEntity<?> getAllGameApi() {
        var responseWrapper = gameDetailService.getAllGame();

        if (responseWrapper.getHttpCode() == 500) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable("id") String id) {
        var responseWrapper = gameDetailService.getGameById(id);

        if (responseWrapper.getHttpCode() != 200) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }

    @GetMapping("/new")
    public ResponseEntity<?> get15NewestGame() {
        var responseWrapper = gameDetailService.get15NewestGames();

        if (responseWrapper.getHttpCode() != 200) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }

    @GetMapping("/featured")
    public ResponseEntity<?> getGameFeatured() {
        var responseWrapper = gameDetailService.getGameFeatured();

        if (responseWrapper.getHttpCode() != 200) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllGenres() {
        var responseWrapper = gameDetailService.getGenres();

        if (responseWrapper.getHttpCode() != 200) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }

    @GetMapping("/developer")
    public ResponseEntity<?> getAllDevelopers() {
        var responseWrapper = gameDetailService.getDeveloper();

        if (responseWrapper.getHttpCode() != 200) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }

    @GetMapping("/publisher")
    public ResponseEntity<?> getAllPublishers() {
        var responseWrapper = gameDetailService.getPublisher();

        if (responseWrapper.getHttpCode() != 200) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }

    @GetMapping("/operating-systems")
    public ResponseEntity<?> getAllOperatingSystems() {
        var responseWrapper = gameDetailService.getOperatingSystem();

        if (responseWrapper.getHttpCode() != 200) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getGameBySearch(@RequestBody RequestSearch requestSearch) {
        var responseWrapper = gameDetailService.getGameBySearch(requestSearch);

        if (responseWrapper.getHttpCode() != 200) {
            return ResponseEntity.status(responseWrapper.getHttpCode())
                    .body(responseWrapper.getErrMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseWrapper.getData());
    }
}
