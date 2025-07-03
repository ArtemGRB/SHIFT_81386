package org.service;

import org.config.Config;
import org.junit.jupiter.api.Test;
import org.repository.InMemoryRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class FileServiceTest {
    @Test
    void testProcessFiles() {
        InMemoryRepository repo = new InMemoryRepository();
        Config config = mock(Config.class);
        when(config.getOutputPath()).thenReturn(".");
        when(config.getPrefix()).thenReturn("");

        FileService fileService = new FileService(repo, config);
        fileService.processFiles("./src/test/resources/test_input.txt");

        assertFalse(repo.getIntegerList().isEmpty() ||
                repo.getFloatList().isEmpty() ||
                repo.getStringList().isEmpty());
    }
}