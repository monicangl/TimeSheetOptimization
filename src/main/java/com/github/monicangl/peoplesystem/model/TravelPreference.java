package com.github.monicangl.peoplesystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TravelPreference
{
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private boolean domestic;
    private boolean international;
    @Column(columnDefinition = "TEXT")
    private String travelDetails;

    public TravelPreference()
    { // jpa only
    }

    public TravelPreference(boolean domestic, boolean international, String travelDetails)
    {
        this.domestic = domestic;
        this.international = international;
        this.travelDetails = travelDetails;
    }

    public Long getId()
    {
        return id;
    }

    public boolean isDomestic()
    {
        return domestic;
    }

    public boolean isInternational()
    {
        return international;
    }

    public String getTravelDetails()
    {
        return travelDetails;
    }
}
