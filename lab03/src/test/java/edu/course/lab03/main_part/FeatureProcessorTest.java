package edu.course.lab03.main_part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FeatureProcessorTest {

    @Test
    void process_usesInjectedStrategy() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());

        assertArrayEquals(new double[]{0.0, 1.0}, processor.process(new double[]{2.0, 4.0}));
    }

    @Test
    void process_doesNotModifyInputArray() {
        double[] input = {1.0, 2.0, 3.0};
        FeatureProcessor processor = new FeatureProcessor(new MeanCenteringNormalization());

        processor.process(input);

        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, input);
    }

    @Test
    void replacingStrategyChangesResultWithoutChangingProcessor() {
        double[] vector = {1.0, 2.0, 3.0};

        FeatureProcessor minMax = new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor centering = new FeatureProcessor(new MeanCenteringNormalization());

        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, minMax.process(vector));
        assertArrayEquals(new double[]{-1.0, 0.0, 1.0}, centering.process(vector));
    }

    @Test
    void constructor_throwsForNullStrategy() {
        assertThrows(IllegalArgumentException.class, () -> new FeatureProcessor(null));
    }
}
