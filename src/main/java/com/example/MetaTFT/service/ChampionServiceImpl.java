package com.example.MetaTFT.service;

import com.example.MetaTFT.entity.Champion;
import com.example.MetaTFT.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionServiceImpl implements ChampionService{
    private  final ChampionRepository championRepository;

    @Autowired
    public ChampionServiceImpl(ChampionRepository theChampionRepository) {
        championRepository = theChampionRepository;
    }

    @Override
    public List<Champion> findAll(){
        return championRepository.findAll();
    }

    @Override
    public Champion findById(int theId) {
        return championRepository.findById(theId).orElse(null);
    }

    @Override
    public Champion save(Champion champion) {
        if (champion.getAvatarUrl() != null && !champion.getAvatarUrl().isEmpty()) {
            if (!isValidImageUrl(champion.getAvatarUrl())) {
                throw new IllegalArgumentException("Avatar URL không hợp lệ!");
            }
        }
        return championRepository.save(champion);
    }

    private boolean isValidImageUrl(String url) {
        return url.matches("^(https?:\\/\\/.*\\.(?:png|jpg|jpeg|gif|bmp|webp))$");
    }

    @Override
    public void deleteById(int championId) {
        boolean isExist = championRepository.existsById(championId);
        if(isExist){
            championRepository.deleteById(championId);
        }else throw new IllegalArgumentException("Champion Not Found");


    }

    public List<Champion> findChampionsByName(String name) {
        return championRepository.findByNameContainingIgnoreCase(name);
    }


}
