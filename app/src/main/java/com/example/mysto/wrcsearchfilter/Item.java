package com.example.mysto.wrcsearchfilter;

public class Item {

    private String name;
    private int photo;
    private String type;

    public Item(String name, int photo, String type) {
        this.name = name;
        this.photo = photo;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
