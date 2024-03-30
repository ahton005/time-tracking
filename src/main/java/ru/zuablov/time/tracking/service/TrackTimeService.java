package ru.zuablov.time.tracking.service;

import ru.zuablov.time.tracking.dto.TimeStatisticData;
import ru.zuablov.time.tracking.enums.Status;
import ru.zuablov.time.tracking.exception.StatisticException;

import java.math.BigDecimal;
import java.util.List;

public interface TrackTimeService {
    /**
     * Метод для записи статистики в БД.
     *
     * @param method   Название метода
     * @param group    Группа метода
     * @param status   Статус выполнения метода
     * @param execTime Время выполнения метода
     */
    void createTimeStatsAsync(String method, String group, Status status, BigDecimal execTime);

    /**
     * Метод для получения статистики из БД.
     *
     * @param method Название метода
     * @param group  Группа метода
     * @param status Статус выполнения метода
     */
    TimeStatisticData getTimeStatistic(String method, String group, Status status) throws StatisticException;

    /**
     * Метод для получения статусов выполнения методов.
     */
    List<String> getStatuses();

    /**
     * Метод для получения групп методов.
     */
    List<String> getGroups();

    /**
     * Метод для получения списка методов для которых есть данные о времени выполнения.
     */
    List<String> getMethods();
}
