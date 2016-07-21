package com.github.monicangl.peoplesystem.service;

public enum Filter
{
    FULL_NAME("fullName"), WORKING_OFFICE("workingOffice");

    private String value;

    Filter(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
