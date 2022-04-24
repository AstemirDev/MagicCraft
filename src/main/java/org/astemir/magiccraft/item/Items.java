package org.astemir.magiccraft.item;

import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.graphics.ItemModels;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Items {


    private static List<Item> itemsRegistry = new ArrayList<>();


    public static final Item WOODEN_MAGIC_STICK = registerItem(ItemMagicStick.class,"wooden_magic_stick", ItemModels.MAGIC_STICK_WOODEN);
    public static final Item IRON_MAGIC_STICK = registerItem(ItemMagicStick.class,"iron_magic_stick", ItemModels.MAGIC_STICK_IRON);
    public static final Item GOLDEN_MAGIC_STICK = registerItem(ItemMagicStick.class,"golden_magic_stick", ItemModels.MAGIC_STICK_GOLDEN);
    public static final Item ENDER_MAGIC_STICK = registerItem(ItemMagicStick.class,"ender_magic_stick", ItemModels.MAGIC_STICK_ENDER);
    public static final Item DISCORD_MAGIC_STICK = registerItem(ItemMagicStick.class,"discord_magic_stick", ItemModels.MAGIC_STICK_DISCORD);

    public static final Item WOODEN_MAGIC_STAFF = registerItem(ItemMagicStick.class,"wooden_magic_staff", ItemModels.MAGIC_STAFF_WOODEN);
    public static final Item IRON_MAGIC_STAFF = registerItem(ItemMagicStick.class,"iron_magic_staff", ItemModels.MAGIC_STAFF_IRON);
    public static final Item GOLDEN_MAGIC_STAFF = registerItem(ItemMagicStick.class,"golden_magic_staff", ItemModels.MAGIC_STAFF_GOLDEN);
    public static final Item ENDER_MAGIC_STAFF = registerItem(ItemMagicStick.class,"ender_magic_staff", ItemModels.MAGIC_STAFF_ENDER);
    public static final Item DISCORD_MAGIC_STAFF = registerItem(ItemMagicStick.class,"discord_magic_staff", ItemModels.MAGIC_STAFF_DISCORD);


    public static final Item SPELL_BOOK = registerItem(ItemSpellBook.class,"spell_book", ItemModels.SPELL_BOOK_ANIMATED);
    public static final Item MANA_POTION = registerItem(ItemManaPotion.class,"mana_potion", ItemModels.MANA_POTION);

    public static final Item SPELL_BOOK_PAGE = registerItem(ItemSpellBookPage.class,"spell_book_page", ItemModels.SPELL_BOOK_PAGE_EMPTY);

    public static final Item MAGE_KILLER = registerItem(ItemMageKiller.class,"mage_killer", ItemModels.MAGE_KILLER);
    public static final Item MANA_SWORD = registerItem(ItemManaSword.class,"mana_sword", ItemModels.MANA_SWORD);
    public static final Item EXTRACTED_MANA = registerItem(Item.class,"extracted_mana", ItemModels.EXTRACTED_MANA);
    public static final Item MANA_CRYSTAL = registerItem(Item.class,"mana_crystal", ItemModels.MANA_CRYSTAL);
    public static final Item MANA_INGOT = registerItem(Item.class,"mana_ingot", ItemModels.MANA_INGOT);

    public static final Item ANTIMAGIUM_INGOT = registerItem(Item.class,"antimagium_ingot", ItemModels.ANTIMAGIUM_INGOT);
    public static final Item ANTIMAGIUM = registerItem(Item.class,"antimagium", ItemModels.ANTIMAGIUM);

    public static final Item MAGIC_POTION = registerItem(ItemMagicPotion.class,"magic_potion", ItemModels.MAGIC_POTION);
    public static final Item ANTIMAGIC_APPLE = registerItem(ItemAntimagicApple.class,"antimagic_apple", ItemModels.ANTIMAGIC_APPLE);

    public static final Item ICE_PIKE = registerItem(Item.class,"ice_pike", ItemModels.ICE_PIKE);
    public static final Item BOULDER = registerItem(Item.class,"boulder", ItemModels.BOULDER);

    public static final Item BLACK_HOLE = registerItem(Item.class,"black_hole", ItemModels.BLACK_HOLE);

    public static Item registerItem(Class<? extends Item> itemClass, String key, ItemModel texture){
        try {
            Item item = itemClass.getConstructor(ItemModel.class,String.class).newInstance(texture,key);
            itemsRegistry.add(item);
            return item;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Item getItem(String id){
        for (Item i : itemsRegistry) {
            if (i.getNameKey().equals(id)){
                return i;
            }else
            if (i.getNameKey().contains(id)){
                return i;
            }
        }
        return null;
    }

    public static boolean isItem(ItemStack itemStack,Class<? extends Item> itemClass){
        if (itemStack != null) {
            Item compare = getItem(itemStack);
            if (compare != null) {
                if (itemClass.isInstance(compare)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Item getItem(ItemStack item){
        if (item == null){
            return null;
        }
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (!meta.hasCustomModelData()){
                return null;
            }
            for (Item i : itemsRegistry) {
                String id = Item.getStringTag(item,"custom_id");
                if (id != null){
                    if (i.getNameKey().equals(id)){
                        return i;
                    }
                }
            }
        }
        return null;
    }

}
