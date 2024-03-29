package ru.zuablov.time.tracking.service;

public interface TimeTrackService {
    void createTrack(String method, long execTime);
}
