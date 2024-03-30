package ru.zuablov.time.tracking.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zuablov.time.tracking.dto.TimeStatisticData;
import ru.zuablov.time.tracking.enums.Status;
import ru.zuablov.time.tracking.service.TrackTimeService;

import java.util.List;

/**
 * Контроллер для получения статистики по времени выполннения методов.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class TrackTimeController {
    private final TrackTimeService trackTimeService;

    /**
     * Возвращает статистику по каждому методу.
     *
     * @return String
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ", content = {@Content(schema = @Schema(implementation = TimeStatisticData.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = {@Content(schema = @Schema(implementation = String.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере", content = {@Content(schema = @Schema(implementation = String.class), mediaType = "application/json")})
    })
    @GetMapping(value = "/statistics", produces = "application/json")
    public ResponseEntity<?> getAllStatistic(@RequestParam(value = "method", required = false) String method,
                                             @RequestParam(value = "status", required = false) String status,
                                             @RequestParam(value = "group", required = false) String group
    ) {
        try {
            return ResponseEntity.ok(trackTimeService.getTimeStatistic(method, group, Status.fromValue(status)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Возвращает список статусов выполнения методов.
     *
     * @return List<String>
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере", content = {@Content(schema = @Schema(implementation = String.class), mediaType = "application/json")})
    })
    @GetMapping(value = "/statuses", produces = "application/json")
    public ResponseEntity<List<String>> getStatuses() {
        return ResponseEntity.ok(trackTimeService.getStatuses());
    }

    /**
     * Возвращает список групп методов.
     *
     * @return List<String>
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере", content = {@Content(schema = @Schema(implementation = String.class), mediaType = "application/json")})
    })
    @GetMapping(value = "/groups", produces = "application/json")
    public ResponseEntity<List<String>> getGroups() {
        return ResponseEntity.ok(trackTimeService.getGroups());
    }

    /**
     * Возвращает список методов.
     *
     * @return List<String>
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере", content = {@Content(schema = @Schema(implementation = String.class), mediaType = "application/json")})
    })
    @GetMapping(value = "/methods", produces = "application/json")
    public ResponseEntity<List<String>> getMethods() {
        return ResponseEntity.ok(trackTimeService.getMethods());
    }
}
