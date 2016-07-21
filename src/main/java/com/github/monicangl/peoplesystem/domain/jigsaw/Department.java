package com.github.monicangl.peoplesystem.domain.jigsaw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Department
{
    private String name;

    public String getName()
    {
        return name;
    }

//    public void setName(String name)
//    {
//        this.name = name;
//    }
}
