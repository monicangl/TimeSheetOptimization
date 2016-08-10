package com.github.monicangl.peoplesystem.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class RequestParameterValueUnsupportedException extends RuntimeException
{
    public RequestParameterValueUnsupportedException(String name)
    {
        super(name);
    }
}
