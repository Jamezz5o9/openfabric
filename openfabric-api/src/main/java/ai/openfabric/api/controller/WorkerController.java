package ai.openfabric.api.controller;

import ai.openfabric.api.dtos.requests.WorkerRequest;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/worker")
public class WorkerController {
    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService){
        this.workerService = workerService;
    }
    @PostMapping(path = "/hello")
    public @ResponseBody String hello(@RequestBody String name) {
        return "Hello!" + name;
    }

    @GetMapping
    public List<Worker> getListOfWorkers(@RequestParam(defaultValue = "0") int pageNumber,
                                         @RequestParam(defaultValue = "10") int pageSize){
        return workerService.getAllWorkers(pageNumber, pageSize);
    }
    @PostMapping("/start")
    public ResponseEntity<Worker> startWorker(@RequestBody WorkerRequest workerRequest){
        Worker worker = workerService.startWorker(workerRequest);
        return ResponseEntity.ok(worker);
    }

    @DeleteMapping("stop/{name}")
    public ResponseEntity<Void> stopWorker(@PathVariable String name){
        workerService.stopWorker(name);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{name}")
    public ResponseEntity<Worker> getWorkerInfo(@PathVariable String name){
        Worker worker = workerService.getWorkerInfo(name);
        return ResponseEntity.ok(worker);
    }
}
