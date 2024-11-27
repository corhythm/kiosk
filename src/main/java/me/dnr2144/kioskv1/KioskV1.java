package me.dnr2144.kioskv1;

import java.util.Scanner;

public class KioskV1 {
    public static void main(String[] args) {
        int EXIT_OPTION = 0;
        int input = -1;
        Scanner scanner = new Scanner(System.in);
        String[] menuItems = new String[4];
        menuItems[0] = "1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거";
        menuItems[1] = "2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거";
        menuItems[2] = "3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거";
        menuItems[3] = "4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거";

        while (input != EXIT_OPTION) {
            System.out.println("[ SHAKESHACK MENU ]");
            System.out.println("0. 종료");
            System.out.println(menuItems[0]);
            System.out.println(menuItems[1]);
            System.out.println(menuItems[2]);
            System.out.println(menuItems[3]);

            if (!scanner.hasNextInt()) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n---------------------------------------------------------------------------");
                scanner.nextLine();
                continue;
            }

            input = scanner.nextInt();
            if (input > menuItems.length || input < 0) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n---------------------------------------------------------------------------");
                continue;
            }

            String message = input == EXIT_OPTION ? "작업을 종료합니다." : "선택된 메뉴 ----> " + menuItems[input - 1];
            System.out.println(message + "---------------------------------------------------------------------------");
        }
    }
}
