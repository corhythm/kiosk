package me.dnr2144.challenge;

import me.dnr2144.menu.Menu;
import me.dnr2144.menu.MenuItem;

import java.util.*;

import static me.dnr2144.challenge.Discount.displayDiscountInfo;
import static me.dnr2144.challenge.Discount.fromValue;
import static me.dnr2144.util.ErrorMessage.INVALID_INPUT_MESSAGE;
import static me.dnr2144.util.ErrorMessage.INVALID_OPTION_RANGE;
import static me.dnr2144.util.KioskConstants.*;

public class AdvancedKiosk {

    private final Map<MenuItem, Integer> basket;
    private final List<Menu> menuCategories;
    private final Scanner scanner;
    double currBasketSum; // 장바구니 총 금액

    public AdvancedKiosk(List<Menu> menuCategories, Scanner scanner) {
        basket = new HashMap<>();
        this.menuCategories = menuCategories;
        this.scanner = scanner;
        currBasketSum = 0;
    }

    public void start() {
        while (true) {
            try {
                int mainMenuInput = getMainMenuInput();
                validateRange(mainMenuInput, 0,
                        menuCategories.size() + (basket.isEmpty() ? 0 : ORDER_OPTION_NUM)); // input 값 검증
                if (mainMenuInput == EXIT_OPTION) {
                    System.out.println("작업을 종료합니다.");
                    break; // 종료
                }

                if (mainMenuInput <= MAIN_MENU_OPTION_NUM) handleMenuSelection(mainMenuInput); // 메뉴를 선택했을 때
                else handleOrderProcess(mainMenuInput); // '주문' 혹은 '주문 취소'를 선택했을 때

            } catch (InputMismatchException e) { // scanner 입력 중 잘못된 입력 걸러내기
                System.out.println(INVALID_INPUT_MESSAGE);
            } catch (Exception e) { // 그 외 담겨 있는 메시지 출력
                System.out.println(e.getMessage());
            }

            scanner.nextLine(); // buffer 초기화
            System.out.println("-----------------------------");
        }
    }

    private int getMainMenuInput() {
        displayMainMenu();
        return scanner.nextInt();
    }

    private void displayMainMenu() { // 메인 메뉴 출력
        System.out.println("[ MAIN MENU ]");
        System.out.println("0. 종료");
        for (int i = 0; i < menuCategories.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), menuCategories.get(i).category());
        }
        displayOrderMenu(); // 주문 옵션 출력
    }

    private void displayOrderMenu() {
        if (!basket.isEmpty()) { // 장바구니에 데이터가 있으면 주문 옵션 출력
            System.out.println("\n[ ORDER MENU ]");
            System.out.printf("%-15s | %-20s%n", "4. Orders", "장바구니를 확인 후 주문합니다.");
            System.out.printf("%-15s | %-20s%n", "5. Cancel", "진행중인 주문을 취소합니다.");
        }
    }

    private void displayBasket() { // 장바구니에 있는 목록 출력
        if (basket.isEmpty()) throw new NoSuchElementException("장바구니가 비어있습니다");

        System.out.println("\n[ Orders ] (아래와 같이 주문하시겠습니까?)");
        currBasketSum = basket.entrySet().stream()
                .peek(entry -> printBasketItem(entry.getKey(), entry.getValue()))
                .mapToDouble(entry -> entry.getKey().price() * entry.getValue())
                .sum();

        System.out.println("\n[ Total ]");
        System.out.printf("W %.1f%n%n", currBasketSum);
    }

    private void printBasketItem(MenuItem menuItem, int quantity) { // 장바구니 목록 출력
        System.out.printf("%-25s | W %-6.1f | %-35s | x %2d%n",
                menuItem.name(), menuItem.price(), menuItem.menuInfo(), quantity);
    }

    private void handleMenuSelection(int mainMenuInput) {
        Menu selectedMenu = menuCategories.get(mainMenuInput - 1);
        selectedMenu.displayMenuItems(); // 선택한 메뉴 카테고리에 속한 메뉴 아이템들 출력

        int menuItemInput = scanner.nextInt();
        validateRange(menuItemInput, 0, selectedMenu.menuItems().size()); // 메뉴 아이템 값 검증
        if (menuItemInput == GO_BACK_OPTION) return; // 뒤로가기

        handleBasketAddition(selectedMenu.menuItems().get(menuItemInput - 1)); // 장바구니 추가 프로세스
    }

    private void handleBasketAddition(MenuItem selectedMenuItem) {
        System.out.printf("위 메뉴를 장바구니에 추가하시겠습니까?%n%-10s %s%n", "1. 확인", "2. 취소");
        int basketInput = scanner.nextInt();
        addToBasket(selectedMenuItem, basketInput); // 장바구니 추가
    }

    private void handleOrderProcess(int mainMenuInput) {
        if (!isOrderOnGoing(mainMenuInput)) return;

        displayBasket();
        int orderInput = getOrderInput();
        if (orderInput == ORDER_HOLD_AND_GO_BACK_MENU) return;

        completeOrder();
    }

    private int getOrderInput() {
        System.out.printf("%-10s %s%n", "1. 주문", "2. 메뉴판");
        int orderInput = scanner.nextInt();
        validateRange(orderInput, 0, ORDER_OPTION_NUM);
        return orderInput;
    }

    private void completeOrder() {
        displayDiscountInfo(); // 할인율 정보 출력
        int discountInput = scanner.nextInt();
        validateRange(discountInput, 0, DISCOUNT_OPTION_NUM);

        double price = applyDiscountForOrder(discountInput); // 할인률 적용 후 주문 처리
        System.out.printf("주문이 완료되었습니다. 금액은 W %.2f 입니다.\n", price);
        basket.clear();
    }

    private void validateRange(int input, int lowerBound, int upperBound) { // 입력 범위 검증
        if (input < lowerBound || input > upperBound) throw new IllegalArgumentException(
                String.format("입력값이 유효하지 않습니다. (%d ~ %d 사이의 값을 입력해주세요)", lowerBound, upperBound));

    }

    private boolean isOrderOnGoing(int input) { // 진행중인 주문을 취소할 때
        if (input < 0) throw new IllegalArgumentException(INVALID_OPTION_RANGE);
        if (input == ORDER_CANCEL) { // 주문 취소
            basket.clear(); // 장바구니 모두 비우기
            return false;
        }

        return input == GO_AHEAD_ORDER;
    }

    private void addToBasket(MenuItem menuItem, int input) { // 장바구니에 선택한 메뉴 아이템 추가
        if (input == CANCEL_ADD_TO_BASKET) return;
        if (input != AGREE_ADD_TO_BASKET) throw new IllegalArgumentException(INVALID_OPTION_RANGE);

        basket.put(menuItem, basket.getOrDefault(menuItem, 0) + 1); // 장바구니 추가
    }

    private double applyDiscountForOrder(int discountFlag) { // 할인율 적용
        return fromValue(discountFlag).applyDiscount(currBasketSum);
    }
}
