package org.box;

import org.junit.jupiter.api.Test;
import org.repository.InMemoryRepository;
import org.service.FileService;
import static org.junit.jupiter.api.Assertions.*;

class ServiceBoxTest {
    @Test
    void testGetServices() {
        ServiceBox box = new ServiceBox();

        assertNotNull(box.get(InMemoryRepository.class));
        assertNotNull(box.get(FileService.class));
    }
}