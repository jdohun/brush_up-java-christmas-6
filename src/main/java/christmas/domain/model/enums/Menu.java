package christmas.domain.model.enums;

import static christmas.domain.model.enums.MenuCategory.*;

public enum Menu {
    BUTTON_MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BARBECUE_RIBS(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),

    ZERO_COKE(BEVERAGE, "제로콜라", 3_000),
    RED_WINE(BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(BEVERAGE, "샴페인", 25_000),

    GIVEAWAY_NONE(GIVEAWAY, "없음", 0),
    GIVEAWAY_CHAMPAGNE(GIVEAWAY, CHAMPAGNE.getName(), CHAMPAGNE.getPrice());

    private final MenuCategory menuCategory;
    private final String name;
    private final int price;

    Menu(MenuCategory menuCategory, String name, int price) {
        this.menuCategory = menuCategory;
        this.name = name;
        this.price = price;
    }

    public MenuCategory getMenuCategory() {
        return this.menuCategory;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
