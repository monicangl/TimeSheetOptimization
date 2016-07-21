package com.github.monicangl.peoplesystem.domain;

public class PeopleBasicInfo
{
    private String shortName;
    private String fullName;
    private String workingOffice;

    public PeopleBasicInfo(String shortName, String fullName, String workingOffice)
    {
        this.shortName = shortName;
        this.fullName = fullName;
        this.workingOffice = workingOffice;
    }

    public String getShortName()
    {
        return shortName;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getWorkingOffice()
    {
        return workingOffice;
    }
}
