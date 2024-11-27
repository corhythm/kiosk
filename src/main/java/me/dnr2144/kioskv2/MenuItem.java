package me.dnr2144.kioskv2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MenuItem {
    private final String name;
    private final double price;
    private final String menuInfo;

    @Override
    public String toString() {
        return String.format("%-15s | W %-4.1f | %s", name, price, menuInfo);
    }
}