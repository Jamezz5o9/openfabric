package ai.openfabric.api.services.impl;

import ai.openfabric.api.dtos.response.ApiData;
import ai.openfabric.api.dtos.response.WorkerStatisticsRequest;
import ai.openfabric.api.exceptions.GenericException;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.model.WorkerStatistics;
import ai.openfabric.api.repository.WorkerRepository;
import ai.openfabric.api.repository.WorkerStatisticsRepository;
import ai.openfabric.api.services.WorkerStatisticsService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerStatisticsImpl  implements WorkerStatisticsService {

    private WorkerStatisticsRepository workerStatisticsRepository;
    private WorkerRepository workerRepository;
    private DockerClient dockerClient;

    @Autowired
    public WorkerStatisticsImpl(WorkerRepository workerRepository, WorkerStatisticsRepository workerStatisticsRepository,
                                DockerClient dockerClient){
        this.workerStatisticsRepository = workerStatisticsRepository;
        this.workerRepository = workerRepository;
        this.dockerClient = dockerClient;
    }
    @Override
    public ApiData updateWorkerStatistics(WorkerStatisticsRequest workerStatisticsRequest) {
        Optional<Worker> foundWorker = workerRepository.findByName(workerStatisticsRequest.getWorkerName());
        if(!foundWorker.isPresent()) throw new GenericException(String.format("Worker with name: %s not found", workerStatisticsRequest.getWorkerName()));
        else{
//            DockerClient client = DockerClientBuilder.getInstance(foundWorker.get().getId()).build();
//            ContainerState containerState = dockerClient.statsCmd(foundWorker.get().getId()).exec();

            WorkerStatistics workerStatistics = WorkerStatistics.builder()
                    .worker(foundWorker.get())
                    .cpuUsage(workerStatisticsRequest.getCpuUsage())
                    .memoryUsage(workerStatisticsRequest.getMemoryUsage())
                    .timestamp(LocalDateTime.now())
                    .build();
            workerStatisticsRepository.save(workerStatistics);
        }
        return ApiData.builder()
                .resonse("Worker statistics updated successfully")
                .build();
    }

    @Override
    public List<WorkerStatistics> getWorkerStatistics(String workerName) {
        Optional<Worker> foundWorker = workerRepository.findByName(workerName);
        if(!foundWorker.isPresent()) throw new GenericException(String.format("Worker with name: %s not found", workerName));
        else return workerStatisticsRepository.findTopByWorkerOrderByTimestampDesc(foundWorker.get());
    }
}
