package com.example.ale.groceryshoppingassistant;

public class Food {
    private double calories;
    private double fat;
    private String name;
    private String serving;
    private int servingSize;


    public Food(String name, double calories, double fat, String serving, int servingSize){
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.serving = serving;
        this.servingSize = servingSize;
    }

    public String getServingSize() {
        return servingSize+"";
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String getCalories() {
        return calories+"";
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat+"";
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
