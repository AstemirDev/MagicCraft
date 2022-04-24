package org.astemir.magiccraft.player;

import org.astemir.magiccraft.database.Database;
import org.astemir.magiccraft.database.DatabaseField;
import org.astemir.magiccraft.database.DatabaseFieldType;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.Pair;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDataHandler {

    public ConcurrentHashMap<UUID,PlayerData> playerDatas = new ConcurrentHashMap<>();

    private Database playersDB;

    public PlayerDataHandler() {
        playersDB = new Database("players");
        playersDB.connect();
        playersDB.create(true,
                DatabaseField.init(DatabaseFieldType.INTEGER,"id").primaryKey().autoIncrement().notNull(),
                DatabaseField.init(DatabaseFieldType.VARCHAR,"name").length(32),
                DatabaseField.init(DatabaseFieldType.VARCHAR,"selected_spell").length(32),
                DatabaseField.init(DatabaseFieldType.INTEGER,"mana"),
                DatabaseField.init(DatabaseFieldType.INTEGER,"mana_regeneration"),
                DatabaseField.init(DatabaseFieldType.INTEGER,"mana_regeneration_max_cooldown"),
                DatabaseField.init(DatabaseFieldType.INTEGER,"magic_level"),
                DatabaseField.init(DatabaseFieldType.DOUBLE,"magic_experience"),
                DatabaseField.init(DatabaseFieldType.INTEGER,"magic_points"),
                DatabaseField.init(DatabaseFieldType.INTEGER,"is_mage"),
                DatabaseField.init(DatabaseFieldType.VARCHAR,"learned_spells")
        );
    }

    public void loadPlayerData(Player player){
        if (!hasPlayerData(player)) {
            playerDatas.put(player.getUniqueId(), new PlayerData());
        }else{
            PlayerData data = new PlayerData();
            ResultSet set = playersDB.getValues(Pair.init("name",player.getName()),
                    "selected_spell",
                    "mana",
                    "mana_regeneration",
                    "mana_regeneration_max_cooldown",
                    "magic_level",
                    "magic_experience",
                    "magic_points",
                    "is_mage",
                    "learned_spells"
            );

            try {
                data.setSelectedSpell(Spells.getSpellByKey(set.getString("selected_spell")));
                data.setMana(set.getInt("mana"));
                data.setManaRegeneration(set.getInt("mana_regeneration"));
                data.setManaRegenerationMaxCooldown(set.getInt("mana_regeneration_max_cooldown"));
                data.setMagicLevel(set.getInt("magic_level"));
                data.setMagicExperience(set.getDouble("magic_experience"));
                data.setMagicPoints(set.getInt("magic_points"));
                data.setMage(true ? set.getInt("is_mage") == 1 : false);
                System.out.println(set.getBoolean("is_mage"));
                data.setLearnedSpells(set.getString("learned_spells"));
                playerDatas.put(player.getUniqueId(),data);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void savePlayerData(Player player){
        if (!containsPlayer(player)){
            newPlayerData(player);
        }
        PlayerData data = playerDatas.get(player.getUniqueId());

        if (!hasPlayerData(player)){
            playersDB.insertValues(
                    Pair.init("name",player.getName()),
                    Pair.init("mana",data.getMana()),
                    Pair.init("selected_spell", data.getSelectedSpellKey()),
                    Pair.init("mana_regeneration",data.getManaRegeneration()),
                    Pair.init("mana_regeneration_max_cooldown",data.getManaRegenerationMaxCooldown()),
                    Pair.init("magic_level",data.getMagicLevel()),
                    Pair.init("magic_experience",data.getMagicExperience()),
                    Pair.init("magic_points",data.getMagicPoints()),
                    Pair.init("is_mage",data.isMage() ? 1 : 0),
                    Pair.init("learned_spells",data.getLearnedSpells())
                    );
        }else{
            playersDB.updateValues(Pair.init("name",player.getName()),
                    Pair.init("mana",data.getMana()),
                    Pair.init("selected_spell", data.getSelectedSpellKey()),
                    Pair.init("mana_regeneration",data.getManaRegeneration()),
                    Pair.init("mana_regeneration_max_cooldown",data.getManaRegenerationMaxCooldown()),
                    Pair.init("magic_level",data.getMagicLevel()),
                    Pair.init("magic_experience",data.getMagicExperience()),
                    Pair.init("magic_points",data.getMagicPoints()),
                    Pair.init("is_mage",data.isMage() ? 1 : 0),
                    Pair.init("learned_spells",data.getLearnedSpells())
            );
        }
        playerDatas.remove(player.getUniqueId());
    }

    public boolean containsPlayer(Player player){
        return playerDatas.containsKey(player.getUniqueId());
    }

    public void newPlayerData(Player player){
        playerDatas.put(player.getUniqueId(),new PlayerData());
    }

    public boolean hasPlayerData(Player player){
        ResultSet set = playersDB.getAllValues(Pair.init("name",player.getName()));
        if (set != null) {
            try {
                if (set.isClosed()) {
                    return false;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            return false;
        }
        return true;
    }

    public PlayerData getPlayerData(Player player){
        if (player != null){
            return playerDatas.get(player.getUniqueId());
        }
        return null;
    }

    public Player getPlayer(PlayerData data){
        if (data != null){
            for (Map.Entry<UUID,PlayerData> entry : playerDatas.entrySet()){
                if (entry.getValue().equals(data)){
                    Player player = Bukkit.getPlayer(entry.getKey());
                    if (player != null){
                        return player;
                    }
                }
            }
        }
        return null;
    }


    public void update(long ticks){
        playerDatas.forEach((uuid,data)->{
            data.update(ticks);
        });
    }

    public Database getPlayersDB() {
        return playersDB;
    }
}
