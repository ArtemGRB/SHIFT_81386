package org;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        FileService fileService = new FileService(dataBase);
        try {
            fileService.processing("in1.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}