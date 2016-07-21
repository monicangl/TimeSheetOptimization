package com.github.monicangl.peoplesystem.domain.jigsaw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkingOffice
{
    private String name;

    public String getName()
    {
        return name;
    }
}
