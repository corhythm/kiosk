package me.dnr2144.kioskv3;

import me.dnr2144.kioskv2.MenuItem;

import java.util.List;
import java.util.Scanner;

import static me.dnr2144.util.Constants.EXIT_OPTION;
import static me.dnr2144.util.Constants.INVALID_INPUT_MESSAGE;

public class KioskV3 {

    private List<MenuItem> menuItems;

    public KioskV3(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        while (input != EXIT_OPTION) {
            displayMenu();
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

    public void displayMenu() {
        System.out.println("[ SHAKESHACK MENU ]");
        System.out.println("0. 종료");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), menuItems.get(i));
        }
    }
}
