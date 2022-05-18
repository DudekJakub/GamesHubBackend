package com.gameshub.controller;

import com.gameshub.domain.game.rawgGame.RawgGameDetailedDto;
import com.gameshub.service.RawgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rawg_games")
public class RawgGameController {

    private final RawgService rawgService;

    @GetMapping("/{id}")
    public ResponseEntity<RawgGameDetailedDto> rawgGameById(@PathVariable Long id) {
        return ResponseEntity.ok(rawgService.getRawgGameDetailedById(id));
    }

    @GetMapping()
    public ResponseEntity<List<RawgGameDetailedDto>> rawgGamesRelatedToGivenName(@RequestParam String name) {
        return ResponseEntity.ok(rawgService.fetchListOfRawgGamesRelatedToGivenName(name));
    }
}
