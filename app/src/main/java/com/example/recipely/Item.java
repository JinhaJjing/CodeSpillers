package com.example.recipely;

import java.io.Serializable;
import java.util.List;

public class Item  implements Serializable {
    private String title;
    private String image_url;
    private List<String> ingredients;
    private List<String> steps;

    public Item(String title) {
        this.title = title;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "[title : "+title+ " image_url : "+ image_url + "ingredients : "+ingredients.toString()+" steps : "+ steps.toString()+"]";
    }
}
