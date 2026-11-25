package edu.course.lab06;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BoxTest {
    @Test
    void storesAndReplacesString() {
        Box<String> box = new Box<>("first");
        assertEquals("first", box.get());

        box.set("second");
        assertEquals("second", box.get());

    }

    @Test
    void storesAndReplacesInteger() {
        Box<Integer> box = new Box<>(10);
        assertEquals(10, box.get());

        box.set(20);
        assertEquals(20, box.get());
    }
}
