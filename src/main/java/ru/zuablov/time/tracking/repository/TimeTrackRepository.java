package ru.zuablov.time.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zuablov.time.tracking.entity.TimeTrackEntity;

@Repository
public interface TimeTrackRepository extends JpaRepository<TimeTrackEntity, Long> {
}
