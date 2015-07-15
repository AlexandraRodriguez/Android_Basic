package com.example.ale.groceryshoppingassistant;

public class Food {
    private int calories;
    private int fat;
    private String name;


    public Food(String name, int calories, int fat){
        this.name = name;
        this.calories = calories;
        this.fat = fat;
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
