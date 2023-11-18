package Felipe.API2.Exception;
import Felipe.API2.dto.DefaultError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.net.BindException;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Slf4j
@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<DefaultError> handlePacienteNotFoundException(PacienteNotFoundException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PacienteNotInformedException.class)
    public ResponseEntity<DefaultError> handlePacienteNotInformedException(PacienteNotInformedException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UfNotFoundException.class)
    public ResponseEntity<DefaultError> handleUfNotFoundException(UfNotFoundException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
