package ai.openfabric.api.repository;

import ai.openfabric.api.model.Worker;
import org.springframework.data.domain.*;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkerRepository extends CrudRepository<Worker, String> {
    Page<Worker> findAll(Pageable pageable);
    Optional<Worker> findByName(String name);
}
