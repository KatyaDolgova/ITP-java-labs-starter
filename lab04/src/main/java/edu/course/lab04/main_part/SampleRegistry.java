package edu.course.lab04.main_part;

import java.util.HashMap;
import java.util.Map;

public class SampleRegistry {
    private final Map<SampleId, DataSample> samples = new HashMap<>();

    public DataSample put(SampleId id, DataSample sample) {
        if (id == null) {
            throw new IllegalArgumentException("id не может быть null");
        }
        if (sample == null) {
            throw new IllegalArgumentException("sample не может быть null");
        }
        return this.samples.put(id, sample);
    }

    public DataSample get(SampleId id) {
        return this.samples.get(id);
    }

    public DataSample remove(SampleId id) {
        return this.samples.remove(id);
    }

    public boolean contains(SampleId id) {
        return this.samples.containsKey(id);
    }

    public int size() {
        return this.samples.size();
    }
}
