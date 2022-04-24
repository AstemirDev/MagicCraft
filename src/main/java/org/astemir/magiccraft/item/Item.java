package org.astemir.magiccraft.item;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.minecraft.server.v1_16_R3.NBTBase;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.ItemModel;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Item {

    private ItemModel texture;
    private String nameKey;

    public Item(ItemModel texture, String nameKey){
        this.texture = texture;
        this.nameKey = nameKey;
    }

    public boolean onRightClick(PlayerClickEvent e){
        return false;
    }

    public boolean onLeftClick(PlayerClickEvent e){
        return false;
    }

    public boolean onDrop(PlayerDropItemEvent e){
        return false;
    }

    public boolean onAttackEntity(EntityDamageByEntityEvent e){
        return false;
    }

    public boolean onEntityClick(PlayerInteractAtEntityEvent e){
        return false;
    }

    public boolean onConsume(PlayerItemConsumeEvent e){
        return false;
    }

    public boolean onMoveToAnotherInventory(InventoryClickEvent e){
        return false;
    }

    public boolean onSpawnInWorldAsEntity(EntitySpawnEvent e){
        return false;
    }

    public static ItemStack changeTexture(ItemStack itemStack, ItemModel texture){
        if (itemStack.getItemMeta() != null){
            itemStack.setType(texture.getMaterial());
            ItemMeta meta = itemStack.getItemMeta();
            meta.setCustomModelData(texture.getCustomModelData());
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }

    public List<Component> getLore(){
        return null;
    }

    public ItemModel getTexture(){
        return texture;
    }

    public String getNameKey(){
        return nameKey;
    }

    public ItemStack toItemStack(){
        ItemStack itemStack = new ItemStack(texture.getMaterial());
        ItemMeta meta = itemStack.getItemMeta();
        TranslatableComponent component = new TranslatableComponent();
        component.setItalic(false);
        component.setColor(ChatColor.WHITE);
        component.setTranslate("item.magiccraft."+nameKey);
        meta.setDisplayNameComponent(new BaseComponent[]{component});
        meta.setCustomModelData(texture.getCustomModelData());
        if (getLore() != null) {
            meta.lore(getLore());
        }
        itemStack.setItemMeta(meta);
        itemStack = setStringTag(itemStack,"custom_id",nameKey);
        return itemStack;
    }

    public static ItemStack setIntTag(ItemStack itemStack,String key,int value){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getOrCreateTag();
        tagCompound.setInt(key,value);
        nmsItemStack.setTag(tagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    public static ItemStack setDoubleTag(ItemStack itemStack,String key,double value){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getOrCreateTag();
        tagCompound.setDouble(key,value);
        nmsItemStack.setTag(tagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    public static ItemStack setStringTag(ItemStack itemStack,String key,String value){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getOrCreateTag();
        tagCompound.setString(key,value);
        nmsItemStack.setTag(tagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    public static ItemStack setTag(ItemStack itemStack, String key, NBTBase tag){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getOrCreateTag();
        tagCompound.set(key,tag);
        nmsItemStack.setTag(tagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    public static ItemStack removeTag(ItemStack itemStack, String key){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getOrCreateTag();
        tagCompound.remove(key);
        nmsItemStack.setTag(tagCompound);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    public static boolean hasTag(ItemStack itemStack, String key){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getOrCreateTag();
        return tagCompound.hasKey(key);
    }

    public static String getStringTag(ItemStack itemStack,String key){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getTag();
        if (tagCompound != null){
            if (tagCompound.getString(key) != null){
                return tagCompound.getString(key);
            }
        }
        return null;
    }

    public static int getIntTag(ItemStack itemStack,String key){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getTag();
        if (tagCompound != null){
            if (tagCompound.getString(key) != null){
                return tagCompound.getInt(key);
            }
        }
        return 0;
    }

    public static double getDoubleTag(ItemStack itemStack,String key){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getTag();
        if (tagCompound != null){
            if (tagCompound.getString(key) != null){
                return tagCompound.getDouble(key);
            }
        }
        return 0;
    }

    public static NBTBase getTag(ItemStack itemStack,String key){
        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagCompound = nmsItemStack.getTag();
        if (tagCompound != null){
            if (tagCompound.getString(key) != null){
                return tagCompound.get(key);
            }
        }
        return null;
    }
}
