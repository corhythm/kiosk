package me.dnr2144.kioskv2;

public record MenuItem(String name, double price, String menuInfo) {
    @Override
    public String toString() {
        return String.format("%-15s | W %-4.1f | %s", name, price, menuInfo);
    }
}