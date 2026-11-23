package edu.course.lab05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class TempFilePreparationTest {
    @TempDir
    Path tempDir;

    @Test
    void readsAllLinesFromUtf8File() throws IOException {
        Path file = this.tempDir.resolve("numbers.txt");
        Files.write(file, List.of("10", "bad", "20"), StandardCharsets.UTF_8);

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        assertEquals(List.of("10", "bad", "20"), lines);
    }

    @Test
    void badLineCausesNumberFormatException() throws IOException {
        Path file = this.tempDir.resolve("numbers.txt");
        Files.write(file, List.of("10", "bad", "20"), StandardCharsets.UTF_8);
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        List<Integer> numbers = new ArrayList<>();
        assertThrows(NumberFormatException.class, () -> {
            for (String line : lines) {
                numbers.add(Integer.parseInt(line));
            }
        });
        assertEquals(List.of(10), numbers);
    }
}
