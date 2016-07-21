package com.github.monicangl.peoplesystem.service.mapper;

import org.springframework.stereotype.Component;
import com.github.monicangl.peoplesystem.domain.jigsaw.People;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class PeopleMapper
{
    private BoundMapperFacade<People, com.github.monicangl.peoplesystem.model.People> boundMapper;
    private MapperFactory mapperFactory;

    public PeopleMapper()
    {
        this.mapperFactory = new DefaultMapperFactory.Builder().build();
        this.mapperFactory.classMap(People.class
                , com.github.monicangl.peoplesystem.model.People.class)
                .field("employeeId", "employeeId")
                .field("loginName", "loginName")
                .field("preferredName", "preferredName")
                .field("gender", "gender")
                .field("picture.url", "pictureUrl")
                .field("role.name", "role")
                .field("grade.name", "grade")
                .field("department.name", "department")
                .field("hireDate", "hireDate")
                .field("totalExperience", "totalExperience")
                .field("twExperience", "twExperience")
                .field("assignable", "assignable")
                .field("homeOffice.name", "homeOffice")
                .field("workingOffice.name", "workingOffice")
                .field("projectPreferences", "projectPreference")
                .field("longTermGoal", "longTermGoal")
                .field("travelPreferences.domestic", "travelPreference.domestic")
                .field("travelPreferences.international", "travelPreference.international")
                .field("travelPreferences.travelDetails", "travelPreference.travelDetails")
                .byDefault()
                .register();
        boundMapper = mapperFactory.getMapperFacade(People.class
                , com.github.monicangl.peoplesystem.model.People.class);
    }

    public com.github.monicangl.peoplesystem.model.People map(People people)
    {
        return boundMapper.map(people);
    }
}
