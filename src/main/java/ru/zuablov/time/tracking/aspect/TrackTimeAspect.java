package ru.zuablov.time.tracking.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.zuablov.time.tracking.annotations.TrackAsyncTime;
import ru.zuablov.time.tracking.annotations.TrackTime;
import ru.zuablov.time.tracking.enums.Status;
import ru.zuablov.time.tracking.service.TrackTimeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class TrackTimeAspect<T> {
    private final TrackTimeService trackTimeService;

    @Around("@annotation(trackTime)")
    public Object timeTrack(ProceedingJoinPoint joinPoint, TrackTime trackTime) throws Throwable {
        long startTime = System.nanoTime();
        Status status = Status.OK;
        Object result;
        String group = trackTime.value().name();
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            status = Status.ERROR;
            throw e;
        } finally {
            BigDecimal resultTime = BigDecimal.valueOf(System.nanoTime() - startTime).divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
            log.info("Execution time {} ms for {}", resultTime, joinPoint.getSignature().toShortString());
            trackTimeService.createTimeStatsAsync(joinPoint.getSignature().toShortString(), group, status, resultTime);
        }
        return result;
    }

    @Around("@annotation(trackAsyncTime)")
    public CompletableFuture<T> timeTrackAsync(ProceedingJoinPoint joinPoint, TrackAsyncTime trackAsyncTime) {
        return CompletableFuture.supplyAsync(() -> {
            long startTime = System.nanoTime();
            T res;
            Status status = Status.OK;
            String group = trackAsyncTime.value().name();
            try {
                res = (T) joinPoint.proceed();
            } catch (Throwable e) {
                status = Status.ERROR;
                log.error("Exception in {}", joinPoint.getSignature(), e);
                throw new CompletionException(e);
            } finally {
                BigDecimal resultTime = BigDecimal.valueOf(System.nanoTime() - startTime).divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
                log.info("Execution time {} ms for {}", resultTime, joinPoint.getSignature().toShortString());
                trackTimeService.createTimeStatsAsync(joinPoint.getSignature().toShortString(), group, status, resultTime);
            }
            return res;
        });
    }
}
