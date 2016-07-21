package com.github.monicangl.peoplesystem.service.exception;

public class RequestParameterValueUnsupportedException extends RuntimeException
{
    public RequestParameterValueUnsupportedException(String name)
    {
        super(name);
    }
}
