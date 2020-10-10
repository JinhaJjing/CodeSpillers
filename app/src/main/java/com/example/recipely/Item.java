package com.example.recipely;

import java.io.Serializable;

public class Item  implements Serializable {
    private String title;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "[title : "+title+ " image : "+ image + "]";
    }
}
