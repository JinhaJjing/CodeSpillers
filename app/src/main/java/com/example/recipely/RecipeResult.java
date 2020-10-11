package com.example.recipely;

import java.io.Serializable;
import java.util.List;

public class RecipeResult implements Serializable {
    private List<Item> recipeList;

    public List<Item> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Item> recipeList) {
        this.recipeList = recipeList;
    }
}
