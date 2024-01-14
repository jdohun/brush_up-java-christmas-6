package christmas.domain.model.enums;

import christmas.domain.model.enums.menu.MenuCategory;

import static christmas.domain.model.enums.menu.Menu.CHAMPAGNE;
import static christmas.domain.model.enums.menu.MenuCategory.GIVEAWAY;

public enum Giveaway {
    GIVEAWAY_CHAMPAGNE(CHAMPAGNE.getName(), CHAMPAGNE.getPrice());

    private final MenuCategory menuCategory;
    private final String name;
    private final int price;

    Giveaway(String name, int price) {
        this.menuCategory = GIVEAWAY;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
