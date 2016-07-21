package com.github.monicangl.peoplesystem.domain.jigsaw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture
{
    private String url;

    public String getUrl()
    {
        return url;
    }
}
