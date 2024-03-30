package ru.zuablov.time.tracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Сущность для хранения статистики в БД.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "time_track")
public class TrackTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String methodName;
    private BigDecimal execTime;
    private String status;
    private String groupMethod;

    public TrackTimeEntity(String methodName, BigDecimal execTime, String status, String groupMethod) {
        this.execTime = execTime;
        this.methodName = methodName;
        this.status = status;
        this.groupMethod = groupMethod;
    }
}
