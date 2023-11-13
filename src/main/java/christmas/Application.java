package christmas;

import camp.nextstep.edu.missionutils.Console;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Application {
    private static final Map<String, Map<String, Integer>> menuBoard = Map.of(
            "에피타이저",
            Map.of(
                    "양송이수프",
                    6000,
                    "타파스",
                    5500,
                    "시저샐러드",
                    8000
            ),
            "메인",
            Map.of(
                    "티본스테이크",
                    55000,
                    "바비큐립",
                    54000,
                    "해산물파스타",
                    35000,
                    "크리스마스파스타",
                    25000
            ),
            "디저트",
            Map.of(
                    "초코케이크",
                    15000,
                    "아이스크림",
                    5000
            ),
            "음료",
            Map.of(
                    "제로콜라",
                    3000,
                    "레드와인",
                    60000,
                    "샴페인",
                    25000
            )
    );

    private static final Map<String, Integer> allMenus = menuBoard.entrySet()
            .stream()
            .flatMap(s -> s.getValue().entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        final int reservationDay = readReservationDay();

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        Map<String, Integer> userMenu = getUserMenu();

        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

        System.out.println();

        System.out.println("<주문 메뉴>");
        userMenu.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        });

        System.out.println();

        System.out.println("<할인 전 총주문 금액>");
        final long totalPrice = getTotalPrice(userMenu);
        System.out.printf("%,d원\n", totalPrice);

        System.out.println();

        System.out.println("<증정 메뉴>");
        if (totalPrice >= 120_000) {
            System.out.println("샴페인 1개");
        } else {
            System.out.println("없음");
        }

        System.out.println();

        System.out.println("<혜택 내역>");

        long totalDiscount = 0;

        long christmasDiscount = 0;
        if (reservationDay <= 25) {
            christmasDiscount += 1_000L + (reservationDay - 1) * 100L;
        }

        final LocalDate date = LocalDate.of(2023, 12, reservationDay);
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        long totalWeekDay = 0;

        if (List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY).contains(dayOfWeek)) {
            long countDessert = userMenu.entrySet().stream()
                    .filter(s -> menuBoard.get("디저트").containsKey(s.getKey()))
                    .count();
            totalWeekDay = 2_023 * countDessert;
        }

        long totalWeekEnd = 0;

        if (List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY).contains(dayOfWeek)) {
            long countDessert = userMenu.entrySet().stream()
                    .filter(s -> menuBoard.get("메인").containsKey(s.getKey()))
                    .count();
            totalWeekEnd = 2_023 * countDessert;
        }

        long specialDiscount = 0;
        if (List.of(3,10,17,24,25,31).contains(reservationDay)) {
            specialDiscount = 1000L;
        }

        long giveEvent = 0;
        if (totalPrice >= 120_000) {
            giveEvent = allMenus.get("샴페인").longValue();
        }

        totalDiscount = christmasDiscount + totalWeekDay + totalWeekEnd + giveEvent;

        if (totalDiscount == 0) {
            System.out.println("없음");
        } else {
            if (christmasDiscount > 0) {
                System.out.printf("크리스마스 디데이 할인: -%,d%n", christmasDiscount);
            }
            if (totalWeekDay > 0) {
                System.out.printf("평일 할인: -%,d%n", totalWeekDay);
            }
            if (totalWeekEnd > 0) {
                System.out.printf("주말 할인: -%,d%n", totalWeekEnd);
            }
            if (specialDiscount > 0) {
                System.out.printf("특별 할인: -%,d%n", specialDiscount);
            }
            if (giveEvent > 0) {
                System.out.printf("증정 이벤트: -%,d%n", giveEvent);
            }
        }

        System.out.println();

        System.out.println("<총혜택 금액>");
        System.out.printf("-%,d\n", totalDiscount);

        System.out.println();

        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("-%,d\n", totalPrice - totalDiscount);

        System.out.println();

        System.out.println("<12월 이벤트 배지>");
        if (totalDiscount >= 20_000) {
            System.out.println("산타");
        } else if (totalDiscount >= 10_000) {
            System.out.println("트리");
        } else if (totalDiscount >= 5_000) {
            System.out.println("별");
        } else {
            System.out.println("없음");
        }

        Console.close();
    }

    private static long getTotalPrice(Map<String, Integer> userMenu) {
        AtomicLong totalPrice = new AtomicLong();

        userMenu.entrySet().forEach(entry -> {
            totalPrice.addAndGet((long) allMenus.get(entry.getKey()) * entry.getValue());
        });

        return totalPrice.get();
    }

    private static int readReservationDay() {
        final String reservationDayString = Console.readLine();
        try {
            final int reservationDay =  Integer.parseInt(reservationDayString);

            if (reservationDay < 1 || reservationDay > 31) {
                throw new IllegalArgumentException();
            }

            return reservationDay;
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            return readReservationDay();
        }
    }

    private static Map<String, Integer> getUserMenu() {
        final String userMenuString = Console.readLine();
        final List<String> userMenus = List.of(userMenuString.trim().split(","));

        long totalMenuCount = 0;
        Set<String> menuSet = new HashSet<>();

        Map<String, Integer> result = new HashMap<>();

        for (String s : userMenus) {
            final List<String> menuAndCount = List.of(s.split("-"));
            if (menuAndCount.size() != 2) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                return getUserMenu();
            }

            final String menuName = menuAndCount.get(0).trim();
            menuSet.add(menuName);
            totalMenuCount++;

            final String menuCountString = menuAndCount.get(1).trim();

            final boolean hasMenu = allMenus.containsKey(menuName);
            if (!hasMenu) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                return getUserMenu();
            }

            try {
                final int menuCount = Integer.parseInt(menuCountString);

                if (menuCount < 1) {
                    throw new IllegalArgumentException();
                }

                result.put(menuName, menuCount);
            } catch (IllegalArgumentException exception) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                return getUserMenu();
            }
        }

        if (totalMenuCount != menuSet.size()) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            return getUserMenu();
        }

        return result;
    }
}
