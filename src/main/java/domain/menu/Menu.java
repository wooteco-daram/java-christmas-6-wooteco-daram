package domain.menu;

import domain.price.Price;
import exception.GlobalException;
import exception.ErrorMessage;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Menu {

    PINE_MUSHROOM_SOUP("양송이수프", Price.from(6_000)),
    TAPAS("타파스", Price.from(5_500)),
    CAESAR_SALAD("시저샐러드", Price.from(8_000)),
    T_BONE_STEAK("티본스테이크", Price.from(55_000)),
    BBQ_RIBS("바비큐립", Price.from(54_000)),
    SEAFOOD_PASTA("해산물파스타", Price.from(35_000)),
    CHRISTMAS_PASTA("크리스마스파스타", Price.from(25_000)),
    CHOCOLATE_CAKE("초코케이크", Price.from(15_000)),
    ICE_CREAM("아이스크림", Price.from(5_000)),
    ZERO_COKE("제로콜라", Price.from(3_000)),
    RED_WINE("레드와인", Price.from(60_000)),
    CHAMPAGNE("샴페인", Price.from(25_000));

    private static final Map<String, Menu> names = Arrays.stream(values()).collect(
            Collectors.toUnmodifiableMap(
                    Menu::getName,
                    Function.identity()
            )
    );

    private final String name;
    private final Price price;

    Menu(
            final String name,
            final Price price
    ) {
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
