package pl.rmitula.restfullshop.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rmitula.restfullshop.exception.model.ErrorResponse;

public class BaseExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable exception) {
        System.out.println("***************************************");
        System.out.println(exception);
        System.out.println("***************************************");
        return new ErrorResponse(500, exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(HttpMessageNotReadableException exception) {
        System.out.println(exception);
        return new ErrorResponse(400, exception.getMessage());
    }
}
