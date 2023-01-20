package com.example.TestApp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UrlDto {
    private Status success;
    private Data data;
}
