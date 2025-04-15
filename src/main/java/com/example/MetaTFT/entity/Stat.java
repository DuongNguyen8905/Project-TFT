package com.example.MetaTFT.entity;

import jakarta.persistence.*;

    @Entity
    @Table(name = "stat")
    public class Stat{

        @OneToOne(mappedBy = "stat")
        private Champion champion;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "health")
        private int health;

        @Column(name = "mana")
        private int mana;

        @Column(name = "attack_damage")
        private int attackDamage;

        @Column(name = "abilityPower")
        private int abilityPower;

        @Column(name = "armor")
        private int armor;

        @Column(name = "magic_resist")
        private int magicResist;

        @Column(name = "attack_speed")
        private int attackSpeed;

        @Column(name = "crit_chance")
        private int critChance;

        @Column(name = "crit_damage")
        private int critDamage;

        @Column(name = "range_stat")
        private int rangeStat;

        public Stat() {

        }

        public Stat(int health, int mana, int attackDamage, int abilityPower, int armor, int magicResist, int attackSpeed, int critChance, int critDamage, int rangeStat) {
            this.health = health;
            this.mana = mana;
            this.attackDamage = attackDamage;
            this.abilityPower = abilityPower;
            this.armor = armor;
            this.magicResist = magicResist;
            this.attackSpeed = attackSpeed;
            this.critChance = critChance;
            this.critDamage = critDamage;
            this.rangeStat = rangeStat;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public int getMana() {
            return mana;
        }

        public void setMana(int mana) {
            this.mana = mana;
        }

        public int getAttackDamage() {
            return attackDamage;
        }

        public void setAttackDamage(int attackDamage) {
            this.attackDamage = attackDamage;
        }

        public int getAbilityPower() {
            return abilityPower;
        }

        public void setAbilityPower(int abilityPower) {
            this.abilityPower = abilityPower;
        }

        public int getAttackSpeed() {
            return attackSpeed;
        }

        public void setAttackSpeed(int attackSpeed) {
            this.attackSpeed = attackSpeed;
        }

        public int getCritChance() {
            return critChance;
        }

        public void setCritChance(int critChance) {
            this.critChance = critChance;
        }

        public int getrangeStat() {
            return rangeStat;
        }

        public void setrangeStat(int rangeStat) {
            this.rangeStat = rangeStat;
        }

        public int getCritDamage() {
            return critDamage;
        }

        public void setCritDamage(int critDamage) {
            this.critDamage = critDamage;
        }

        public int getMagicResist() {
            return magicResist;
        }

        public void setMagicResist(int magicResist) {
            this.magicResist = magicResist;
        }

        public int getArmor() {
            return armor;
        }

        public void setArmor(int armor) {
            this.armor = armor;
        }

        @Override
        public String toString() {
            return "Stats{" +
                    "id=" + id +
                    ", health=" + health +
                    ", mana=" + mana +
                    ", attackDamage=" + attackDamage +
                    ", abilityPower=" + abilityPower +
                    ", armor=" + armor +
                    ", magicResist=" + magicResist +
                    ", attackSpeed=" + attackSpeed +
                    ", critChance=" + critChance +
                    ", critDamage=" + critDamage +
                    ", range=" + rangeStat +
                    '}';
        }
    }
