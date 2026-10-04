package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DiscountPolicyTest {

    @Test
    void noDiscount_returnsOriginalPrice() {
        DiscountPolicy policy = new NoDiscount();

        assertEquals(100.0, policy.apply(100.0));
    }

    @Test
    void percentDiscount_appliesPercentToPrice() {
        DiscountPolicy policy = new PercentDiscount(20);

        assertEquals(80.0, policy.apply(100.0));
    }

    @Test
    void percentDiscount_throwsForPercentOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-1));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(101));
    }

    @Test
    void policies_throwForNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new NoDiscount().apply(-1.0));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(20).apply(-1.0));
    }

    @Test
    void priceCalculator_replacingPolicyChangesResult() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.calculate(100.0));

        PriceCalculator withDiscount = new PriceCalculator(new PercentDiscount(20));
        assertEquals(80.0, withDiscount.calculate(100.0));
    }

    @Test
    void priceCalculator_throwsForNullPolicy() {
        assertThrows(IllegalArgumentException.class, () -> new PriceCalculator(null));
    }
}
