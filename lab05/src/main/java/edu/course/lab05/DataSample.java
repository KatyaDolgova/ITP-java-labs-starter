package edu.course.lab05;

public final class DataSample {
    private final String id;
    private final String label;
    private final String source;
    private final int featureCount;
    private final SampleStatus status;

    public DataSample(String id, String label, String source, int featureCount, SampleStatus status) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id не может быть null или пустым");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("label не может быть null или пустым");
        }
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("source не может быть null или пустым");
        }
        if (featureCount <= 0) {
            throw new IllegalArgumentException("featureCount должен быть положительным");
        }
        if (status == null) {
            throw new IllegalArgumentException("status не может быть null");
        }

        this.id = id;
        this.label = label;
        this.source = source;
        this.featureCount = featureCount;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public String getSource() {
        return this.source;
    }

    public int getFeatureCount() {
        return this.featureCount;
    }

    public SampleStatus getStatus() {
        return this.status;
    }
}
