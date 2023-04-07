package ai.openfabric.api.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Builder
@Getter
@Setter
public class ApiErrorResponse   {
    private ZonedDateTime timeStamp;
    private HttpStatus statusCode;
    private String path;
    private Object data;
    private Boolean isSuccessful;
}
