package pl.rmitula.restfullshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.restfullshop.exception.model.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(NotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }
}
