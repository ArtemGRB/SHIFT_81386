package org.service;

import org.junit.jupiter.api.Test;
import org.repository.InMemoryRepository;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;



class StatServiceTest {


    @Test
    void testCalculateAverage() {
        InMemoryRepository repo = new InMemoryRepository();
        repo.add(10L);
        repo.add(20L);
        repo.add(30L);

        StatService statService = new StatService(repo);
        BigDecimal avg = statService.calculateAverage(repo.getIntegerList());
        assertEquals(new BigDecimal("20.000"), avg);
    }
}