package org;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class FileService {

    DataBase dataBase;

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?\\d+$");
    private static final Pattern FLOAT_PATTERN = Pattern.compile("^-?\\d+\\.\\d+(?:[Ee][-+]?\\d+)?$");

    public FileService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public  void processing(String inputFiles) throws FileNotFoundException {
        filesProcessing(inputFiles);
        writeToFile(dataBase.getIntegerList(),TypeData.INTEGER);
        writeToFile(dataBase.getFloatList(), TypeData.FLOAT);
        writeToFile(dataBase.getStringList(),TypeData.STRING);
    }

    private void filesProcessing(String inputFiles) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFiles))) {
            String string;
            while ((string = reader.readLine()) != null) {
                if (INTEGER_PATTERN.matcher(string).matches()) {
                    dataBase.getIntegerList().add(string);
                } else if (FLOAT_PATTERN.matcher(string).matches()) {
                    dataBase.getFloatList().add(string);
                } else {
                    dataBase.getStringList().add(string);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(List<String> list, TypeData typeData) {
        String fileName = typeToString(typeData);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));) {
            for (String string : list) {
                writer.write(string);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile() {

    }

    private String typeToString(TypeData typeData) {
        switch (typeData) {
            case INTEGER:
                return "integers.txt";
            case FLOAT:
                return "floats.txt";
            case STRING:
                return "strings.txt";
            default:
                throw new IllegalStateException("Unexpected value: " + typeData);
        }
    }


}
