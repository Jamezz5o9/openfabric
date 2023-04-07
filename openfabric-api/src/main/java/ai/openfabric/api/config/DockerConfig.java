package ai.openfabric.api.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerConfig {

    @Bean
    public DockerClient dockerClient(){
        return DockerClientBuilder.getInstance("tcp://localhost:8080").build();
    }
}
