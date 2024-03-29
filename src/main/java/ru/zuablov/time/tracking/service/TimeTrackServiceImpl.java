package ru.zuablov.time.tracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zuablov.time.tracking.entity.TimeTrackEntity;
import ru.zuablov.time.tracking.repository.TimeTrackRepository;

@Service
@RequiredArgsConstructor
public class TimeTrackServiceImpl implements TimeTrackService {

    private final TimeTrackRepository repository;

    @Override
    public void createTrack(String method, long execTime) {
        TimeTrackEntity entity = new TimeTrackEntity(method, execTime);
        repository.save(entity);
    }
}
