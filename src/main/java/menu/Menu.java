package menu;

import exception.GlobalException;
import exception.ErrorMessage;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Menu {

    PINE_MUSHROOM_SOUP("양송이수프", new Price(6_000)),
    TAPAS("타파스", new Price(5_500)),
    CAESAR_SALAD("시저샐러드", new Price(8_000)),
    T_BONE_STEAK("티본스테이크", new Price(55_000)),
    BBQ_RIBS("바비큐립", new Price(54_000)),
    SEAFOOD_PASTA("해산물파스타", new Price(35_000)),
    CHRISTMAS_PASTA("크리스마스파스타", new Price(25_000)),
    CHOCOLATE_CAKE("초코케이크", new Price(15_000)),
    ICE_CREAM("아이스크림", new Price(5_000)),
    ZERO_COKE("제로콜라", new Price(3_000)),
    RED_WINE("레드와인", new Price(60_000)),
    CHAMPAGNE("샴페인", new Price(25_000));

    private static final Map<String, Menu> names = Arrays.stream(values()).collect(
            Collectors.toUnmodifiableMap(
                    Menu::getName,
                    Function.identity()
            )
    );

    private final String name;
    private final Price price;

    Menu(final String name, final Price price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public static Menu findByName(final String name) {
        return Optional.ofNullable(names.get(name))
                .orElseThrow(() -> GlobalException.from(ErrorMessage.INVALID_MENU));
    }
}
