package ai.openfabric.api.repository;

import ai.openfabric.api.model.Worker;
import ai.openfabric.api.model.WorkerStatistics;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerStatisticsRepository extends CrudRepository<WorkerStatistics, String> {
    List<WorkerStatistics> findTopByWorkerOrderByTimestampDesc(Worker worker);
}
