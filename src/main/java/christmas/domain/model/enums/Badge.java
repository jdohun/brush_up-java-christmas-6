package christmas.domain.model.enums;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String name;
    private final int condition;

    Badge(String name, int condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Badge getByTotalBenefitAmount(int totalBenefitAmount){
        return Arrays.stream(values())
                .filter(badge -> badge.condition <= totalBenefitAmount)
                .findFirst()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
