package ru.zuablov.time.tracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;

@Entity
@Repository
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTrackEntity {
    @Id
    Long id;
    String methodName;
    String execTime;
}
