package ai.openfabric.api.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerRequest {
    private int port;
    private String name;
}
