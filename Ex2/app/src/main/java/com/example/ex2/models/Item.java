package com.example.ex2.models;

public class Item {


    private String title;
    private String description;
    private int quantity;
    private float weight;
    private String weightUnit;
    public Item(String title, String description, int quantity, float weight, String weightUnit) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.weight = weight;
        this.weightUnit = weightUnit;
    }

    public Item(String title, String description, int quantity, float weight) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.weight = weight;
    }

    public Item(String title, String description, int quantity) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
    }

    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Item(String title) {
        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }




}
