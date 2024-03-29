package ru.zuablov.time.tracking.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.zuablov.time.tracking.service.TimeTrackService;

import java.math.BigDecimal;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class TrackTimeAspect {
    private final TimeTrackService timeTrackService;

    @Around("@annotation(ru.zuablov.time.tracking.annotations.TrackTime)")
    public Object timeTrack(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        var res = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("Execution time {}", BigDecimal.valueOf(resultTime / 1000));
        timeTrackService.createTrack(joinPoint.getSignature().toShortString(), resultTime);
        return res;
    }
}
