package ru.team2.skud.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty("was_sent")
    public boolean wasSent;
}
