package org;

import org.box.ServiceBox;
import org.config.Config;
import org.repository.InMemoryRepository;
import org.service.FileService;
import org.service.StatService;


public class Main {
    public static void main(String[] args) {

        ServiceBox serviceBox = new ServiceBox();

//        Config config = new Config();
//        InMemoryRepository inMemoryRepository = new InMemoryRepository();
//        FileService fileService = new FileService(inMemoryRepository, config);
//        StatService statService = new StatService(inMemoryRepository);

        parseArguments(args, serviceBox.get(Config.class));

        processFiles(serviceBox.get(Config.class), serviceBox.get(FileService.class));

        printStatistics(serviceBox.get(Config.class), serviceBox.get(StatService.class));

    }

    private static void parseArguments(String[] args, Config config) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                switch (args[i]) {
                    case "-o":
                        if (i + 1 >= args.length) {
                            System.err.println("Отсутствует значение для опции -o");
                        }
                        config.setOutputPath(args[++i]);
                        break;
                    case "-p":
                        if (i + 1 >= args.length) {
                            System.err.println("Отсутствует значение для опции -p");
                            break;
                        }
                        config.setPrefix(args[++i]);
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
                        throw new IllegalArgumentException("Неизвестная опция: " + args[i]);
                }
            } else {
                config.getInputFiles().add(args[i]);
            }
        }
    }

    private static void processFiles(Config config, FileService fileService) {
        for (String fileName : config.getInputFiles()) {
            fileService.process(fileName);
        }
    }

    private static void printStatistics(Config config, StatService statService) {
        if (config.isShortStatistic()) {
            statService.printShortStat();
        }

        if (config.isFullStatistic()) {
            statService.printFullStat();
        }
    }
}