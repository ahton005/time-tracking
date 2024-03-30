package ru.zuablov.time.tracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zuablov.time.tracking.dto.TimeStatisticData;
import ru.zuablov.time.tracking.entity.TrackTimeEntity;
import ru.zuablov.time.tracking.enums.Status;
import ru.zuablov.time.tracking.exception.StatisticException;
import ru.zuablov.time.tracking.repository.TrackTimeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackTimeServiceImpl implements TrackTimeService {

    private final TrackTimeRepository repository;

    @Override
    @Async
    @Transactional
    public void createTimeStatsAsync(String method, String group, Status status, BigDecimal execTime) {
        TrackTimeEntity entity = new TrackTimeEntity(method, execTime, status.name(), group);
        repository.save(entity);
    }

    @Override
    public TimeStatisticData getTimeStatistic(String method, String group, Status status) throws StatisticException {
        List<TrackTimeEntity> statisticList;
        if (method != null && !method.isEmpty()) {
            statisticList = repository.findAllByMethodNameAndStatus(method , status.name());
        } else if (group != null && !group.isEmpty()) {
            statisticList = repository.findAllByGroupMethodIgnoreCaseAndStatus(group, status.name());
        } else {
            statisticList = repository.findAllByStatus(status.name());
        }
        var minTime = statisticList.stream().map(TrackTimeEntity::getExecTime).min(BigDecimal::compareTo).orElseThrow(() -> new StatisticException("Не найдена статистика"));
        var maxTime = statisticList.stream().map(TrackTimeEntity::getExecTime).max(BigDecimal::compareTo).orElseThrow(() -> new StatisticException("Не найдена статистика"));
        var avgTime = statisticList.stream().map(TrackTimeEntity::getExecTime).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(statisticList.size()), RoundingMode.HALF_UP);
        return new TimeStatisticData(method, avgTime, maxTime, minTime, status, group);
    }

    @Override
    public List<String> getStatuses() {
        return repository.findAll().stream().map(TrackTimeEntity::getStatus).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getGroups() {
        return repository.findAll().stream().map(TrackTimeEntity::getGroupMethod).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getMethods() {
        return repository.findAll().stream().map(TrackTimeEntity::getMethodName).distinct().collect(Collectors.toList());
    }
}
