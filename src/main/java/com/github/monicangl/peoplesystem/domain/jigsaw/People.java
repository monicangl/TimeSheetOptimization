package com.github.monicangl.peoplesystem.domain.jigsaw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class People
{
    private String employeeId;
    private String loginName;
    private String preferredName;
    private String gender;
    private Picture picture;
    private Role role;
    private Grade grade;
    private Department department;
    private String hireDate;
    private Float totalExperience;
    private Float twExperience;
    private Boolean assignable;
    private HomeOffice homeOffice;
    private WorkingOffice workingOffice;
    private String projectPreferences;
    private String longTermGoal;
    private TravelPreferences travelPreferences;

    public String getEmployeeId()
    {
        return employeeId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public String getPreferredName()
    {
        return preferredName;
    }

    public String getGender()
    {
        return gender;
    }

    public Picture getPicture()
    {
        return picture;
    }

    public Role getRole()
    {
        return role;
    }

    public Grade getGrade()
    {
        return grade;
    }

    public Department getDepartment()
    {
        return department;
    }

    public String getHireDate()
    {
        return hireDate;
    }

    public Float getTotalExperience()
    {
        return totalExperience;
    }

    public Float getTwExperience()
    {
        return twExperience;
    }

    public Boolean getAssignable()
    {
        return assignable;
    }

    public HomeOffice getHomeOffice()
    {
        return homeOffice;
    }

    public WorkingOffice getWorkingOffice()
    {
        return workingOffice;
    }

    public String getProjectPreferences()
    {
        return projectPreferences;
    }

    public String getLongTermGoal()
    {
        return longTermGoal;
    }

    public TravelPreferences getTravelPreferences()
    {
        return travelPreferences;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    public void setGrade(Grade grade)
    {
        this.grade = grade;
    }
}
