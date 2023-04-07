package ai.openfabric.api.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class WorkerStatisticsRequest {
    @NotNull(message = "This field should not be null")
    private String workerName;
    @NotNull(message = "This field should not  be null")
    private Double cpuUsage;
    @NotNull(message = "This field should not be null")
    private Double memoryUsage;
}
