package org.service;

import org.config.Config;
import org.data.DataBase;
import org.TypeData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class FileService {

    private DataBase dataBase;
    private Config config;

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?\\d+$");
    private static final Pattern FLOAT_PATTERN = Pattern.compile("^-?\\d+\\.\\d+(?:[Ee][-+]?\\d+)?$");

    public FileService(DataBase dataBase, Config config) {
        this.dataBase = dataBase;
        this.config = config;
    }

    public void process(String inputFiles) throws IOException {
        processFiles(inputFiles);
        createOutputFolder();
        writeToFile(dataBase.getIntegerList(), TypeData.INTEGER);
        writeToFile(dataBase.getFloatList(), TypeData.FLOAT);
        writeToFile(dataBase.getStringList(), TypeData.STRING);
    }

    private void processFiles(String inputFiles) throws FileNotFoundException {
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
        if(list.isEmpty()){return;}
        String fileName = config.getOutputPath() +
                File.separator +
                config.getPrefix() +
                typeToString(typeData);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, config.isAppendMark()))) {
            for (String string : list) {
                writer.write(string);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createOutputFolder() throws IOException {
        Path path = Paths.get(config.getOutputPath());
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
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
