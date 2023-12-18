package christmas.domain.model.enums;

import java.util.Arrays;

public enum Badge {
    NONE("없음", 0, 5_000),
    STAR("별", 5_000, 10_000),
    TREE("트리", 10_000, 20_000),
    SANTA("산타", 20_000, Integer.MAX_VALUE);

    private static final Badge[] badges = Badge.values();

    private final String name;
    private final int minPrice;
    private final int maxPrice;

    Badge(String name, int minPrice, int maxPrice) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public static Badge getByTotalBenefitAmount(int totalBenefitAmount){
        return Arrays.stream(badges)
                .filter(badge -> badge.isWithinPriceRange(totalBenefitAmount))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isWithinPriceRange(int amount) {
        return amount >= minPrice && amount < maxPrice;
    }

    public String getName() {
        return name;
    }
}
