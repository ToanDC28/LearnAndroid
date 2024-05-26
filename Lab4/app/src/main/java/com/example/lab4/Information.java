package com.example.lab4;

public class Information {
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

    public Information(int images, String name, String description) {
        this.images = images;
        this.name = name;
        this.description = description;
    }
}
