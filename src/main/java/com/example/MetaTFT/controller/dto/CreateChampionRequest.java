package com.example.MetaTFT.controller.dto;

import com.example.MetaTFT.entity.Stat;
import com.example.MetaTFT.entity.Tier;

public class CreateChampionRequest {

    private String name;

    private Tier tier;

    private String avatarUrl;

    private Stat stat;

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
