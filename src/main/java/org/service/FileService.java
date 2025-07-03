package org.service;

import org.config.Config;
import org.repository.InMemoryRepository;
import org.enums.TypeData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

    public void processWriteFiles() {
        createOutputFolder();
        writeToFile(inMemoryRepository.getIntegerList());
        writeToFile(inMemoryRepository.getFloatList());
        writeToFile(inMemoryRepository.getStringList());
    }



    public void processFiles(String inputFile) {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (INTEGER_PATTERN.matcher(line).matches()) {
                    inMemoryRepository.add(Long.parseLong(line));
                } else if (FLOAT_PATTERN.matcher(line).matches()) {
                    inMemoryRepository.add(Double.parseDouble(line));
                } else {
                    inMemoryRepository.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: файл не найден - " + inputFile);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + inputFile + " - " + e.getMessage());
        }
    }

    private <T> void writeToFile(List<T> list) {

        if (list.isEmpty()) {
            return;
        }

        final String nameFile = switch (list.get(0).getClass().getSimpleName()){
            case "Long"-> TypeData.INTEGER.getNameFile();
            case "Double" -> TypeData.FLOAT.getNameFile();
            case "String" -> TypeData.STRING.getNameFile();
            default -> throw new IllegalArgumentException("Неподдерживаемый тип");
        };

        String fileName = config.getOutputPath() +
                File.separator +
                config.getPrefix() +
                nameFile;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, config.isAppendMark()))) {
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toString());
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
}
