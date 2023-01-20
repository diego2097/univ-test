package com.example.TestApp.service;

import com.example.TestApp.mapper.ResultsMapper;
import com.example.TestApp.model.UrlDto;
import com.example.TestApp.model.request.ExtractUrlDto;
import com.example.TestApp.model.response.ResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class UnivisionService {
    public static final int BUFFER_SIZE = 3 * 1024 * 1024;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ResultsMapper resultsMapper;

    public ResultDto getUnivisionDto(ExtractUrlDto extractUrlDto) throws JsonProcessingException {

        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(BUFFER_SIZE))
                .build();
        log.info("Fetching information from: {}", extractUrlDto.getUrl());
        String response = WebClient.builder()
                .baseUrl(extractUrlDto.getUrl())
                .exchangeStrategies(strategies)
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
        UrlDto urlDto = mapToUrlDto(response);
        return resultsMapper.mapWidgetsToUnivisionDto(urlDto.getData().getWidgets());
    }

    private UrlDto mapToUrlDto(String clientResponse) throws JsonProcessingException {
        return objectMapper.readValue(clientResponse, UrlDto.class);
    }
}
