package com.spring.apidemo.gg;

import com.spring.apidemo.data.SelfException;
import com.spring.apidemo.data.SelfResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

@RestControllerAdvice
public class SelfExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SelfExceptionHandler.class);

    @ExceptionHandler(value = SelfException.class)
    @ResponseStatus(HttpStatus.OK)
    public SelfResult demoException(SelfException e) {
        return SelfResult.error(e.getCode(), e.getMessage());
    }

//    @ExceptionHandler(value = MissingServletRequestParameterException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Exception missingServletRequestParameterException(Exception e) {
//        return e;
//    }

//    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    public Exception httpRequestMethodNotSupportedException(Exception e) {
//        return e;
//    }


}
