package org.astemir.magiccraft.recipe;

import org.astemir.magiccraft.item.Item;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

public class RecipeUtils {

    public static Recipe cooking(String name,Item item,Item input,float exp,int cookingTime){
        CookingRecipe recipe = new FurnaceRecipe(new NamespacedKey("magiccraft",name),item.toItemStack(),new RecipeChoice.ExactChoice(input.toItemStack()),exp,cookingTime);
        return recipe;
    }

    public static Recipe shapeless(String name, Item item, ItemStack... ingredients){
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey("magiccraft",name),item.toItemStack());
        for (ItemStack itemStack : ingredients){
            recipe = recipe.addIngredient(itemStack);
        }
        return recipe;
    }

    public static Recipe shaped(String name, Item item,RecipeScheme scheme,RecipeComponent... component){
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey("magiccraft",name),item.toItemStack());
        recipe = recipe.shape(scheme.getSchemeLines());
        for (RecipeComponent recipeComponent : component) {
            recipe = recipe.setIngredient(recipeComponent.getKey(),recipeComponent.getItemStack());
        }
        return recipe;
    }

    public static RecipeScheme magicRod(){
        return new RecipeScheme(
                " a",
                "a ");
    }

    public static RecipeScheme magicRodB(){
        return new RecipeScheme(
                " b",
                "a ");
    }

    public static RecipeScheme magicStaff(){
        return new RecipeScheme(
                "  a",
                " a ",
                "a  ");
    }

    public static RecipeScheme magicStaffB(){
        return new RecipeScheme(
                "  b",
                " a ",
                "a  ");
    }

    public static RecipeScheme sword(){
        return new RecipeScheme(
                "a",
                "a",
                "b");
    }

    public static RecipeScheme pickaxe(){
        return new RecipeScheme(
                "aaa",
                " b ",
                " b ");
    }

    public static RecipeScheme axe(){
        return new RecipeScheme(
                "aa ",
                "ab ",
                " b ");
    }

    public static RecipeScheme shovel(){
        return new RecipeScheme(
                " a ",
                " b ",
                " b ");
    }

    public static RecipeScheme hoe(){
        return new RecipeScheme(
                "aa ",
                " b ",
                " b ");
    }

    public static RecipeScheme nine(){
        return new RecipeScheme(
                "xxx",
                "xxx",
                "xxx");
    }

    public static RecipeScheme four(){
        return new RecipeScheme(
                "xx",
                "xx");
    }

    public static RecipeScheme scheme(String... lines){
        return new RecipeScheme(lines);
    }

    public static RecipeComponent component(char key,ItemStack item){
        return new RecipeComponent(key,item);
    }

    public static ItemStack ingredient(Material material){
        return new ItemStack(material);
    }

    public static ItemStack ingredient(Item item){
        return item.toItemStack();
    }


    public static RecipeComponent component(char key, Material material){
        return new RecipeComponent(key,new ItemStack(material));
    }

    public static RecipeComponent component(char key,Item item){
        return new RecipeComponent(key,item.toItemStack());
    }
}
