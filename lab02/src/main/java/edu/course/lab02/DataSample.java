package edu.course.lab02;

import java.util.Arrays;

public class DataSample {
    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, SampleStatus status, double[] features) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id не может быть null или пустым");
        }
        if (label == null || label.isEmpty()) {
            throw new IllegalArgumentException("label не может быть null или пустым");
        }
        if (status == null) {
            throw new IllegalArgumentException("status не может быть null");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("features не может быть null или пустым");
        }
        for (double feature : features) {
            if (Double.isNaN(feature) || Double.isInfinite(feature)) {
                throw new IllegalArgumentException("features должны быть конечными числами");
            }
        }

        this.id = id;
        this.label = label;
        this.status = status;
        this.features = Arrays.copyOf(features, features.length);
    }

    public String getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public SampleStatus getStatus() {
        return this.status;
    }

    public double[] getFeatures() {
        return Arrays.copyOf(this.features, this.features.length);
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("status не может быть null");
        }

        this.status = newStatus;
    }

    public boolean isReady() {
        return this.status == SampleStatus.READY;
    }

    public double averageFeatures() {
        double sum = 0;
        for (double feature : this.features) {
            sum += feature;
        }
        return sum / this.features.length;
    }
}
