package com.basaki.example.postgres.jsonb.error;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * {@code ExceptionProcessor} processes exceptions at the application level and
 * it is not restricted to any specific controller.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/8/17
 */
@ControllerAdvice
@Slf4j
public class ExceptionProcessor {

    /**
     * Handles <tt>DataNotFoundException</tt> exception.It unwraps the root case
     * and coverts it into an <tt>ErrorInfo</tt> object.
     *
     * @param req HTTP request to extract the URL
     * @param ex  exception to be processed
     * @return ths error information that is sent to the client
     */
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorInfo handleDataNotFoundException(
            HttpServletRequest req, DataNotFoundException ex) {
        ErrorInfo info = getErrorInfo(req, HttpStatus.NOT_FOUND);
        info.setMessage(ex.getMessage());

        return info;
    }

    /**
     * Handles <tt>IllegalArgumentException</tt> exception.It unwraps the root
     * case and coverts it into an <tt>ErrorInfo</tt> object.
     *
     * @param req HTTP request to extract the URL
     * @param ex  exception to be processed
     * @return ths error information that is sent to the client
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo handleIllegalArgException(HttpServletRequest req,
            IllegalArgumentException ex) {
        ErrorInfo info = getErrorInfo(req, HttpStatus.BAD_REQUEST);
        info.setMessage(ex.getMessage());

        return info;
    }

    /**
     * Handles <tt>IllegalStateException</tt> exception.It unwraps the root
     * case and coverts it into an <tt>ErrorInfo</tt> object.
     *
     * @param req HTTP request to extract the URL
     * @param ex  exception to be processed
     * @return ths error information that is sent to the client
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo handleIllegalStateException(HttpServletRequest req,
            IllegalStateException ex) {
        ErrorInfo info = getErrorInfo(req, HttpStatus.BAD_REQUEST);
        info.setMessage(ex.getMessage());

        return info;
    }

    private ErrorInfo getErrorInfo(HttpServletRequest req,
            HttpStatus httpStatus) {
        ErrorInfo info = new ErrorInfo();
        ServletUriComponentsBuilder builder =
                ServletUriComponentsBuilder.fromServletMapping(req);
        info.setPath(builder.path(
                req.getRequestURI()).build().getPath());
        info.setCode(httpStatus.value());
        info.setType(httpStatus.getReasonPhrase());
        return info;
    }
}

