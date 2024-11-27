package me.dnr2144.kioskv4andv5;

import me.dnr2144.kioskv2.MenuItem;

import java.util.List;

public class App {
    public static void main(String[] args) {

        List<MenuItem> burgers = List.of(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        );
        List<MenuItem> drinks = List.of(
                new MenuItem("Coke", 2.5, "시원한 코카콜라"),
                new MenuItem("Diet Coke", 2.5, "칼로리를 줄인 다이어트 코카콜라"),
                new MenuItem("Sprite", 2.5, "상쾌한 스프라이트"),
                new MenuItem("Iced Tea", 3.0, "달콤한 아이스티"),
                new MenuItem("Lemonade", 3.5, "상큼한 레모네이드"),
                new MenuItem("Milkshake", 5.0, "바닐라, 초콜릿, 딸기 중 선택 가능한 밀크쉐이크")
        );
        List<MenuItem> desserts = List.of(
                new MenuItem("Vanilla Custard", 4.5, "부드럽고 달콤한 바닐라 커스터드"),
                new MenuItem("Chocolate Custard", 4.5, "진한 초콜릿 풍미의 커스터드"),
                new MenuItem("Strawberry Milkshake", 5.5, "상큼한 딸기 밀크쉐이크"),
                new MenuItem("Cookie & Cream Shake", 5.5, "쿠키와 크림이 어우러진 밀크쉐이크"),
                new MenuItem("Brownie Sundae", 6.0, "따뜻한 브라우니와 아이스크림"),
                new MenuItem("Ice Cream Cone", 3.0, "바삭한 콘과 아이스크림")
        );

        List<Menu> menus = List.of(
                new Menu("Burgers", burgers),
                new Menu("Drinks", drinks),
                new Menu("Desserts", desserts)
        );


        KioskV4 kioskV4 = new KioskV4(menus);
        kioskV4.start();
    }
}
