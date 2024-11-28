package me.dnr2144.menu;

import java.util.List;


public record Menu(String category, List<MenuItem> menuItems) {

    public void displayMenuItems() { // 메뉴 아이템 출력
        System.out.printf("[ %S MENU]\n", category);
        System.out.println("0. 뒤로가기");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), menuItems.get(i).toString());
        }

    }
}

