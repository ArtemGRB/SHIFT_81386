package org.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryRepository {
    private final List<Long> integerList = new ArrayList<>();
    private final List<Double> floatList = new ArrayList<>();
    private final List<String> stringList = new ArrayList<>();

    public List<Long> getIntegerList() {
        return List.copyOf(integerList);
    }

    public List<Double> getFloatList() {
        return List.copyOf(floatList);
    }

    public List<String> getStringList() {
        return List.copyOf(stringList);
    }

    public <T> void add(T value) {
        if (value.getClass() == Long.class) {
            integerList.add((Long) value);
        } else if (value.getClass() == Double.class) {
            floatList.add((Double) value);
        } else {
            stringList.add((String) value);
        }
    }
}
