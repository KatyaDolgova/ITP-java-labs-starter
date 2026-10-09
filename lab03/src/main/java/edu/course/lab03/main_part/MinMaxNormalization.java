package edu.course.lab03.main_part;

public class MinMaxNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values не должно быть null или empty");
        }

        double min = values[0];
        double max = values[0];
        for (double value : values) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        double[] result = new double[values.length];
        double range = max - min;
        for (int i = 0; i < values.length; i++) {
            result[i] = range == 0 ? 0.0 : (values[i] - min) / range;
        }

        return result;
    }
}
