package com.example.MetaTFT.entity;

import jakarta.persistence.*;

@Entity
@Table(name="champion")
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tier")
    private String tier;

    @Column(name = "avatarUrl")
    private String avatarUrl;

    public Champion(){

    }

    public Champion(int id, String name, String tier, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.tier = tier;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String gettier() {
        return tier;
    }

    public void settier(String tier) {
        this.tier = tier;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
