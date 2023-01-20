package com.example.TestApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Data {

    private ArrayList<Widget> widgets;
}
