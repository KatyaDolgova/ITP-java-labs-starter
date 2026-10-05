package edu.course.lab03.main_part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NormalizationTest {

    @Test
    void minMax_scalesValuesToZeroOneRange() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, strategy.normalize(new double[]{10.0, 20.0, 30.0}));
    }

    @Test
    void minMax_returnsZerosWhenAllValuesEqual() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        assertArrayEquals(new double[]{0.0, 0.0}, strategy.normalize(new double[]{5.0, 5.0}));
    }

    @Test
    void minMax_throwsForEmptyArray() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(new double[0]));
    }

    @Test
    void meanCentering_subtractsMean() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();

        assertArrayEquals(new double[]{-1.0, 0.0, 1.0}, strategy.normalize(new double[]{1.0, 2.0, 3.0}));
    }

    @Test
    void meanCentering_throwsForNullArray() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();

        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
    }
}
