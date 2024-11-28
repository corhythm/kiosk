package me.dnr2144.challenge;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static me.dnr2144.util.ErrorMessage.INVALID_DISCOUNT_RATE;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Discount {
    NATIONAL_HERO(1, "국가유공자", 10),  // 국가유공자
    MILITARY_PERSONNEL(2, "군인", 15), // 군인
    STUDENT(3, "학생", 5),            // 학생
    GENERAL(4, "일반", 0);            // 일반

    private final int value;
    private final String discountTarget;
    private final int discountRate;

    public static Discount fromValue(int value) { // 할인 대상 찾기
        return Arrays.stream(values())
                .filter(d -> d.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DISCOUNT_RATE));
    }

    public double applyDiscount(double total) { // 할인 적용
        return total * (1 - this.discountRate / 100.0);
    }

    public static void displayDiscountInfo() { // 할인 정보 출력
        System.out.println("할인 정보를 입력해주세요.");
        Arrays.stream(Discount.values())
                .forEachOrdered(discount -> System.out.printf("%d. %-15s : %3d%%%n", discount.getValue(),
                        discount.getDiscountTarget(), discount.getDiscountRate()));
    }
}
