package org.astemir.magiccraft.player;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {

    public static final Map<Integer,PlayerStats> statsTable = new HashMap<>();

    private int manaRegeneration = 1;
    private int manaRegenerationMaxCooldown = 40;

    static{
        statsTable.put(0,new PlayerStats(1,40));
        statsTable.put(10,new PlayerStats(1,35));
        statsTable.put(25,new PlayerStats(1,30));
        statsTable.put(40,new PlayerStats(1,25));
        statsTable.put(65,new PlayerStats(1,20));
        statsTable.put(100,new PlayerStats(2,40));
        statsTable.put(135,new PlayerStats(2,35));
        statsTable.put(160,new PlayerStats(2,30));
        statsTable.put(195,new PlayerStats(2,25));
        statsTable.put(210,new PlayerStats(2,20));
        statsTable.put(245,new PlayerStats(3,40));
        statsTable.put(280,new PlayerStats(3,35));
        statsTable.put(325,new PlayerStats(3,30));
        statsTable.put(350,new PlayerStats(3,25));
        statsTable.put(375,new PlayerStats(3,20));
        statsTable.put(400,new PlayerStats(4,40));
        statsTable.put(435,new PlayerStats(4,35));
        statsTable.put(480,new PlayerStats(4,30));
        statsTable.put(525,new PlayerStats(4,25));
        statsTable.put(550,new PlayerStats(4,20));
        statsTable.put(600,new PlayerStats(5,40));
        statsTable.put(645,new PlayerStats(5,35));
        statsTable.put(680,new PlayerStats(5,30));
        statsTable.put(725,new PlayerStats(5,25));
        statsTable.put(750,new PlayerStats(5,20));
        statsTable.put(825,new PlayerStats(6,40));
        statsTable.put(880,new PlayerStats(6,35));
        statsTable.put(955,new PlayerStats(6,30));
        statsTable.put(1000,new PlayerStats(6,25));
        statsTable.put(1050,new PlayerStats(6,20));
        statsTable.put(1150,new PlayerStats(7,40));
        statsTable.put(1225,new PlayerStats(7,35));
        statsTable.put(1300,new PlayerStats(7,30));
        statsTable.put(1450,new PlayerStats(7,25));
        statsTable.put(1525,new PlayerStats(7,20));
        statsTable.put(1600,new PlayerStats(8,40));
        statsTable.put(1750,new PlayerStats(8,35));
        statsTable.put(1895,new PlayerStats(8,30));
        statsTable.put(2050,new PlayerStats(8,25));
        statsTable.put(2225,new PlayerStats(8,20));
        statsTable.put(2350,new PlayerStats(9,40));
        statsTable.put(2500,new PlayerStats(9,35));
        statsTable.put(2750,new PlayerStats(9,30));
        statsTable.put(3000,new PlayerStats(9,25));
        statsTable.put(3250,new PlayerStats(9,20));
        statsTable.put(3500,new PlayerStats(10,40));
        statsTable.put(4000,new PlayerStats(10,35));
        statsTable.put(4500,new PlayerStats(10,30));
        statsTable.put(5000,new PlayerStats(10,25));
        statsTable.put(6500,new PlayerStats(10,20));
        statsTable.put(8000,new PlayerStats(10,15));
        statsTable.put(10550,new PlayerStats(10,10));
        statsTable.put(15000,new PlayerStats(10,5));
        statsTable.put(20000,new PlayerStats(10,0));
    }

    private static void stats(int level,int manaRegen,int manaRegenMaxCooldown){
        statsTable.put(level,new PlayerStats(manaRegen,manaRegenMaxCooldown));
    }

    public PlayerStats(int manaRegeneration, int manaRegenerationMaxCooldown) {
        this.manaRegeneration = manaRegeneration;
        this.manaRegenerationMaxCooldown = manaRegenerationMaxCooldown;
    }

    public static PlayerStats getStatsForLevel(int level){
        PlayerStats res = statsTable.get(0);
        int resLvl = 0;
        for (int statLvl : statsTable.keySet()) {
            if (resLvl < statLvl) {
                if (level >= statLvl) {
                    res = statsTable.get(statLvl);
                    resLvl = statLvl;
                }
            }
        }
        return res;
    }


    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public int getManaRegenerationMaxCooldown() {
        return manaRegenerationMaxCooldown;
    }
}
