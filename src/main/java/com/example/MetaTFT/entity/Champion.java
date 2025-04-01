package com.example.MetaTFT.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "champion")
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message ="")
    @NotEmpty(message = "")
    private String name;

    @Column(name = "tier")
    @Enumerated(EnumType.STRING) // Hibernate tự kiểm tra giá trị hợp lệ
    private Tier tier;

    @Column(name = "avatarUrl")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            message = "avatarUrl phải là một đường dẫn URL hợp lệ")
    public String avatarUrl;

    public Champion() {

    }

    public Champion(int id, String name, Tier tier, String avatarUrl) {
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
