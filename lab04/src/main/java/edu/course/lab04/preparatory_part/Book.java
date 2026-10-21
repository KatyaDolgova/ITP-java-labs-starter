package edu.course.lab04.preparatory_part;

import java.util.Objects;

public final class Book {
    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("isbn не может быть null или пустым");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title не может быть null или пустым");
        }

        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Book other = (Book) o;
        return this.isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.isbn);
    }
}
