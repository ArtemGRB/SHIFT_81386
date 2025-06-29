package org;

import java.util.*;
import java.util.stream.Collectors;

public class StatService {

    DataBase dataBase;

    public StatService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void printShortStat(){

        System.out.println("\nКраткая статистика:");
        System.out.println("----------------------------------");

        if (!dataBase.getIntegerList().isEmpty()){
            System.out.println("Целые числа: " + dataBase.getIntegerList().size());
        }

        if (!dataBase.getFloatList().isEmpty()){
            System.out.println("Вещественные числа: " + dataBase.getFloatList().size());
        }

        if (!dataBase.getStringList().isEmpty()){
            System.out.println("Строки: " + dataBase.getStringList().size() + "\n\n");
        }
    }

    public void printFullStat(){
        System.out.println("\nПолная статистика:");
        System.out.println("----------------------------------");

        if (!dataBase.getIntegerList().isEmpty()){
            System.out.println("Целые числа: " + dataBase.getIntegerList().size());
            ArrayList<Long> arrayLongList = dataBase.getIntegerList().stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(ArrayList::new));
            System.out.println("  Минимальное: " + Collections.min(arrayLongList));
            System.out.println("  Максимальное: " + Collections.max(arrayLongList));
            System.out.println("  Сумма: " + arrayLongList.stream().mapToLong(Long::intValue).sum());
            System.out.println("  Среднее: "
                    + arrayLongList.stream().mapToLong(Long::intValue).average().orElse(0.0)
                    + "\n");

        }

        if (!dataBase.getFloatList().isEmpty()){
            System.out.println("Вещественные числа: " + dataBase.getFloatList().size());
            ArrayList<Double> arrayDoubleList = dataBase.getFloatList().stream()
                    .map(Double::parseDouble)
                    .collect(Collectors.toCollection(ArrayList::new));
            System.out.println("  Минимальное: " + Collections.min(arrayDoubleList));
            System.out.println("  Максимальное: " + Collections.max(arrayDoubleList));
            System.out.println("  Сумма: " + arrayDoubleList.stream().mapToDouble(Double::intValue).sum());
            System.out.println("  Среднее: "
                    + arrayDoubleList.stream().mapToDouble(Double::intValue).average().orElse(0.0)
                    + "\n");
        }

        if (!dataBase.getStringList().isEmpty()){
            System.out.println("Строки: " + dataBase.getStringList().size());
            System.out.println("  Минимальная длина: "
                    + Collections.max(dataBase.getStringList(), Comparator.comparingInt(String::length)).length());
            System.out.println("  Максимальная длина: "
                    + Collections.min(dataBase.getStringList(), Comparator.comparingInt(String::length)).length());
        }
    }

}
