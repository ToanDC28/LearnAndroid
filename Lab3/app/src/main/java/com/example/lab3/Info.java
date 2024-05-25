package com.example.lab3;

public class Info {
    private int images;
    private String name;
    private String description;

    public int getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Info(int images, String name, String description) {
        this.images = images;
        this.name = name;
        this.description = description;
    }
}
