package com.example.ex2.models;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private ArrayList<Item> items;

    public User(String firstName, String lastName, ArrayList<Item> items) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.items = items;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
