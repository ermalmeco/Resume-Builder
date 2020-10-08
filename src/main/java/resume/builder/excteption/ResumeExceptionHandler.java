package resume.builder.excteption;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResumeExceptionHandler extends ResponseEntityExceptionHandler  {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(""+HttpStatus.INTERNAL_SERVER_ERROR);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResumeAlreadyExists.class)
    public ResponseEntity<ExceptionResponse> resourceAlreadyExists(ResumeAlreadyExists ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(""+HttpStatus.CONFLICT);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(""+HttpStatus.NOT_FOUND);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(""+HttpStatus.NOT_FOUND);

        StringBuilder sb = new StringBuilder(ex.getBindingResult().getAllErrors().size());
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            sb.append(error.getDefaultMessage());
        }
        response.setErrorMessage(sb.toString());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
