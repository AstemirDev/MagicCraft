package org.astemir.magiccraft.item;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.magic.IPassiveSpell;
import org.astemir.magiccraft.magic.PassiveQuirkSpell;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;

public class ItemMagicStick extends Item{

    public ItemMagicStick(ItemModel texture, String nameKey) {
        super(texture, nameKey);
    }


    @Override
    public boolean onRightClick(PlayerClickEvent e) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(e.getPlayer());
        if (Items.isItem(e.getPlayer().getInventory().getItemInOffHand(),ItemMagicStick.class)) {
            e.getPlayer().swingOffHand();
        }else{
            e.getPlayer().swingMainHand();
        }
        if (data.isMage()) {
            if (!e.getPlayer().hasCooldown(Material.STICK)) {
                if (e.getClickedBlock() != null) {
                    if (e.getClickedBlock().getType().equals(Material.BOOKSHELF)) {
                        Location loc = e.getClickedBlock().getLocation().clone().add(0.5f, 0, 0.5f);
                        World world = loc.getWorld();
                        world.playSound(loc, "magiccraft.items.magic_heal", 0.5f, 1.5f);
                        e.getClickedBlock().setType(Material.AIR);
                        for (int i = 0; i < 15; i++) {
                            world.spawnParticle(Particle.SPELL_WITCH, loc.getX() + RandomUtils.randomFloat(-0.5f, 0.5f), loc.getBlockY() + RandomUtils.randomFloat(-0.5f, 0.5f), loc.getZ() + RandomUtils.randomFloat(-0.5f, 0.5f), 0, 0, 0, 0);
                        }
                        world.dropItem(loc, ((ItemSpellBook)Items.SPELL_BOOK).randomColored());
                        e.getPlayer().setCooldown(Material.STICK, 40);
                        if (data.getSelectedSpell() != null){
                            MagicCraft.playerDataHandler.getPlayerData(e.getPlayer()).getSelectedSpell().fakeCooldown(e.getPlayer());
                        }
                    }
                }
            }
            if (data.getSelectedSpell() != null) {
                if (!(data.getSelectedSpell() instanceof IPassiveSpell)) {
                    MagicCraft.playerDataHandler.getPlayerData(e.getPlayer()).getSelectedSpell().use(e.getPlayer());
                }
            }
        }
        return true;
    }

}
