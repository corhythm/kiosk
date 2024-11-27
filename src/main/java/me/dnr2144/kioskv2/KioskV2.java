package me.dnr2144.kioskv2;

import me.dnr2144.menu.MenuItem;

import java.util.List;
import java.util.Scanner;

public class KioskV2 {
    private static final int EXIT_OPTION = 0;
    private static final String INVALID_INPUT_MESSAGE = "잘못된 입력입니다. 다시 선택하세요.\n-------------------------------";

    public static void main(String[] args) {

        List<MenuItem> menuItems = List.of(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        );

        Scanner scanner = new Scanner(System.in);
        int input = -1;
        while (input != EXIT_OPTION) {
            displayMenu(menuItems);
            if (!scanner.hasNextInt()) {
                System.out.println(INVALID_INPUT_MESSAGE);
                scanner.nextLine();
                continue;
            }

            input = scanner.nextInt();
            if (input > menuItems.size() || input < 0) {
                System.out.println(INVALID_INPUT_MESSAGE);
                continue;
            }

            String message = input == EXIT_OPTION ? "프로그램을 종료합니다." : menuItems.get(input - 1).toString();
            System.out.println(message);
        }
    }

    private static void displayMenu(List<MenuItem> menuItems) {
        System.out.println("[ SHAKESHACK MENU ]");
        System.out.println("0. 종료");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), menuItems.get(i));
        }
    }
}