package ai.openfabric.api.services;

import ai.openfabric.api.dtos.response.ApiData;
import ai.openfabric.api.dtos.response.WorkerStatisticsRequest;
import ai.openfabric.api.model.WorkerStatistics;

import java.util.List;

public interface WorkerStatisticsService {
    ApiData updateWorkerStatistics(WorkerStatisticsRequest workerStatisticsRequest);
    List<WorkerStatistics> getWorkerStatistics(String workerName);
}
