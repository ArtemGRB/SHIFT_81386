package org.service;

import org.config.Config;
import org.repository.InMemoryRepository;
import org.enums.TypeData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class FileService {

    private final InMemoryRepository inMemoryRepository;
    private final Config config;

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?\\d+$");
    private static final Pattern FLOAT_PATTERN = Pattern.compile("^-?\\d+\\.\\d+(?:[Ee][-+]?\\d+)?$");

    public FileService(InMemoryRepository inMemoryRepository, Config config) {
        this.inMemoryRepository = inMemoryRepository;
        this.config = config;
    }

    public void process(String inputFiles) {
        processFiles(inputFiles);
        createOutputFolder();
        writeToFile(inMemoryRepository.getIntegerList(), TypeData.INTEGER);
        writeToFile(inMemoryRepository.getFloatList(), TypeData.FLOAT);
        writeToFile(inMemoryRepository.getStringList(), TypeData.STRING);
    }

    private void processFiles(String inputFile) {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (INTEGER_PATTERN.matcher(line).matches()) {
                    inMemoryRepository.getIntegerList().add(line);
                } else if (FLOAT_PATTERN.matcher(line).matches()) {
                    inMemoryRepository.getFloatList().add(line);
                } else {
                    inMemoryRepository.getStringList().add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: файл не найден - " + inputFile);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + inputFile + " - " + e.getMessage());
        }
    }

    private void writeToFile(List<String> list, TypeData typeData) {
        if (list.isEmpty()) {
            return;
        }
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
            System.err.println("Ошибка записи файла " + fileName + ":" + e.getMessage());
        }
    }

    private void createOutputFolder() {
        try {
            Path path = Paths.get(config.getOutputPath());
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при создании директории: " + e.getMessage() +
                    "Все файлы создадутся в корневой папке программы");
            config.setOutputPath(".");

        }
    }

    private String typeToString(TypeData typeData) {
        switch (typeData) {
            case INTEGER -> {
                return "integers.txt";
            }
            case FLOAT -> {
                return "floats.txt";
            }
            case STRING -> {
                return "strings.txt";
            }
            default -> throw new IllegalStateException("Неизвестное значение: " + typeData);
        }
    }
}
