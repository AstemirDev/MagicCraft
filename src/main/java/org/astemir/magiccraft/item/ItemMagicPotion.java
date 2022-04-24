package org.astemir.magiccraft.item;

import net.kyori.adventure.text.Component;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.MagicRunnable;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemMagicPotion extends Item{



    private final String MAGIC_POTION_BEGIN = "msg.magiccraft.magic_potion_begin";
    private final String MAGIC_POTION_MIDDLE = "msg.magiccraft.magic_potion_middle";
    private final String MAGIC_POTION_END = "msg.magiccraft.magic_potion_end";
    private final String MAGIC_POTION_INTERRUPT = "msg.magiccraft.magic_potion_interrupt";


    public ItemMagicPotion(ItemModel texture, String nameKey) {
        super(texture, nameKey);
    }

    @Override
    public boolean onConsume(PlayerItemConsumeEvent e) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(e.getPlayer());
        if (!data.isMage()) {
            boolean begin = MagicCraft.runnables.run(new MagicRunnable(e.getPlayer().getUniqueId(),400) {
                @Override
                protected void update() {
                    if (getLeft() == 200){
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,20,0,false));
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20,0,false));
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HARM,0,0,false));
                        e.getPlayer().sendMessage(Component.translatable(MAGIC_POTION_MIDDLE));
                        e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ZOMBIE_VILLAGER_CURE,1,0.5f);
                    }
                }

                @Override
                protected void end() {
                    data.setMage(true);
                    data.setMana(0);
                    e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED,1,1f);
                    e.getPlayer().sendMessage(Component.translatable(MAGIC_POTION_END));
                }

                @Override
                protected void interrupted() {
                    e.getPlayer().sendMessage(Component.translatable(MAGIC_POTION_INTERRUPT));
                }

                @Override
                protected boolean condition() {
                    return !e.getPlayer().isDead() && e.getPlayer().isOnline();
                }
            });
            if (begin){
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WITHER,200,2,false));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,400,2,false));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,400,2,false));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON,400,2,false));
                e.getPlayer().sendMessage(Component.translatable(MAGIC_POTION_BEGIN));
                e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.ENTITY_ZOMBIE_VILLAGER_CURE,1,1f);
            }else{
                kill(e.getPlayer());
            }
        }else{
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,0,false));
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,100,0,false));
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

    @Override
    public ItemStack toItemStack() {
        ItemStack itemStack = super.toItemStack();
        itemStack.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        PotionMeta meta = (PotionMeta) itemStack.getItemMeta();
        meta.setColor(Color.WHITE);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
