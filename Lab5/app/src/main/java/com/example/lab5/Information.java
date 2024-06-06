package com.example.lab5;

public class Information {
    private int image;
    private String name;
    private String description;
    private String type;

    public Information(int image, String name, String description, String type) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
