package ru.zuablov.time.tracking.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TrackTimeAspect {

    @Around("@annotation(TrackTime)")
    public Object timeTrack(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        var res = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        return res;
    }
}
