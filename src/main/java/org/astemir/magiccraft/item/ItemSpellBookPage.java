package org.astemir.magiccraft.item;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.graphics.ItemModels;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemSpellBookPage extends Item{


    public ItemSpellBookPage(ItemModel texture, String nameKey) {
        super(texture, nameKey);
    }

    @Override
    public boolean onRightClick(PlayerClickEvent e) {
        e.getPlayer().swingMainHand();
        ItemStack itemStack = e.getItem();
        String spellID = getStringTag(itemStack,"spell");
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(e.getPlayer());
        if (spellID != null){
            MagicSpell spell = Spells.getSpellByKey(spellID);
            MagicSpell selected = data.getSelectedSpell();
            if (selected != null){
                if (selected.equals(spell)){
                    return super.onRightClick(e);
                }
            }
            if (spell != null) {
                if (data.hasLearnedSpell(spell)) {
                    data.setSelectedSpell(spell);
                    e.getPlayer().sendMessage(Component.translatable("msg.magiccraft.spell_selected").append(Component.translatable("spell.magiccraft." + spell.getRegisterKey())));
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1f, 0.65f);
                } else {
                    e.getPlayer().sendMessage(Component.translatable("msg.magiccraft.spell_not_learned").append(Component.translatable("spell.magiccraft." + spell.getRegisterKey())));
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 0.5f);
                }
            }
        }
        return super.onRightClick(e);
    }

    @Override
    public boolean onEntityClick(PlayerInteractAtEntityEvent e) {
        e.setCancelled(true);
        return true;
    }

    @Override
    public boolean onMoveToAnotherInventory(InventoryClickEvent e) {
        if (e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)){
            return true;
        }
        if (e.getClickedInventory() != null) {
            if (e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onSpawnInWorldAsEntity(EntitySpawnEvent e) {
        return true;
    }

    @Override
    public boolean onDrop(PlayerDropItemEvent e) {
        e.getItemDrop().remove();
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_FRAME_BREAK,1,0.5f);
        return false;
    }

    public ItemStack getRightSpellItemStack(String spellID){
        MagicSpell spell = Spells.getSpellByKey(spellID);
        ItemStack stack = setStringTag(toItemStack(),"spell",spellID);

        stack = changeTexture(stack, ItemModels.textureOfSpellSymbol(spell.getTexture()));
        ItemMeta meta = stack.getItemMeta();
        net.md_5.bungee.api.chat.TranslatableComponent component = new TranslatableComponent();
        component.setItalic(false);
        component.setColor(ChatColor.WHITE);
        component.setTranslate("spell.magiccraft." + spell.getRegisterKey());

        TextComponent manaComponent = new TextComponent(spell.getManaUsage()+ UnicodeSymbols.MANA_UI);
        manaComponent.setItalic(false);
        manaComponent.setColor(ChatColor.WHITE);

        TextComponent cooldownComponent = new TextComponent(spell.getTicksCooldown()/20+ UnicodeSymbols.COOLDOWN_UI);
        cooldownComponent.setItalic(false);
        cooldownComponent.setColor(ChatColor.WHITE);

        meta.setDisplayNameComponent(new BaseComponent[]{component});
        meta.setLoreComponents(Arrays.asList(new BaseComponent[]{manaComponent},new BaseComponent[]{cooldownComponent}));
        stack.setItemMeta(meta);
        return stack;
    }
}
