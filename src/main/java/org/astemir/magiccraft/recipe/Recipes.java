package org.astemir.magiccraft.recipe;

import org.astemir.magiccraft.item.Items;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import java.util.ArrayList;
import java.util.List;
import static org.astemir.magiccraft.recipe.RecipeUtils.*;


public class Recipes {

    private List<Recipe> recipes = new ArrayList<>();


    private final Recipe WOODEN_MAGIC_STICK = register(shaped(
            "wooden_magic_stick",
            Items.WOODEN_MAGIC_STICK,
            magicRod(),
            component('a',Material.STICK)));

    private final Recipe IRON_MAGIC_STICK = register(shaped(
            "iron_magic_stick",
            Items.IRON_MAGIC_STICK,
            magicRodB(),
            component('a',Material.STICK),
            component('b',Material.IRON_INGOT)));

    private final Recipe GOLDEN_MAGIC_STICK = register(shaped(
            "golden_magic_stick",
            Items.GOLDEN_MAGIC_STICK,
            magicRodB(),
            component('b',Material.GOLD_INGOT),
            component('a',Material.STICK)));

    private final Recipe ENDER_MAGIC_STICK = register(shaped(
            "ender_magic_stick",
            Items.ENDER_MAGIC_STICK,
            magicRodB(),
            component('a',Material.ENDER_PEARL),
            component('b',Material.ENDER_EYE)));

    private final Recipe DISCORD_MAGIC_STICK = register(shaped(
            "discord_magic_stick",
            Items.DISCORD_MAGIC_STICK,
            magicRodB(),
            component('a',Items.MANA_INGOT),
            component('b',Items.ANTIMAGIUM)));

    private final Recipe WOODEN_MAGIC_STAFF = register(shaped(
            "wooden_magic_staff",
            Items.WOODEN_MAGIC_STAFF,
            magicStaff(),
            component('a',Material.STICK)));

    private final Recipe IRON_MAGIC_STAFF = register(shaped(
            "iron_magic_staff",
            Items.IRON_MAGIC_STAFF,
            magicStaffB(),
            component('a',Material.STICK),
            component('b',Material.IRON_INGOT)));

    private final Recipe GOLDEN_MAGIC_STAFF = register(shaped(
            "golden_magic_staff",
            Items.GOLDEN_MAGIC_STAFF,
            magicStaffB(),
            component('a',Material.STICK),
            component('b',Material.GOLD_INGOT)));

    private final Recipe ENDER_MAGIC_STAFF = register(shaped(
            "ender_magic_staff",
            Items.ENDER_MAGIC_STAFF,
            magicStaffB(),
            component('a',Material.ENDER_PEARL),
            component('b',Material.ENDER_EYE)));

    private final Recipe DISCORD_MAGIC_STAFF = register(shaped(
            "discord_magic_staff",
            Items.DISCORD_MAGIC_STAFF,
            magicStaffB(),
            component('a',Items.MANA_INGOT),
            component('b',Items.ANTIMAGIUM)));

    private final Recipe MANA_SWORD = register(shaped(
            "mana_sword",
            Items.MANA_SWORD,
            sword(),
            component('a',Items.MANA_INGOT),
            component('b',Items.MANA_INGOT)));

    private final Recipe MAGE_KILLER = register(shaped(
            "mage_killer",
            Items.MAGE_KILLER,
            sword(),
            component('a',Items.ANTIMAGIUM_INGOT),
            component('b',Material.STICK)));


    private final Recipe ANTIMAGIC_APPLE = register(shapeless(
            "antimagic_apple",
            Items.ANTIMAGIC_APPLE,
            ingredient(Items.ANTIMAGIUM),
            ingredient(Items.ANTIMAGIUM),
            ingredient(Items.ANTIMAGIUM),
            ingredient(Items.ANTIMAGIUM),
            ingredient(Material.APPLE)
           ));

    private final Recipe MAGIC_POTION = register(shapeless(
            "magic_potion",
            Items.MAGIC_POTION,
            ingredient(Material.GLASS_BOTTLE),
            ingredient(Material.GHAST_TEAR),
            ingredient(Material.BLAZE_POWDER),
            ingredient(Material.LAPIS_LAZULI),
            ingredient(Material.CHORUS_FRUIT),
            ingredient(Material.ENDER_PEARL),
            ingredient(Material.REDSTONE)
    ));

    private final Recipe MANA_POTION = register(shapeless(
            "mana_potion_from_mana",
            Items.MANA_POTION,
            ingredient(Items.EXTRACTED_MANA),
            ingredient(Items.EXTRACTED_MANA),
            ingredient(Items.EXTRACTED_MANA),
            ingredient(Material.GLASS_BOTTLE)
    ));

    private final Recipe MANA_POTION_FROM_THINGS = register(shapeless(
            "mana_potion_from_things",
            Items.MANA_POTION,
            ingredient(Material.ENDER_PEARL),
            ingredient(Material.LAPIS_LAZULI),
            ingredient(Material.SUGAR),
            ingredient(Material.BLAZE_POWDER),
            ingredient(Material.GLASS_BOTTLE)
    ));

    private final Recipe ANTIMAGIUM = register(shapeless(
            "antimagium",
            Items.ANTIMAGIUM,
            ingredient(Material.DIAMOND),
            ingredient(Material.GHAST_TEAR),
            ingredient(Material.BLAZE_POWDER),
            ingredient(Material.LAPIS_LAZULI),
            ingredient(Material.CHORUS_FRUIT),
            ingredient(Material.ENDER_PEARL),
            ingredient(Material.REDSTONE)
    ));

    private final Recipe MANA_CRYSTAL = register(shaped(
            "mana_crystal",
            Items.MANA_CRYSTAL,
            nine(),
            component('x',Items.EXTRACTED_MANA)));

    private final Recipe MANA_INGOT = register(cooking(
            "mana_ingot",
            Items.MANA_INGOT,
            Items.MANA_CRYSTAL,5,60));

    private final Recipe ANTIMAGIUM_INGOT = register(shaped(
            "antimagium_ingot",
            Items.ANTIMAGIUM_INGOT,
            nine(),
            component('x',Items.ANTIMAGIUM)));

    public Recipe register(Recipe recipe){
        recipes.add(recipe);
        return recipe;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
