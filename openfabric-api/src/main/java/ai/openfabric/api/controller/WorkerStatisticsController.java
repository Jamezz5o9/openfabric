package ai.openfabric.api.controller;

import ai.openfabric.api.dtos.response.ApiData;
import ai.openfabric.api.dtos.response.WorkerStatisticsRequest;
import ai.openfabric.api.model.WorkerStatistics;
import ai.openfabric.api.services.WorkerStatisticsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkerStatisticsController {
    private final WorkerStatisticsService workerStatisticsService;
    @Autowired
    public WorkerStatisticsController(WorkerStatisticsService workerStatisticsService){
        this.workerStatisticsService = workerStatisticsService;
    }

    @GetMapping("/{workerName}")
    public ResponseEntity<List<WorkerStatistics>> getWorkerStatistics(@PathVariable String workerName){
        List<WorkerStatistics> foundWorkers = workerStatisticsService.getWorkerStatistics(workerName);
        return new ResponseEntity<>(foundWorkers, HttpStatus.OK);
    }

    @PutMapping("/statistics")
    public  ResponseEntity<ApiData> updateWorkerStatistics(@RequestBody WorkerStatisticsRequest workerStatisticsRequest){
        ApiData data = workerStatisticsService.updateWorkerStatistics(workerStatisticsRequest);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
