package org.astemir.magiccraft.item;

import net.kyori.adventure.text.Component;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.MagicRunnable;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemAntimagicApple extends Item{


    public ItemAntimagicApple(ItemModel texture, String nameKey) {
        super(texture, nameKey);
    }

    private final String ANTIMAGIC_APPLE_BEGIN = "msg.magiccraft.antimagic_apple_begin";
    private final String ANTIMAGIC_APPLE_MIDDLE = "msg.magiccraft.antimagic_apple_middle";
    private final String ANTIMAGIC_APPLE_END = "msg.magiccraft.antimagic_apple_end";
    private final String ANTIMAGIC_APPLE_INTERRUPT = "msg.magiccraft.antimagic_apple_interrupt";


    @Override
    public boolean onConsume(PlayerItemConsumeEvent e) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(e.getPlayer());
        if (data.isMage()) {
            boolean begin = MagicCraft.runnables.run(new MagicRunnable(e.getPlayer().getUniqueId(),400) {
                @Override
                protected void update() {
                    data.cooldownManaRegen();
                    if (getLeft() == 200){
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WITHER,100,2,false));
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,20,0,false));
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20,0,false));
                        e.getPlayer().sendMessage(Component.translatable(ANTIMAGIC_APPLE_MIDDLE));
                        e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ZOMBIE_VILLAGER_CURE,1,0.5f);
                    }
                }

                @Override
                protected void end() {
                    data.setMage(false);
                    e.getPlayer().sendActionBar(Component.text(""));
                    e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED,1,1f);
                    e.getPlayer().sendMessage(Component.translatable(ANTIMAGIC_APPLE_END));
                }

                @Override
                protected void interrupted() {
                    e.getPlayer().sendMessage(Component.translatable(ANTIMAGIC_APPLE_INTERRUPT));
                }

                @Override
                protected boolean condition() {
                    return !e.getPlayer().isDead() && e.getPlayer().isOnline();
                }
            });
            if (begin){
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,400,2,false));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,400,2,false));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON,400,2,false));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HARM,2,0,false));
                e.getPlayer().sendMessage(Component.translatable(ANTIMAGIC_APPLE_BEGIN));
                e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ZOMBIE_VILLAGER_CURE,1,1f);
            }else{
                kill(e.getPlayer());
            }
        }else{
            kill(e.getPlayer());
        }
        return false;
    }


    private void kill(Player player){
        new BukkitRunnable(){
            @Override
            public void run() {
                player.damage(999);
            }
        }.runTaskLater(MagicCraft.getPlugin(MagicCraft.class),1);
    }

    @Override
    public boolean onRightClick(PlayerClickEvent e) {
        return false;
    }

    @Override
    public boolean onLeftClick(PlayerClickEvent e) {
        return false;
    }

}
