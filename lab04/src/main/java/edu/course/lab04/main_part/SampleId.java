package edu.course.lab04.main_part;

import java.util.Objects;

public final class SampleId {
    private final String datasetName;
    private final int observationNumber;

    public SampleId(String datasetName, int observationNumber) {
        if (datasetName == null || datasetName.isEmpty()) {
            throw new IllegalArgumentException("datasetName не может быть null или пустым");
        }
        if (observationNumber < 0) {
            throw new IllegalArgumentException("observationNumber не может быть отрицательным");
        }

        this.datasetName = datasetName;
        this.observationNumber = observationNumber;
    }

    public String getDatasetName() {
        return this.datasetName;
    }

    public int getObservationNumber() {
        return this.observationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        SampleId other = (SampleId) o;
        return this.observationNumber == other.observationNumber
                && this.datasetName.equals(other.datasetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.datasetName, this.observationNumber);
    }
}
