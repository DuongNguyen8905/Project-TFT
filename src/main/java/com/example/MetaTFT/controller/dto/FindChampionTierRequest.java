package com.example.MetaTFT.controller.dto;

import com.example.MetaTFT.entity.Tier;

import java.util.List;

public class FindChampionTierRequest {

    private List<Tier>tiers;

    public List<Tier> getTiers() {
        return tiers;
    }

    public void setTiers(List<Tier> tiers) {
        this.tiers = tiers;
    }
}
