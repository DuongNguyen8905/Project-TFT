package com.example.MetaTFT.controller;

import com.example.MetaTFT.entity.Champion;
import com.example.MetaTFT.service.ChampionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/champions")

public class ChampionController {
    private final ChampionService championService;
    public  ChampionController (ChampionService theChampionService){
        championService = theChampionService;
    }

    @GetMapping()
    public List<Champion> getList(){
        return championService.findAll();
    }

    @PostMapping("/save")
    public Champion saveChampion(@RequestBody Champion theChampion){
            return championService.save(theChampion);
        }

    @GetMapping("/{championId}")
    public ResponseEntity<?> getChampion(@PathVariable int championId) {
        Optional<Champion> theChampion = Optional.ofNullable(championService.findById(championId));

        if (theChampion.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Champion ID not found: " + championId);
        }
        return ResponseEntity.ok(theChampion.get());
    }

    @DeleteMapping("/{championId}")
    public ResponseEntity<String> deleteChampion(@PathVariable int championId) {
        Champion tempChampion = championService.findById(championId);

        championService.deleteById(championId);
        return ResponseEntity.ok("Deleted Champion ID - " + championId);
    }
}
