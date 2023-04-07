package ai.openfabric.api.services;

import ai.openfabric.api.dtos.requests.WorkerRequest;
import ai.openfabric.api.model.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> getAllWorkers(int pageNumber, int pageSize);
    Worker startWorker(WorkerRequest workerRequest);
    void stopWorker(String name);

    Worker getWorkerInfo(String name);
}
