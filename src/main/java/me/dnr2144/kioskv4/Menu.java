package me.dnr2144.kioskv4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.dnr2144.kioskv2.MenuItem;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Menu {

    private final String category;
    private final List<MenuItem> menuItems;

    public void displayMenuItems() {
        System.out.printf("[ %S MENU]\n", category);
        System.out.println("0. 뒤로가기");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), menuItems.get(i).toString());
        }
    }
}

