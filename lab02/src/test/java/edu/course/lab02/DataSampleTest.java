package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DataSampleTest {

    @Test
    void constructor_createsValidObject() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1.0, 2.0, 3.0});

        assertEquals("s1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.NEW, sample.getStatus());
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, sample.getFeatures());
    }

    @Test
    void constructor_throwsForNullId() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample(null, "cat", SampleStatus.NEW, new double[]{1.0}));
    }

    @Test
    void constructor_throwsForEmptyId() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("", "cat", SampleStatus.NEW, new double[]{1.0}));
    }

    @Test
    void constructor_throwsForNullLabel() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", null, SampleStatus.NEW, new double[]{1.0}));
    }

    @Test
    void constructor_throwsForEmptyLabel() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "", SampleStatus.NEW, new double[]{1.0}));
    }

    @Test
    void constructor_throwsForNullStatus() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", null, new double[]{1.0}));
    }

    @Test
    void constructor_throwsForNullFeatures() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", SampleStatus.NEW, null));
    }

    @Test
    void constructor_throwsForEmptyFeatures() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", SampleStatus.NEW, new double[0]));
    }

    @Test
    void constructor_throwsForNanFeature() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1.0, Double.NaN}));
    }

    @Test
    void constructor_throwsForInfiniteFeature() {
        assertThrows(IllegalArgumentException.class,
            () -> new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1.0, Double.POSITIVE_INFINITY}));
    }

    @Test
    void changeStatus_updatesStatus() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1.0});

        sample.changeStatus(SampleStatus.READY);

        assertEquals(SampleStatus.READY, sample.getStatus());
    }

    @Test
    void changeStatus_throwsForNull() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1.0});

        assertThrows(IllegalArgumentException.class, () -> sample.changeStatus(null));
    }

    @Test
    void isReady_returnsTrueForReadyStatus() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.READY, new double[]{1.0});

        assertTrue(sample.isReady());
    }

    @Test
    void isReady_returnsFalseForNonReadyStatus() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1.0});

        assertFalse(sample.isReady());
    }

    @Test
    void averageFeatures_calculatesAverage() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{2.0, 4.0, 6.0});

        assertEquals(4.0, sample.averageFeatures());
    }

    @Test
    void constructorArray_mutationDoesNotAffectObject() {
        double[] original = {1.0, 2.0, 3.0};
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, original);

        original[0] = 999.0;

        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, sample.getFeatures());
    }

    @Test
    void getFeatures_mutationDoesNotAffectObject() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1.0, 2.0, 3.0});

        double[] returned = sample.getFeatures();
        returned[0] = 999.0;

        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, sample.getFeatures());
    }
}
