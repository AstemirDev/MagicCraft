package org.astemir.magiccraft.item;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public abstract class ItemCustomSword extends Item{


    public ItemCustomSword(ItemModel texture, String nameKey) {
        super(texture, nameKey);
    }


    @Override
    public boolean onRightClick(PlayerClickEvent e) {
        return false;
    }

    @Override
    public boolean onLeftClick(PlayerClickEvent e) {
        return false;
    }

    public double getDamage(){
        return 3;
    }

    public double getAttackSpeed(){
        return -2.4;
    }

    @Override
    public ItemStack toItemStack() {
        ItemStack itemStack = super.toItemStack();
        ItemMeta meta = itemStack.getItemMeta();

        double damage = getDamage();
        double attackSpeed = getAttackSpeed();

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        TextComponent empty = new TextComponent();
        TranslatableComponent mainHand = new TranslatableComponent();
        mainHand.setColor(ChatColor.GRAY);
        mainHand.setItalic(false);
        mainHand.setTranslate("item.modifiers.mainhand");

        TextComponent damageText = new TextComponent(" "+(int)(1+damage)+" ");
        damageText.setItalic(false);
        damageText.setColor(ChatColor.DARK_GREEN);

        TranslatableComponent damageTextText = new TranslatableComponent();
        damageTextText.setColor(ChatColor.DARK_GREEN);
        damageTextText.setItalic(false);
        damageTextText.setTranslate("attribute.name.generic.attack_damage");

        TextComponent attackSpeedText = new TextComponent(" "+(double)(4+attackSpeed)+" ");
        attackSpeedText.setItalic(false);
        attackSpeedText.setColor(ChatColor.DARK_GREEN);

        TranslatableComponent attackSpeedTextText = new TranslatableComponent();
        attackSpeedTextText.setColor(ChatColor.DARK_GREEN);
        attackSpeedTextText.setItalic(false);
        attackSpeedTextText.setTranslate("attribute.name.generic.attack_speed");

        meta.setLoreComponents(Arrays.asList(
                new BaseComponent[]{empty},
                new BaseComponent[]{mainHand},
                new BaseComponent[]{damageText,damageTextText},
                new BaseComponent[]{attackSpeedText,attackSpeedTextText}));

        itemStack.setItemMeta(meta);
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        nmsItemStack.a(GenericAttributes.ATTACK_DAMAGE, new net.minecraft.server.v1_16_R3.AttributeModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF"), "Weapon modifier", damage, net.minecraft.server.v1_16_R3.AttributeModifier.Operation.ADDITION), EnumItemSlot.MAINHAND);
        nmsItemStack.a(GenericAttributes.ATTACK_SPEED, new net.minecraft.server.v1_16_R3.AttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "Weapon modifier", attackSpeed, net.minecraft.server.v1_16_R3.AttributeModifier.Operation.ADDITION), EnumItemSlot.MAINHAND);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }
}
