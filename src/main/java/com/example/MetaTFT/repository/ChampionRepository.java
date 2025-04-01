package com.example.MetaTFT.repository;

import com.example.MetaTFT.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChampionRepository extends JpaRepository<Champion,Integer> {
    List<Champion> findByNameContainingIgnoreCase(String name);

}
