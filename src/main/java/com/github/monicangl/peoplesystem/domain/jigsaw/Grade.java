package com.github.monicangl.peoplesystem.domain.jigsaw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Grade
{
    private String name;

    public String getName()
    {
        return name;
    }
}
