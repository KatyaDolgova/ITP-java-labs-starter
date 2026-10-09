package edu.course.lab03.main_part;

public class FeatureProcessor {

    private final NormalizationStrategy strategy;

    public FeatureProcessor(NormalizationStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("strategy должно быть не null");
        }

        this.strategy = strategy;
    }

    public double[] process(double[] vector) {
        return strategy.normalize(vector);
    }
}
