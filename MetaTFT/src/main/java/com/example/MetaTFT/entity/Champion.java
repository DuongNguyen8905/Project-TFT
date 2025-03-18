package com.example.MetaTFT.entity;

import jakarta.persistence.*;

@Entity
@Table(name="champion")
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;


    private String name;


    private String tiger;


    private String avatarUrl;

    public Champion(){

    }

    public Champion(int id, String name, String tiger, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.tiger = tiger;
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

    public String getTiger() {
        return tiger;
    }

    public void setTiger(String tiger) {
        this.tiger = tiger;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
