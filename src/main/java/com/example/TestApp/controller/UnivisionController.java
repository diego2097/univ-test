package com.example.TestApp.controller;


import com.example.TestApp.model.request.ExtractUrlDto;
import com.example.TestApp.model.response.ResultDto;
import com.example.TestApp.service.UnivisionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UnivisionController {
    private final UnivisionService univisonService;
    @Autowired
    public UnivisionController(UnivisionService univisonService) {
        this.univisonService = univisonService;
    }

    @GetMapping(path = "/v1/extract")
    ResponseEntity<ResultDto> getUnivisionDto(@RequestBody ExtractUrlDto extractUrlDto) throws JsonProcessingException {
        return ResponseEntity.ok(univisonService.getUnivisionDto(extractUrlDto));
    }
}
