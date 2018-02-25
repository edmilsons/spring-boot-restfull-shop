package pl.rmitula.restfullshop.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.restfullshop.exception.BadRequestException;
import pl.rmitula.restfullshop.exception.ConflictException;
import pl.rmitula.restfullshop.exception.NotFoundException;
import pl.rmitula.restfullshop.exception.model.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlers extends BaseExceptionHandler {
    //TODO: Handle ConstraintViolationException. Throws when posting null field.

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        System.out.println(exception);
        return new ErrorResponse(404, exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException exception) {
        System.out.println(exception);
        return new ErrorResponse(400, exception.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(ConflictException exception) {
        System.out.println(exception);
        return new ErrorResponse(409, exception.getMessage());
    }
}
