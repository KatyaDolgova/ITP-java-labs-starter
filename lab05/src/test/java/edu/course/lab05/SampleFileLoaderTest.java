package edu.course.lab05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SampleFileLoaderTest {
    private static final String HEADER = "id;label;source;featureCount;status";

    @TempDir
    Path tempDir;

    private final SampleFileLoader loader = new SampleFileLoader();

    private Path writeFile(String... lines) throws IOException {
        Path file = this.tempDir.resolve("samples.txt");
        Files.write(file, List.of(lines), StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void loadsValidFile() throws IOException {
        Path file = writeFile(HEADER, "S-101;cat;camera-1;128;READY", "S-102;dog;camera-2;64;NEW");

        LoadResult result = this.loader.load(file);

        assertEquals(2, result.getSamples().size());
        assertFalse(result.hasErrors());
        DataSample first = result.getSamples().get(0);
        assertEquals("S-101", first.getId());
        assertEquals("cat", first.getLabel());
        assertEquals("camera-1", first.getSource());
        assertEquals(128, first.getFeatureCount());
        assertEquals(SampleStatus.READY, first.getStatus());
    }

    @Test
    void loadsUnicodeText() throws IOException {
        Path file = writeFile(HEADER, "S-201;кошка;камера-1;32;PROCESS");

        LoadResult result = this.loader.load(file);

        assertEquals(1, result.getSamples().size());
        assertEquals("кошка", result.getSamples().get(0).getLabel());
        assertEquals("камера-1", result.getSamples().get(0).getSource());
    }

    @Test
    void reportsEmptyField() throws IOException {
        Path file = writeFile(HEADER, "S-101;;camera-1;128;READY");

        LoadResult result = this.loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals(2, result.getErrors().get(0).getLineNumber());
        assertEquals("label", result.getErrors().get(0).getField());
    }

    @Test
    void reportsInvalidNumber() throws IOException {
        Path file = writeFile(HEADER, "S-101;cat;camera-1;many;READY");

        LoadResult result = this.loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals("featureCount", result.getErrors().get(0).getField());
    }

    @Test
    void reportsUnknownStatus() throws IOException {
        Path file = writeFile(HEADER, "S-101;cat;camera-1;128;BROKEN");

        LoadResult result = this.loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals("status", result.getErrors().get(0).getField());
    }

    @Test
    void reportsWrongColumnCount() throws IOException {
        Path file = writeFile(HEADER, "S-101;cat;camera-1;128", "S-102;cat;camera-1;128;READY;extra");

        LoadResult result = this.loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(2, result.getErrors().size());
        assertEquals(2, result.getErrors().get(0).getLineNumber());
        assertEquals(3, result.getErrors().get(1).getLineNumber());
    }

    @Test
    void keepsValidRowsNextToInvalidOnes() throws IOException {
        Path file = writeFile(
                HEADER,
                "S-101;cat;camera-1;128;READY",
                "S-102;dog;camera-2;abc;NEW",
                "",
                "S-103;bird;camera-3;16;PROCESS",
                "S-104;fox;camera-4;8;WRONG");

        LoadResult result = this.loader.load(file);

        assertEquals(2, result.getSamples().size());
        assertEquals("S-101", result.getSamples().get(0).getId());
        assertEquals("S-103", result.getSamples().get(1).getId());
        assertEquals(2, result.getErrors().size());
        assertEquals(3, result.getErrors().get(0).getLineNumber());
        assertEquals(6, result.getErrors().get(1).getLineNumber());
    }

    @Test
    void wrongHeaderIsSourceError() throws IOException {
        Path file = writeFile("id;name;source;featureCount;status", "S-101;cat;camera-1;128;READY");

        assertThrows(DataLoadingException.class, () -> this.loader.load(file));
    }

    @Test
    void missingFileKeepsCause() {
        Path file = this.tempDir.resolve("missing.txt");

        DataLoadingException e = assertThrows(DataLoadingException.class, () -> this.loader.load(file));

        assertInstanceOf(NoSuchFileException.class, e.getCause());
    }
}
