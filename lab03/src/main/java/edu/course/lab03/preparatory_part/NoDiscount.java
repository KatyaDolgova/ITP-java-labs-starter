package edu.course.lab03.preparatory_part;

public class NoDiscount implements DiscountPolicy {

    @Override
    public double apply(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price должен быть опложительным");
        }

        return price;
    }
}
