package org.astemir.magiccraft.recipe;

public class RecipeScheme{

    private String[] schemeLines;

    public RecipeScheme(String... l) {
        schemeLines = l;
    }

    public String[] getSchemeLines() {
        return schemeLines;
    }
}