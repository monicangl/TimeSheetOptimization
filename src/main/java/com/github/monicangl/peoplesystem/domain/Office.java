package com.github.monicangl.peoplesystem.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Office
{
    private String name;
    private Integer peopleCount;
    private List<Name> people;

    public Office(String name, List<Name> people)
    {
        this.name = name;
        this.people = people;
        this.peopleCount = people.size();
    }

    @JsonProperty("workingOffice")
    public String getName()
    {
        return name;
    }

    @JsonProperty("peopleCount")
    public Integer getPeopleCount()
    {
        return peopleCount;
    }

    @JsonProperty("people")
    public List<Name> getPeople()
    {
        return people;
    }
}
