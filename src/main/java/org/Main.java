package org;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Config config = new Config();
        DataBase dataBase = new DataBase();
        FileService fileService = new FileService(dataBase, config);
        StatService statService = new StatService(dataBase);

        for (int i = 0; i < args.length; i++) {

            if (args[i].startsWith("-")) {
                switch (args[i]) {
                    case "-o":
                        i++;
                        config.setOutputPath(args[i]);
                        break;
                    case "-p":
                        i++;
                        config.setPrefix(args[i]);
                        break;
                    case "-s":
                        config.setShortStatistic(true);
                        break;
                    case "-f":
                        config.setFullStatistic(true);
                        break;
                    case "-a":
                        config.setAppendMark(true);
                        break;
                    default:
                        System.err.println("Неизвестная опция: " + args[i]);
                        break;
                }
            } else config.getInputFiles().add(args[i]);
        }


        try {
            for (String fileName: config.getInputFiles()) {
                fileService.process(fileName);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (config.isShortStatistic()){
            statService.printShortStat();
        }

        if (config.isFullStatistic()){
            statService.printFullStat();
        }
    }
}