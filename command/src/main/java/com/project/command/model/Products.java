package com.project.command.model;

public class Products {
    private String name;
    private String description;

    public Products(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Products(){}

    //Getters
    public String getName(){
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    //Setters


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
