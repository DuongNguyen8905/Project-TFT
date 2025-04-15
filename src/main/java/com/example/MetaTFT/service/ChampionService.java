package com.example.MetaTFT.service;

import com.example.MetaTFT.entity.Champion;
import com.example.MetaTFT.entity.Tier;

import java.util.List;

public interface ChampionService {

    List<Champion>findAll();

    Champion findById(int theId);

    Champion save(Champion champion);

    void deleteById(int theId);

    List<Champion> findChampionsByName(String name);

    List<Champion>findByTier(List<Tier>tiers);


}
