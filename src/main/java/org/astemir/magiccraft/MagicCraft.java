package org.astemir.magiccraft;

import org.astemir.magiccraft.beams.BeamHandler;
import org.astemir.magiccraft.commands.ItemGiveCommand;
import org.astemir.magiccraft.database.Database;
import org.astemir.magiccraft.database.DatabaseField;
import org.astemir.magiccraft.database.DatabaseFieldType;
import org.astemir.magiccraft.event.EventListener;
import org.astemir.magiccraft.graphics.PlayerUIRenderer;
import org.astemir.magiccraft.magic.ISpellUpdateHandled;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.player.PlayerCooldownManager;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.player.PlayerDataHandler;
import org.astemir.magiccraft.recipe.Recipes;
import org.astemir.magiccraft.utils.MagicRunnable;
import org.astemir.magiccraft.utils.MagicRunnables;
import org.astemir.magiccraft.utils.Pair;
import org.astemir.magiccraft.world.InsistentBlocksHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.sql.SQLException;


public final class MagicCraft extends JavaPlugin {

    public static PlayerDataHandler playerDataHandler;
    public static PlayerCooldownManager cooldownManager;
    public static BeamHandler beamHandler;
    public static PlayerUIRenderer uiRenderer;
    public static MagicRunnables runnables;
    private static InsistentBlocksHandler blocksHandler;
    private static Recipes recipes;

    public static long ticks = 0;

    @Override
    public void onEnable() {
        playerDataHandler = new PlayerDataHandler();
        beamHandler = new BeamHandler();
        uiRenderer = new PlayerUIRenderer();
        cooldownManager = new PlayerCooldownManager();
        recipes = new Recipes();
        runnables = new MagicRunnables();
        blocksHandler = new InsistentBlocksHandler();
        registerEvents();
        registerCommands();
        registerRecipes();
        runLoop();
        loadPlayersData();
    }

    @Override
    public void onDisable() {
       savePlayersData();
       uiRenderer.getBossbarHandler().disableAllBossbars();
    }

    private void registerRecipes(){
        recipes.getRecipes().forEach((a)->{
            Bukkit.addRecipe(a);
        });
    }

    private void loadPlayersData(){
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            playerDataHandler.loadPlayerData(onlinePlayer);
        }
    }

    private void savePlayersData(){
        try {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                playerDataHandler.savePlayerData(onlinePlayer);
            }
            playerDataHandler.getPlayersDB().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void registerEvents(){
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new EventListener(),this);
    }

    private void registerCommands(){
        Bukkit.getPluginCommand("itemgive").setExecutor(new ItemGiveCommand());
    }

    private void runLoop(){
        new BukkitRunnable(){
            @Override
            public void run() {
                ticks++;
                playerDataHandler.update(ticks);
                runnables.update(ticks);
                beamHandler.update(ticks);
                blocksHandler.update(ticks);
                cooldownManager.update();
                if (!Bukkit.getOnlinePlayers().isEmpty()){
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        PlayerData data = playerDataHandler.getPlayerData(onlinePlayer);
                        if (data != null) {
                            if (data.getSelectedSpell() != null) {
                                MagicSpell spell = data.getSelectedSpell();
                                if (data.getSelectedSpell().equals(spell)) {
                                    if (spell instanceof ISpellUpdateHandled) {
                                        ((ISpellUpdateHandled) spell).onUpdate(onlinePlayer, ticks);
                                    }
                                }
                            }
                            uiRenderer.render(onlinePlayer);
                        }
                    }
                }
            }
        }.runTaskTimer(this,0,0);
    }
}
