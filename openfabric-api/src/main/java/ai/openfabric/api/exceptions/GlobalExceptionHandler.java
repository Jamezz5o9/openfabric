package ai.openfabric.api.exceptions;

import ai.openfabric.api.utils.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handleGenericException(GenericException genericException, HttpServletRequest httpServletRequest){
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .data(genericException.getMessage())
                .isSuccessful(false)
                .statusCode(HttpStatus.BAD_REQUEST)
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }
}
