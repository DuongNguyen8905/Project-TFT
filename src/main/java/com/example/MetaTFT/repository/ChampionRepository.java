package com.example.MetaTFT.repository;

import com.example.MetaTFT.entity.Champion;
import com.example.MetaTFT.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionRepository extends JpaRepository<Champion,Integer> {
    List<Champion> findByNameContainingIgnoreCase(String name);

    List<Champion> findByTierIn(List<Tier> tiers);
}
