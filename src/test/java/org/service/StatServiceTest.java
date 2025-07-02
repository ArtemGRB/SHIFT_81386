package org.service;

import org.junit.jupiter.api.Test;
import org.repository.InMemoryRepository;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatServiceTest {
    @Test
    void testShortStat() {
        InMemoryRepository repo = new InMemoryRepository();
        repo.getIntegerList().add("10");
        repo.getFloatList().add("1.5");

        StatService statService = new StatService(repo);
        statService.printShortStat();
    }

    @Test
    void testCalculateAverage() {
        InMemoryRepository repo = new InMemoryRepository();
        repo.getIntegerList().addAll(List.of("10", "20", "30"));

        StatService statService = new StatService(repo);
        BigDecimal avg = statService.calculateAverage(repo.getIntegerList());
        assertEquals(new BigDecimal("20.000"), avg);
    }
}