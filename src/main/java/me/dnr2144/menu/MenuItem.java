package me.dnr2144.menu;

import java.util.Objects;

public record MenuItem(String name, double price, String menuInfo) {

    @Override
    public boolean equals(Object obj) { // Challenge 과제때, Map 안에 들어간 Menu 구분할 때 사용 (이름으로 구분)
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuItem menuItem = (MenuItem) obj;
        return Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() { // Challenge 과제때, Map 안에 들어간 Menu 구분할 때 사용 (이름으로 구분)
        return Objects.hash(name);
    } // Challenge 과제때, Map 안에 들어간 Menu 구분할 때 사용 (이름으로 구분)

    @Override
    public String toString() {
        return String.format("%-15s | W %-4.1f | %s", name, price, menuInfo);
    }
}