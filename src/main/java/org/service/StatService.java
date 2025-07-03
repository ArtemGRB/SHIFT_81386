package org.service;

import org.repository.InMemoryRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

            List<Long> longList = inMemoryRepository.getIntegerList();

            System.out.println("Целые числа: " + longList.size());
            System.out.println("  Минимальное: " + Collections.min(longList));
            System.out.println("  Максимальное: " + Collections.max(longList));
            System.out.println("  Сумма: " + calculateSum(longList));
            System.out.println("  Среднее: " + calculateAverage(longList));
        }

        if (!inMemoryRepository.getFloatList().isEmpty()) {

            List<Double> doubleList = inMemoryRepository.getFloatList();

            System.out.println("Вещественные числа: " + doubleList.size());
            System.out.println("  Минимальное: " + Collections.min(doubleList));
            System.out.println("  Максимальное: " + Collections.max(doubleList));
            System.out.println("  Сумма: " + calculateSum(doubleList));
            System.out.println("  Среднее: " + calculateAverage(doubleList));
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

    private <T extends Number> BigDecimal calculateSum(List<T> numbers) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Number num : numbers) {
            sum = sum.add(BigDecimal.valueOf(num.doubleValue()));
        }
        return sum;
    }

    protected <T extends Number> BigDecimal calculateAverage(List<T> numbers) {

        if (numbers.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = calculateSum(numbers);
        BigDecimal count = new BigDecimal(numbers.size());

        return sum.divide(count, SCALE, RoundingMode.HALF_UP);
    }
}
