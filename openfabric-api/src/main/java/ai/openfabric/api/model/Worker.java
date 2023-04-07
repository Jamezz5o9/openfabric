package ai.openfabric.api.model;


import ai.openfabric.api.enums.WorkerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Worker extends Datable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    public String id;

    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    private int port;
    @Column(nullable = false)
    private int status;
    @Enumerated(value = EnumType.STRING)
    private WorkerStatus workerStatus;
    private int ipAddress;

    public Worker(String name, int port, WorkerStatus running) {
        this.name = name;
        this.port = port;
        this.workerStatus = running;
    }
}
