package edu.course.lab04.main_part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class SampleRegistryTest {

    private DataSample sample(String id) {
        return new DataSample(id, "setosa", SampleStatus.NEW, new double[] {5.1, 3.5});
    }

    @Test
    void newRegistry_isEmpty() {
        assertEquals(0, new SampleRegistry().size());
    }

    @Test
    void put_thenGet_returnsSameValue() {
        SampleRegistry registry = new SampleRegistry();
        DataSample value = sample("s1");

        assertNull(registry.put(new SampleId("iris", 1), value));
        assertSame(value, registry.get(new SampleId("iris", 1)));
        assertEquals(1, registry.size());
    }

    @Test
    void get_returnsNullForMissingKey() {
        assertNull(new SampleRegistry().get(new SampleId("iris", 1)));
    }

    @Test
    void contains_reflectsPresenceOfKey() {
        SampleRegistry registry = new SampleRegistry();
        registry.put(new SampleId("iris", 1), sample("s1"));

        assertTrue(registry.contains(new SampleId("iris", 1)));
        assertFalse(registry.contains(new SampleId("iris", 2)));
    }

    @Test
    void remove_deletesValueAndReturnsIt() {
        SampleRegistry registry = new SampleRegistry();
        DataSample value = sample("s1");
        registry.put(new SampleId("iris", 1), value);

        assertSame(value, registry.remove(new SampleId("iris", 1)));
        assertFalse(registry.contains(new SampleId("iris", 1)));
        assertEquals(0, registry.size());
    }

    @Test
    void remove_returnsNullForMissingKey() {
        assertNull(new SampleRegistry().remove(new SampleId("iris", 1)));
    }

    @Test
    void put_throwsForNullKeyOrValue() {
        SampleRegistry registry = new SampleRegistry();

        assertThrows(IllegalArgumentException.class, () -> registry.put(null, sample("s1")));
        assertThrows(IllegalArgumentException.class, () -> registry.put(new SampleId("iris", 1), null));
    }


    @Test
    void twoInstancesOfEqualKey_findSameValue() {
        SampleRegistry registry = new SampleRegistry();
        DataSample value = sample("s1");
        SampleId keyForPut = new SampleId("iris", 42);
        SampleId keyForGet = new SampleId("iris", 42);

        registry.put(keyForPut, value);

        assertSame(value, registry.get(keyForGet));
    }

    @Test
    void putWithEqualKey_replacesValueAndKeepsSize() {
        SampleRegistry registry = new SampleRegistry();
        DataSample oldValue = sample("old");
        DataSample newValue = sample("new");

        registry.put(new SampleId("iris", 1), oldValue);
        DataSample previous = registry.put(new SampleId("iris", 1), newValue);

        assertSame(oldValue, previous);
        assertSame(newValue, registry.get(new SampleId("iris", 1)));
        assertEquals(1, registry.size());
    }

    @Test
    void unequalKeysWithSameHashCode_keepDifferentValues() {
        SampleId first = new SampleId("Aa", 1);
        SampleId second = new SampleId("BB", 1);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, second);

        SampleRegistry registry = new SampleRegistry();
        DataSample firstValue = sample("first");
        DataSample secondValue = sample("second");
        registry.put(first, firstValue);
        registry.put(second, secondValue);

        assertEquals(2, registry.size());
        assertSame(firstValue, registry.get(new SampleId("Aa", 1)));
        assertSame(secondValue, registry.get(new SampleId("BB", 1)));
    }
}
