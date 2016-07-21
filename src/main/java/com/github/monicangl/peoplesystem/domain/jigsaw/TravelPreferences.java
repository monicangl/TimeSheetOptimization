package com.github.monicangl.peoplesystem.domain.jigsaw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelPreferences
{
    private boolean domestic;
    private boolean international;
    private String travelDetails;

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
