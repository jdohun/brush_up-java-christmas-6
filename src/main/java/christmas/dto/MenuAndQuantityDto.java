package christmas.dto;

import christmas.domain.model.enums.menu.Menu;

public record MenuAndQuantityDto(
        Menu menu,
        int quantity
) {
}
