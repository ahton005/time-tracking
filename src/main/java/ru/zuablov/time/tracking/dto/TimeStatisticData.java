package ru.zuablov.time.tracking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zuablov.time.tracking.enums.Status;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeStatisticData {
    @JsonProperty("method_name")
    private String methodName;
    @JsonProperty("average_time")
    private BigDecimal avgExecTime;
    @JsonProperty("max_time")
    private BigDecimal maxExecTime;
    @JsonProperty("min_time")
    private BigDecimal minExecTime;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("group_method")
    private String groupMethod;
}
