package ru.zuablov.time.tracking.service;

import java.util.concurrent.CompletableFuture;

public interface TestService {
    /**
     * Метод имитирует асинхронную операцию
     *
     * @return Отложенный результат
     */
    CompletableFuture<String> getStringAsync();
}
