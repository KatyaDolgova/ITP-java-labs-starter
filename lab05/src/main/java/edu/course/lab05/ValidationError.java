
package edu.course.lab05;

public final class ValidationError {
    private final int lineNumber;
    private final String field;
    private final String message;

    public ValidationError(int lineNumber, String field, String message) {
        this.lineNumber = lineNumber;
        this.field = field;
        this.message = message;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public String getField() {
        return this.field;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "Строка " + this.lineNumber + ", поле " + this.field + ": " + this.message;
    }
}
