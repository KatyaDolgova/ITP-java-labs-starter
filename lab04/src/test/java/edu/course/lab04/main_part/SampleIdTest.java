package edu.course.lab04.main_part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class SampleIdTest {

    @Test
    void equalKeys_areDifferentObjectsButLogicallyEqual() {
        SampleId first = new SampleId("iris", 7);
        SampleId second = new SampleId("iris", 7);

        assertNotSame(first, second);
        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void keysDifferingInOneField_areNotEqual() {
        assertNotEquals(new SampleId("iris", 7), new SampleId("iris", 8));
        assertNotEquals(new SampleId("iris", 7), new SampleId("wine", 7));
    }

    @Test
    void equals_returnsFalseForNullAndOtherType() {
        SampleId id = new SampleId("iris", 7);

        assertFalse(id.equals(null));
        assertFalse(id.equals("iris#7"));
        assertFalse(id.equals(7));
    }

    @Test
    void equals_isReflexive() {
        SampleId id = new SampleId("iris", 7);

        assertTrue(id.equals(id));
    }

    @Test
    void constructor_throwsForInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> new SampleId(null, 1));
        assertThrows(IllegalArgumentException.class, () -> new SampleId("", 1));
        assertThrows(IllegalArgumentException.class, () -> new SampleId("iris", -1));
    }
}
