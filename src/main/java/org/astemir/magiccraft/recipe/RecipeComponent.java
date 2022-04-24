package org.astemir.magiccraft.recipe;

import org.bukkit.inventory.ItemStack;

public class RecipeComponent{

    private char key;
    private ItemStack itemStack;

    public RecipeComponent(char key, ItemStack itemStack) {
        this.key = key;
        this.itemStack = itemStack;
    }

    public char getKey() {
        return key;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
