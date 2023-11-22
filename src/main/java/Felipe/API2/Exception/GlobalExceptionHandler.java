package Felipe.API2.Exception;
import Felipe.API2.dto.DefaultError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.net.BindException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<DefaultError> handlePacienteNotFoundException(PacienteNotFoundException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PacienteNotInformedException.class)
    public ResponseEntity<DefaultError> PacienteNotInformedException(PacienteNotInformedException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UfNotFoundException.class)
    public ResponseEntity<DefaultError> UfNotFoundException(UfNotFoundException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CpfDuplicadoException.class)
    public ResponseEntity<DefaultError> CpfDuplicadoException(CpfDuplicadoException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.CONFLICT.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DataNascimentoFuturaException.class)
    public ResponseEntity<DefaultError> DataNascimentoFuturaException(DataNascimentoFuturaException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SexoInvalidoException.class)
    public ResponseEntity<DefaultError> SexoInvalidoException(SexoInvalidoException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<DefaultError> ExternalServiceException(ExternalServiceException ex, WebRequest request) {
        DefaultError error = new DefaultError(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
