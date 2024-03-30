package ru.zuablov.time.tracking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zuablov.time.tracking.annotations.TrackAsyncTime;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    @TrackAsyncTime
    public CompletableFuture<String> getStringAsync() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Hello world! Async.");
        return CompletableFuture.completedFuture("Hello world! Async.");
    }
}
