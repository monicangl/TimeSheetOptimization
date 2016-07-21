package com.github.monicangl.peoplesystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class People
{
    @Id
    private String employeeId;
    private String loginName;
    private String preferredName;
    private String gender;
    private String pictureUrl;
    private String role;
    private String grade;
    private String department;
    private String hireDate;
    private Float totalExperience;
    private Float twExperience;
    private Boolean assignable;
    private String homeOffice;
    private String workingOffice;
    @Column(columnDefinition = "TEXT")
    private String projectPreference;
    @Column(columnDefinition = "TEXT")
    private String longTermGoal;
    @OneToOne(cascade = CascadeType.ALL)
    private TravelPreference travelPreference;
//
//    public People()
//    { // jpa only
//    }

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

    public String getRole()
    {
        return role;
    }

    public String getWorkingOffice()
    {
        return workingOffice;
    }

    public Object getPictureUrl()
    {
        return pictureUrl;
    }

    public String getGrade()
    {
        return grade;
    }

    public String getDepartment()
    {
        return department;
    }

    public String getHireDate()
    {
        return hireDate;
    }

    public float getTotalExperience()
    {
        return totalExperience;
    }

    public float getTwExperience()
    {
        return twExperience;
    }

    public Boolean isAssignable()
    {
        return assignable;
    }

    public String getHomeOffice()
    {
        return homeOffice;
    }

    public String getProjectPreference()
    {
        return projectPreference;
    }

    public String getLongTermGoal()
    {
        return longTermGoal;
    }

    public TravelPreference getTravelPreference()
    {
        return travelPreference;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public void setPreferredName(String preferredName)
    {
        this.preferredName = preferredName;
    }

    public void setWorkingOffice(String workingOffice)
    {
        this.workingOffice = workingOffice;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public void setHireDate(String hireDate)
    {
        this.hireDate = hireDate;
    }

    public void setTotalExperience(float totalExperience)
    {
        this.totalExperience = totalExperience;
    }

    public void setTwExperience(float twExperience)
    {
        this.twExperience = twExperience;
    }

    public void setAssignable(Boolean assignable)
    {
        this.assignable = assignable;
    }

    public void setHomeOffice(String homeOffice)
    {
        this.homeOffice = homeOffice;
    }

    public void setProjectPreference(String projectPreference)
    {
        this.projectPreference = projectPreference;
    }

    public void setLongTermGoal(String longTermGoal)
    {
        this.longTermGoal = longTermGoal;
    }

    public void setTravelPreference(TravelPreference travelPreference)
    {
        this.travelPreference = travelPreference;
    }
}
