package domain.menu;

import java.util.List;

public enum MenuBoard {
    APPETIZER("애피타이저", List.of(Menu.PINE_MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN("메인", List.of(Menu.T_BONE_STEAK, Menu.BBQ_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", List.of(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    DRINK("음료", List.of(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE));

    private final String category;
    private final List<Menu> categoryMenus;

    MenuBoard(
            final String category,
            final List<Menu> categoryMenus
    ) {
        this.category = category;
        this.categoryMenus = categoryMenus;
    }

    public String getCategory() {
        return category;
    }

    public List<Menu> getCategoryMenus() {
        return categoryMenus.stream().toList();
    }

    public long countOverlap(final List<Menu> from) {
        return from.stream()
                .filter(categoryMenus::contains)
                .count();
    }

    public boolean hasMenu(Menu menu) {
        return categoryMenus.contains(menu);
    }
}
