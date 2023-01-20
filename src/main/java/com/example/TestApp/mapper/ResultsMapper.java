package com.example.TestApp.mapper;

import com.example.TestApp.model.Content;
import com.example.TestApp.model.Widget;
import com.example.TestApp.model.response.ResultDto;
import com.example.TestApp.model.response.UnivisionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ResultsMapper {

    public ResultDto mapWidgetsToUnivisionDto(List<Widget> widgets) {
        log.info("Building Aggregate Result Response..");
        Map<String, List<Content>> groupByType = widgets.stream()
                .flatMap(widget -> widget.getContents().stream())
                .collect(Collectors.groupingBy(Content::getType));
        List<UnivisionDto> results = groupByType.keySet()
                .stream()
                .map(type -> buildUnivisionDto(type, groupByType.get(type)))
                .collect(Collectors.toList());
        log.info("Build success");
        return ResultDto.builder()
                .results(results)
                .build();
    }
    private UnivisionDto buildUnivisionDto(String type, List<Content> contents){
        return UnivisionDto.builder()
                .type(type)
                .count(contents.size())
                .titles(contents.stream().map(Content::getTitle).collect(Collectors.toList()))
                .build();
    }
}
