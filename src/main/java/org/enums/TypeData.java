package org.enums;

public enum TypeData {
    INTEGER("integers.txt"),
    FLOAT("floats.txt"),
    STRING("strings.txt");

    private final String nameFile;

    TypeData(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getNameFile() {
        return nameFile;
    }
}
