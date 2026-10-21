package edu.course.lab04.main_part;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class MutableKeyExperimentTest {

    private static class MutableSampleId {
        private final String datasetName;
        private int observationNumber;

        MutableSampleId(String datasetName, int observationNumber) {
            this.datasetName = datasetName;
            this.observationNumber = observationNumber;
        }

        void setObservationNumber(int observationNumber) {
            this.observationNumber = observationNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MutableSampleId)) {
                return false;
            }
            MutableSampleId other = (MutableSampleId) o;
            return this.observationNumber == other.observationNumber
                    && this.datasetName.equals(other.datasetName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.datasetName, this.observationNumber);
        }
    }

    @Test
    void changingKeyFieldAfterPut_breaksLookup() {
        Map<MutableSampleId, String> map = new HashMap<>();
        MutableSampleId key = new MutableSampleId("iris", 1);
        map.put(key, "value");
        assertEquals("value", map.get(key));

        key.setObservationNumber(2);

        assertNull(map.get(key));
        assertFalse(map.containsKey(key));

        assertNull(map.get(new MutableSampleId("iris", 1)));

        assertEquals(1, map.size());
    }
}
