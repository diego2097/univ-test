package com.example.TestApp.model.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class ResultDto implements Serializable {
    List<UnivisionDto> results;
}
