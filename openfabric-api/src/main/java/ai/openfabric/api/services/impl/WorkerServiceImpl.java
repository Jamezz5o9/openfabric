package ai.openfabric.api.services.impl;

import ai.openfabric.api.dtos.requests.WorkerRequest;
import ai.openfabric.api.exceptions.GenericException;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.repository.WorkerRepository;
import ai.openfabric.api.services.WorkerService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.ExposedPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ai.openfabric.api.enums.WorkerStatus.RUNNING;
import static ai.openfabric.api.enums.WorkerStatus.STOPPED;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final DockerClient dockerClient;
    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, DockerClient dockerClient){
        this.workerRepository = workerRepository;
        this.dockerClient = dockerClient;
    }
    @Override
    public List<Worker> getAllWorkers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Worker> workersPage = workerRepository.findAll(pageable);
        return workersPage.getContent();
    }

    @Override
    public Worker startWorker(WorkerRequest workerRequest)  {
        String containerID = dockerClient.createContainerCmd("my_image")
                .withName(workerRequest.getName())
                .withExposedPorts(ExposedPort.tcp(workerRequest.getPort()))
                .exec().getId();
        dockerClient.startContainerCmd(containerID).exec();
        Worker worker = new Worker(workerRequest.getName(), workerRequest.getPort(), RUNNING);
        workerRepository.save(worker);
        return worker;
    }

    @Override
    public void stopWorker(String name) {
        dockerClient.stopContainerCmd(name).exec();
        Optional<Worker> savedWorker = workerRepository.findByName(name);
        if(savedWorker.isPresent()){
            Worker worker = savedWorker.get();
            worker.setWorkerStatus(STOPPED);
            workerRepository.save(worker);
        } else{
            throw new GenericException(String.format("Worker %s is not found", name));
        }
    }

    @Override
    public Worker getWorkerInfo(String name) {
        Optional<Worker> foundWorker = workerRepository.findByName(name);
        if(!foundWorker.isPresent()) throw new GenericException(String.format("Worker with name: %s not found", name));
        return foundWorker.get();
    }
}
