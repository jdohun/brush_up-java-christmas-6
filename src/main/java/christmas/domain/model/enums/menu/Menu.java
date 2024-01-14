package christmas.domain.model.enums.menu;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static christmas.domain.model.enums.menu.MenuCategory.*;
import static christmas.domain.model.enums.menu.MenuErrorMessage.ERROR_MENU_NOT_FOUND;

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
    CHAMPAGNE(BEVERAGE, "샴페인", 25_000);

    private final MenuCategory menuCategory;
    private final String name;
    private final int price;

    private static final Menu[] menus = Menu.values();

    Menu(MenuCategory menuCategory, String name, int price) {
        this.menuCategory = menuCategory;
        this.name = name;
        this.price = price;
    }

    public static boolean isExistentMenu(String menuName) {
        return Arrays.stream(menus)
                .anyMatch(menu -> menu.name.equals(menuName));
    }

    public static Menu findByName(String menuName) {
        return Arrays.stream(menus)
                .filter(menu -> menu.name.equals(menuName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(ERROR_MENU_NOT_FOUND.getMessage()));
    }

    public boolean isBeverage() {
        return this.menuCategory == BEVERAGE;
    }

    public boolean isMain() {
        return this.menuCategory == MAIN;
    }

    public boolean isDessert() {
        return this.menuCategory == DESSERT;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
