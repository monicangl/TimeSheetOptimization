package com.github.monicangl.peoplesystem.domain;

public class Name
{
    private String shortName;
    private String fullName;

    public Name(String shortName, String fullName)
    {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName()
    {
        return shortName;
    }

    public String getFullName()
    {
        return fullName;
    }
}
