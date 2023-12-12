package christmas.domain.model.classes.orderInfo;

@FunctionalInterface
public interface DiscountCriteriaChecker {
    boolean isSatisfied(int totalAmount);
}
