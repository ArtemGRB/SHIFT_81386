package org.service;

import org.repository.InMemoryRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class StatService {

    private final int SCALE = 3;
    private final InMemoryRepository inMemoryRepository;

    public StatService(InMemoryRepository inMemoryRepository) {
        this.inMemoryRepository = inMemoryRepository;
    }

    public void printShortStat() {

        System.out.println("\nКраткая статистика:");
        System.out.println("----------------------------------");

        if (!inMemoryRepository.getIntegerList().isEmpty()) {
            System.out.println("Целые числа: " + inMemoryRepository.getIntegerList().size());
        }

        if (!inMemoryRepository.getFloatList().isEmpty()) {
            System.out.println("Вещественные числа: " + inMemoryRepository.getFloatList().size());
        }

        if (!inMemoryRepository.getStringList().isEmpty()) {
            System.out.println("Строки: " + inMemoryRepository.getStringList().size() + "\n\n");
        }
    }

    public void printFullStat() {
        System.out.println("\nПолная статистика:");
        System.out.println("----------------------------------");

        if (!inMemoryRepository.getIntegerList().isEmpty()) {
            System.out.println("Целые числа: " + inMemoryRepository.getIntegerList().size());
            ArrayList<Long> arrayLongList = inMemoryRepository.getIntegerList().stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(ArrayList::new));
            System.out.println("  Минимальное: " + Collections.min(arrayLongList));
            System.out.println("  Максимальное: " + Collections.max(arrayLongList));
            System.out.println("  Сумма: " + calculateSum(inMemoryRepository.getIntegerList()));
            System.out.println("  Среднее: " + calculateAverage(inMemoryRepository.getIntegerList()));
        }

        if (!inMemoryRepository.getFloatList().isEmpty()) {
            System.out.println("Вещественные числа: " + inMemoryRepository.getFloatList().size());
            ArrayList<Double> arrayDoubleList = inMemoryRepository.getFloatList().stream()
                    .map(Double::parseDouble)
                    .collect(Collectors.toCollection(ArrayList::new));
            System.out.println("  Минимальное: " + Collections.min(arrayDoubleList));
            System.out.println("  Максимальное: " + Collections.max(arrayDoubleList));
            System.out.println("  Сумма: " + calculateSum(inMemoryRepository.getFloatList()));
            System.out.println("  Среднее: " + calculateAverage(inMemoryRepository.getFloatList()));
        }

        if (!inMemoryRepository.getStringList().isEmpty()) {
            System.out.println("Строки: " + inMemoryRepository.getStringList().size());
            System.out.println("  Максимальная длина: "
                    + Collections.max(
                            inMemoryRepository.getStringList(), Comparator.comparingInt(String::length)).length());
            System.out.println("  Минимальная длина: "
                    + Collections.min(
                            inMemoryRepository.getStringList(), Comparator.comparingInt(String::length)).length());
        }
    }

    private BigDecimal calculateSum(List<String> numbers) {
        BigDecimal sum = BigDecimal.ZERO;
        for (String numStr : numbers) {
            BigDecimal num = new BigDecimal(numStr);
            sum = sum.add(num);
        }
        return sum;
    }

    protected BigDecimal calculateAverage(List<String> numbers) {

        if (numbers.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = calculateSum(numbers);
        BigDecimal count = new BigDecimal(numbers.size());

        return sum.divide(count, SCALE, RoundingMode.HALF_UP);
    }
}
