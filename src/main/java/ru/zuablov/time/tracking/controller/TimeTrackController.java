package ru.zuablov.time.tracking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zuablov.time.tracking.annotations.TrackTime;

import java.util.concurrent.TimeUnit;

@RestController
public class TimeTrackController {
    @GetMapping("/noDelay")
    @TrackTime
    public ResponseEntity<String> getResultWithoutDelay() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/2delay")
    @TrackTime
    public ResponseEntity<String> getResultWithDelay2Sec() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/5delay")
    @TrackTime
    public ResponseEntity<String> getResultWithDelay5Sec() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return ResponseEntity.ok("Hello World!");
    }
}
