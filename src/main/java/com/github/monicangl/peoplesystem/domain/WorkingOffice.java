package com.github.monicangl.peoplesystem.domain;

public enum WorkingOffice
{
    CHENGDU("Chengdu"), BEIJING("Beijing"), XIAN("Xi'an"), WUHAN("Wuhan"), SHANGHAI("Shanghai"), SHENZHEN("Shenzhen");

    private String value;

    WorkingOffice(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
