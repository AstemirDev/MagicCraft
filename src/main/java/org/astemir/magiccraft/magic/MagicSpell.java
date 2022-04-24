package org.astemir.magiccraft.magic;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.ConfigurableSpell;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class MagicSpell {


    private Set<String> chatSignals;
    private SpellType type;
    private String idKey = "";
    private int manaUsage = 1;
    private int pointsCost = 1;
    private int levelRequired = 1;
    private int ticksCooldown = 0;

    public MagicSpell(ConfigurableSpell configuration) {
        this.type = configuration.type();
        this.manaUsage = configuration.mana();
        this.pointsCost = configuration.points();
        this.levelRequired = configuration.level();
        this.chatSignals = configuration.activationPhrases();
        setSecondsCooldown(configuration.cooldown());
    }

    public void use(Player player){
        if (!MagicCraft.cooldownManager.hasCooldown(player,this)) {
            PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
            if (data.hasEnoughMana(manaUsage)) {
                if (!(this instanceof IHasCooldownException)) {
                    cooldown(player);
                }
                if (!(this instanceof IHasManaUniqueCondition)) {
                    onUse(player);
                }
                data.addMagicExperience(experienceFormula());
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL,1,0.75f);
            }
            if (this instanceof IHasManaUniqueCondition) {
                if (((IHasManaUniqueCondition) this).manaUniqueCondition(player)) {
                    MagicCraft.playerDataHandler.getPlayerData(player).removeMana(manaUsage);
                }
                onUse(player);
            } else {
                MagicCraft.playerDataHandler.getPlayerData(player).removeMana(manaUsage);
            }
        }else{
            broadcastCooldown(player);
        }
    }

    public void broadcastCooldown(Player player){
        if (MagicCraft.cooldownManager.getCooldownInTicks(player,this) >= 20) {
            MagicCraft.uiRenderer.getBossbarHandler().broadcastSpellCooldown(player, this, 60);
        }
    }

    public boolean isCanBeUsedAgain(Player player){
        if (!MagicCraft.cooldownManager.hasCooldown(player,this)) {
            if (MagicCraft.playerDataHandler.getPlayerData(player).hasEnoughMana(manaUsage)) {
                return true;
            }
        }
        return false;
    }

    public double experienceFormula(){
        return (manaUsage + pointsCost + levelRequired) * 2;
    }

    public abstract void onUse(Player player);

    public String getTexture(){
        return type.getTexture();
    }

    public int getManaUsage(){
        return manaUsage;
    }

    public int getPointsCost() {
        return pointsCost;
    }

    public void setPointsCost(int pointsCost) {
        this.pointsCost = pointsCost;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }

    public Set<String> getChatSignals() {
        return chatSignals;
    }

    public void setRegisterKey(String key) {
        this.idKey = key;
    }

    public String getRegisterKey() {
        return idKey;
    }

    public int getTicksCooldown() {
        return ticksCooldown;
    }

    public void setTicksCooldown(int ticksCooldown) {
        this.ticksCooldown = ticksCooldown;
    }

    public void setSecondsCooldown(int seconds) {
        this.ticksCooldown = seconds*20;
    }

    public void cooldown(Player player){
        MagicCraft.cooldownManager.setCooldown(player, this, ticksCooldown);
    }

    public void fakeCooldown(Player player){
        if (!MagicCraft.cooldownManager.hasCooldown(player,this)) {
            MagicCraft.cooldownManager.setCooldown(player, this, 1);
        }
    }


    public <T extends EntityMagic> T createProjectile(Class<T> className,Player player){
        return EntityMagic.create(className,player);
    }
}
