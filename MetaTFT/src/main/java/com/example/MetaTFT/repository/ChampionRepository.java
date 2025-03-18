package com.example.MetaTFT.repository;

import com.example.MetaTFT.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion,Integer> {
}
