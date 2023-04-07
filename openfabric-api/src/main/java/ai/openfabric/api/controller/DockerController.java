package ai.openfabric.api.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/worker")
public class DockerController {

    private final DockerClient dockerClient;

    public DockerController(DockerClient dockerClient){
        this.dockerClient = dockerClient;
    }
    @PostMapping("/container")
    public void createContainer(){
        CreateContainerResponse containerResponse = dockerClient.createContainerCmd("image").withCmd("command").exec();
        dockerClient.startContainerCmd(containerResponse.getId()).exec();
    }

}
