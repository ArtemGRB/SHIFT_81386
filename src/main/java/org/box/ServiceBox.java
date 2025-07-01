package org.box;

import org.config.Config;
import org.repository.InMemoryRepository;
import org.service.FileService;
import org.service.StatService;

import java.util.HashMap;
import java.util.Map;

public class ServiceBox {

    private final Map<String, Object> objets = new HashMap<>();

    public ServiceBox() {
        registerConfig();
        registerInMemoryRepository();
        registerFileService();
        registerStatService();
    }

    private <T> void register(String type, T instance) {
        objets.put(type, instance);
    }

    public <T> T get(Class<T> type) {
        Object object = objets.get(type.getName());
        if (object == null) {
            throw new IllegalStateException("Объект не найден: " + type.getName());
        }
        return (T) object;
    }

    private void registerConfig() {
        register(Config.class.getName(), new Config());
    }

    private void registerInMemoryRepository() {
        register(InMemoryRepository.class.getName(), new InMemoryRepository());
    }

    private void registerFileService() {
        if (!objets.containsKey(InMemoryRepository.class.getName())) {
            registerInMemoryRepository();
        }
        if (!objets.containsKey(Config.class.getName())) {
            registerConfig();
        }

        register(FileService.class.getName(), new FileService(
                get(InMemoryRepository.class),
                get(Config.class)));
    }

    private void registerStatService() {
        if (!objets.containsKey(InMemoryRepository.class.getName())) {
            registerInMemoryRepository();
        }

        register(StatService.class.getName(), new StatService(
                get(InMemoryRepository.class)));
    }
}
