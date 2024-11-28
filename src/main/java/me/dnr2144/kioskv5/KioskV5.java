package me.dnr2144.kioskv5;

import lombok.RequiredArgsConstructor;
import me.dnr2144.menu.Menu;

import java.util.List;
import java.util.Scanner;

import static me.dnr2144.util.KioskConstants.EXIT_OPTION;
import static me.dnr2144.util.KioskConstants.GO_BACK_OPTION;
import static me.dnr2144.util.ErrorMessage.INVALID_INPUT_MESSAGE;

@RequiredArgsConstructor
public class KioskV5 {

    private final List<Menu> menuCategories;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int categoryInput = -1; // 카테고리 입력
        int menuInput = -1; // 메뉴 아이템 입력

        while (true) {
            // 카테고리 먼저 선택
            displayMainMenu();
            if (!isValidInput(scanner)) continue; // 카테고리 번호 숫자인지 체크

            categoryInput = scanner.nextInt();
            if (!isWithinRange(categoryInput, menuCategories.size())) continue; // 지정된 범위를 초과 여부 체크

            if (categoryInput == EXIT_OPTION) { // 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            // 선택된 카테고리 메뉴들 출력
            Menu selectedMenu = menuCategories.get(categoryInput - 1);
            selectedMenu.displayMenuItems();

            if (!isValidInput(scanner)) continue; // 메뉴 아이템 번호 숫자인지 체크

            menuInput = scanner.nextInt();
            if (!isWithinRange(menuInput, selectedMenu.menuItems().size())) continue; // 지정된 범위 초과 여부 체크

            // 카테고리로 돌아가기
            if (menuInput == GO_BACK_OPTION) continue;

            System.out.println("선택한 메뉴: " + selectedMenu.menuItems().get(menuInput - 1).toString());
            System.out.println("-------------------------------");
        }
    }

    private void displayMainMenu() {
        System.out.println("[ MAIN MENU ]");
        System.out.println("0. 종료");
        for (int i = 0; i < menuCategories.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), menuCategories.get(i).category());
        };
    }

    private boolean isValidInput(Scanner scanner) { // scanner를 넘기는 게 좋아보이지는 않는다.
        if (!scanner.hasNextInt()) { // 잘못된 카테고리 문자열 입력
            System.out.println(INVALID_INPUT_MESSAGE);
            scanner.nextLine();
            return false;
        }
        return true;
    }

    private boolean isWithinRange(int input, int size) {
        if (input > size || input < 0) { // 입력된 숫자가 지정된 범위에 있는지 확인
            System.out.println(INVALID_INPUT_MESSAGE);
            return false;
        }
        return true;
    }

}
