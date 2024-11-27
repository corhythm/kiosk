package me.dnr2144.kioskv2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MenuItem {
    private String name;
    private double price;
    private String customRecipes;

    @Override
    public String toString() {
        return String.format("%-15s | W %-4.1f | %s", name, price, customRecipes);
    }
}