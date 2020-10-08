package resume.builder.excteption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResumeAlreadyExists extends RuntimeException {
    public ResumeAlreadyExists(String message) {
        super(message);
    }
}
