package com.example.MetaTFT.controller;

import com.example.MetaTFT.controller.dto.CreateChampionRequest;
import com.example.MetaTFT.controller.dto.FindChampionTierRequest;
import com.example.MetaTFT.entity.Champion;
import com.example.MetaTFT.entity.Stat;
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
            Stat championStat = champion.getStat();

            if( championName==null || championName.isEmpty() ){
                throw new IllegalArgumentException("name is not null");
            }
             if(championTier == null){
                 throw new IllegalArgumentException("Tier is not null");
             }
            if(championStat == null){
                throw new IllegalArgumentException("Stat is not null");
            }

            Champion newChampion = new Champion();
            newChampion.setName(championName);
            newChampion.setTier(championTier);
            newChampion.setAvatarUrl(championAvatarUrl);
            newChampion.setStat(championStat);

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
    public ResponseEntity<?> patchChampion(@PathVariable int championId,
                                           @RequestBody Map<String, Object> patchPayLoad) {
        Champion champion = championService.findById(championId);
        if (champion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Champion not found");
        }

        // Xử lý các field ngoài stat nếu có
        if (patchPayLoad.containsKey("name")) {
            champion.setName((String) patchPayLoad.get("name"));
        }

        if (patchPayLoad.containsKey("avatarUrl")) {
            champion.setAvatarUrl((String) patchPayLoad.get("avatarUrl"));
        }

        if (patchPayLoad.containsKey("tier")) {
            String tierValue = (String) patchPayLoad.get("tier");
            Tier tier = Tier.valueOf(tierValue);
            champion.setTier(tier);
        }

        // Xử lý stat
        if (patchPayLoad.containsKey("stat")) {
            Map<String, Object> statMap = (Map<String, Object>) patchPayLoad.get("stat");
            Stat stat = champion.getStat();
            if (stat == null) {
                stat = new Stat();
                champion.setStat(stat);
            }
            // ===== Cập nhật thông số trong Stat nếu có trong payload =====
            if (statMap.containsKey("health")) {
                stat.setHealth((Integer) statMap.get("health"));
            }

            if (statMap.containsKey("mana")) {
                stat.setMana((Integer) statMap.get("mana"));
            }

            if (statMap.containsKey("attackDamage")) {
                stat.setAttackDamage((Integer) statMap.get("attackDamage"));
            }

            if (statMap.containsKey("abilityPower")) {
                stat.setAbilityPower((Integer) statMap.get("abilityPower"));
            }

            if (statMap.containsKey("armor")) {
                stat.setArmor((Integer) statMap.get("armor"));
            }

            if (statMap.containsKey("magicResist")) {
                stat.setMagicResist((Integer) statMap.get("magicResist"));
            }

            if (statMap.containsKey("attackSpeed")) {
                stat.setAttackSpeed((Integer) statMap.get("attackSpeed"));
            }

            if (statMap.containsKey("critChance")) {
                stat.setCritChance((Integer) statMap.get("critChance"));
            }

            if (statMap.containsKey("critDamage")) {
                stat.setCritDamage((Integer) statMap.get("critDamage"));
            }

            if (statMap.containsKey("rangeStat")) {
                stat.setrangeStat((Integer) statMap.get("rangeStat"));
            }

        }

        // Lưu lại Champion
        Champion updated = championService.save(champion);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/byTier")
    public ResponseEntity<?>getChampionByTiers(@RequestBody FindChampionTierRequest request){
        List<Champion>champions = championService.findByTier(request.getTiers());
        return ResponseEntity.ok(champions);
    }


    // @ExceptionHandler(IllegalArgumentException.class)
    // public ResponseEntity<String> handleCustomException(IllegalArgumentException ex) {
    // return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    //}
}
