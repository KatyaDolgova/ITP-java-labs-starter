package edu.course.lab03.preparatory_part;

public class PriceCalculator {

    private final DiscountPolicy policy;

    public PriceCalculator(DiscountPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException("policy должен быть null");
        }

        this.policy = policy;
    }

    public double calculate(double price) {
        return policy.apply(price);
    }
}
