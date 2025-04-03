package com.example.MetaTFT.controller;

import com.example.MetaTFT.controller.dto.CreateChampionRequest;
import com.example.MetaTFT.entity.Champion;
import com.example.MetaTFT.entity.Tier;
import com.example.MetaTFT.model.ErrorResponse;
import com.example.MetaTFT.service.ChampionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/champions")
public class ChampionController {
    private final ChampionService championService;

    private ObjectMapper ObjectMapper;

    @Autowired
    public ChampionController(ChampionService theChampionService, ObjectMapper theObjectMapper) {
        championService = theChampionService;
        ObjectMapper = theObjectMapper;
    }

    @GetMapping
    public List<Champion> getList() {
        return championService.findAll();
    }

    @GetMapping("/search")
    public List<Champion> searchChampions(@RequestParam String name) {
        return championService.findChampionsByName(name);
    }

    @GetMapping("/{championId}")
    public ResponseEntity<?> getChampion(@PathVariable int championId) {
        Optional<Champion> theChampion = Optional.ofNullable(championService.findById(championId));

        if (theChampion.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Champion ID not found: " + championId);
        }
        return ResponseEntity.ok(theChampion.get());
    }

    @PostMapping
    public ResponseEntity<?> saveChampion(@Valid @RequestBody CreateChampionRequest champion) {
        try {
            String championName = champion.getName();
            Tier championTier = champion.getTier();
            String championAvatarUrl = champion.getAvatarUrl();

            if( championName==null || championName.isEmpty() ){
                throw new IllegalArgumentException("name is not null");
            }
             if(championTier == null){
                 throw new IllegalArgumentException("Tier is not null");
             }

            Champion newChampion = new Champion();
            newChampion.setName(championName);
            newChampion.setTier(championTier);
            newChampion.setAvatarUrl(championAvatarUrl);

            Champion savedChampion = championService.save(newChampion);
            return ResponseEntity.ok(savedChampion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{championId}")
    public ResponseEntity<ErrorResponse> deleteChampion(@PathVariable int championId) {

        try {
            championService.deleteById(championId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }


    }

    @PutMapping
    public Champion updateChampion(@RequestBody Champion theChampion) {
        Champion dbChampion = championService.save(theChampion);
        return dbChampion;
    }

    @PatchMapping("/{championId}")
    public Champion patchChampion(@PathVariable int championId,
                                  @RequestBody Map<String, Object> patchPayLoad) {
        Champion tempChampion = championService.findById(championId);

        if (tempChampion == null) {
            throw new RuntimeException("champion is not found - " + championId);
        }

        if (patchPayLoad.containsKey("id")) {
            throw new RuntimeException("Champion id not allowed in request body - " + championId);
        }

        Champion patcheChampion = apply(patchPayLoad, tempChampion);

        Champion dbChampion = championService.save(patcheChampion);

        return dbChampion;

    }

    private Champion apply(Map<String, Object> patchPayLoad, Champion tempChampion) {

        ObjectNode championNode = ObjectMapper.convertValue(tempChampion, ObjectNode.class);

        ObjectNode patchNode = ObjectMapper.convertValue(patchPayLoad, ObjectNode.class);

        championNode.setAll(patchNode);

        return ObjectMapper.convertValue(championNode, Champion.class);


    }

    // @ExceptionHandler(IllegalArgumentException.class)
    // public ResponseEntity<String> handleCustomException(IllegalArgumentException ex) {
    // return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    //}
}
