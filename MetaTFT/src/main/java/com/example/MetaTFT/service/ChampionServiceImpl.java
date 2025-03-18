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
        return championRepository.save(champion);
    }

    @Override
    public void deleteById(int theId) {
        championRepository.deleteById(theId);

    }

}
