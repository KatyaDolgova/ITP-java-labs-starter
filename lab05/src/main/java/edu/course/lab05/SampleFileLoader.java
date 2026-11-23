package edu.course.lab05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class SampleFileLoader {
    private static final String SEPARATOR = ";";
    private static final String HEADER = "id;label;source;featureCount;status";
    private static final int FIELD_COUNT = 5;

    public LoadResult load(Path path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new DataLoadingException("Невозможно прочитать " + path, e);
        }

        if (lines.isEmpty()) {
            throw new DataLoadingException("Файл пуст, ожидался заголовок: " + HEADER, null);
        }
        String header = lines.get(0).trim();
        if (!HEADER.equals(header)) {
            throw new DataLoadingException("Неверный заголовок: " + header + ", ожидался: " + HEADER, null);
        }

        List<DataSample> samples = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank()) {
                continue;
            }
            DataSample sample = parseLine(line, i + 1, errors);
            if (sample != null) {
                samples.add(sample);
            }
        }

        return new LoadResult(samples, errors);
    }

    private DataSample parseLine(String line, int lineNumber, List<ValidationError> errors) {
        String[] parts = line.split(SEPARATOR, -1);
        if (parts.length != FIELD_COUNT) {
            errors.add(new ValidationError(lineNumber, "line",
                    "Ожидалось полей: " + FIELD_COUNT + ", получено: " + parts.length));
            return null;
        }

        String id = parts[0].trim();
        String label = parts[1].trim();
        String source = parts[2].trim();
        String countText = parts[3].trim();
        String statusText = parts[4].trim();

        int errorsBefore = errors.size();
        checkNotEmpty(id, "id", lineNumber, errors);
        checkNotEmpty(label, "label", lineNumber, errors);
        checkNotEmpty(source, "source", lineNumber, errors);

        int featureCount = 0;
        if (countText.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "featureCount", "Поле не может быть пустым"));
        } else {
            try {
                featureCount = Integer.parseInt(countText);
                if (featureCount <= 0) {
                    errors.add(new ValidationError(lineNumber, "featureCount",
                            "Число должно быть положительным: " + countText));
                }
            } catch (NumberFormatException e) {
                errors.add(new ValidationError(lineNumber, "featureCount", "Неверное число: " + countText));
            }
        }

        SampleStatus status = null;
        if (statusText.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "status", "Поле не может быть пустым"));
        } else {
            try {
                status = SampleStatus.valueOf(statusText);
            } catch (IllegalArgumentException e) {
                errors.add(new ValidationError(lineNumber, "status", "Неизвестный статус: " + statusText));
            }
        }

        if (errors.size() > errorsBefore) {
            return null;
        }
        return new DataSample(id, label, source, featureCount, status);
    }

    private void checkNotEmpty(String value, String field, int lineNumber, List<ValidationError> errors) {
        if (value.isEmpty()) {
            errors.add(new ValidationError(lineNumber, field, "Поле не может быть пустым"));
        }
    }

}
