package edu.course.lab03;

public class PercentDiscount implements DiscountPolicy {

    private final double percent;

    public PercentDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent должен быть от 0 до 100");
        }

        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price должен быть положительным");
        }

        return price * (100 - percent) / 100;
    }
}
