package com.xj.book.home.config;


import com.xj.book.home.utils.MapperUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defultExcepitonHandler(HttpServletRequest request, Exception e) {
        /*if (e instanceof NoHandlerFoundException) {
            return MapperUtils.originalNotFound(e.getMessage());
        }*/
        return MapperUtils.originalError(e.getMessage());
    }

}