package ru.zuablov.time.tracking.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zuablov.time.tracking.annotations.TrackTime;
import ru.zuablov.time.tracking.enums.Group;
import ru.zuablov.time.tracking.service.TestService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Тестовый контроллер, содержит методы имитирующие выполнение различных по времени операций.
 */
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    /**
     * Метод возвращает строку без задержки.
     *
     * @return String
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
    })
    @GetMapping(value = "/0delay", produces = "application/json")
    @TrackTime
    public ResponseEntity<String> getResultWithoutDelay() {
        return ResponseEntity.ok("Hello World! - 0 sec.");
    }

    /**
     * Метод возвращает строку с задержкой в 2 секунды.
     *
     * @return String
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
    })
    @GetMapping(value = "/2delay", produces = "application/json")
    @TrackTime(Group.CONTROLLER)
    public ResponseEntity<String> getResultWithDelay2Sec() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return ResponseEntity.ok("Hello World! - 2 sec.");
    }

    /**
     * Метод возвращает строку с задержкой 3 секунды.
     *
     * @return String
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
    })
    @GetMapping(value = "/3delay", produces = "application/json")
    @TrackTime
    public ResponseEntity<String> getResultWithDelay3Sec() throws InterruptedException, ExecutionException {
        testService.getStringAsync();
        return ResponseEntity.ok("Hello World! - 3 sec.");
    }

    /**
     * Метод возвращает строку с задержкой 5 секунд.
     *
     * @return String
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
    })
    @GetMapping(value = "/5delay", produces = "application/json")
    @TrackTime
    public ResponseEntity<String> getResultWithDelay5Sec() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return ResponseEntity.ok("Hello World! - 5 sec.");
    }
}
