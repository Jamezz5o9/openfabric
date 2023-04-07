package ai.openfabric.api.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

    private final DockerClient dockerClient;

    public DockerController(DockerClient dockerClient){
        this.dockerClient = dockerClient;
    }
    @PostMapping("/container/create")
    public void createContainer(){
        CreateContainerResponse containerResponse = dockerClient.createContainerCmd("olalekan3264/jamesimage").exec();
        dockerClient.startContainerCmd(containerResponse.getId()).exec();
    }

}
