package ru.zuablov.time.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zuablov.time.tracking.entity.TrackTimeEntity;

import java.util.List;


@Repository
public interface TrackTimeRepository extends JpaRepository<TrackTimeEntity, Long> {
    List<TrackTimeEntity> findAllByMethodNameAndStatus(String methodName, String status);
    List<TrackTimeEntity> findAllByGroupMethodIgnoreCaseAndStatus(String group, String status);
    List<TrackTimeEntity> findAllByStatus(String status);
}
