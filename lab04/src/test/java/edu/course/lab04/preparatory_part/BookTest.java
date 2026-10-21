package edu.course.lab04.preparatory_part;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void hashMap_findsValueByAnotherInstanceWithSameIsbn() {
        Map<Book, String> shelves = new HashMap<>();
        Book original = new Book("978-5-17-090630-7", "Java. Полное руководство");
        shelves.put(original, "Полка 3");

        Book sameIsbn = new Book("978-5-17-090630-7", "Java. Полное руководство");

        assertNotSame(original, sameIsbn);
        assertEquals("Полка 3", shelves.get(sameIsbn));
    }

    @Test
    void booksWithDifferentIsbn_areNotEqual() {
        Book first = new Book("978-5-17-090630-7", "Java. Полное руководство");
        Book second = new Book("978-5-4461-1157-8", "Java. Полное руководство");

        assertNotEquals(first, second);
    }

    @Test
    void booksWithSameIsbnAndDifferentTitle_areEqualAndHaveSameHashCode() {
        Book first = new Book("978-5-17-090630-7", "Java. Полное руководство");
        Book second = new Book("978-5-17-090630-7", "Другое название");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void constructor_throwsForEmptyFields() {
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "Title"));
        assertThrows(IllegalArgumentException.class, () -> new Book("", "Title"));
        assertThrows(IllegalArgumentException.class, () -> new Book("123", null));
    }
}
