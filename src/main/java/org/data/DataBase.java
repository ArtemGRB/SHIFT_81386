package org.data;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final List<String> integerList = new ArrayList<>();
    private final List<String> floatList = new ArrayList<>();
    private final List<String> stringList = new ArrayList<>();

    public List<String> getIntegerList() {return integerList;}
    public List<String> getFloatList() {return floatList;}
    public List<String> getStringList() {return stringList;}
}
