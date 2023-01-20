package com.example.TestApp;

import com.example.TestApp.mapper.ResultsMapper;
import com.example.TestApp.model.Content;
import com.example.TestApp.model.Widget;
import com.example.TestApp.model.response.ResultDto;
import com.example.TestApp.model.response.UnivisionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public class ResultsMapperTest {
    private ResultsMapper resultsMapper;
    @BeforeEach
    void setUp() {
        resultsMapper = new ResultsMapper();
    }

    @Test
    void mapSuccess() {
        // Given
        List<Widget> widgets = buildWidgets();
        // When
        ResultDto resultDto = resultsMapper.mapWidgetsToUnivisionDto(widgets);
        // Then
        Optional<UnivisionDto> optionalVideo = resultDto.getResults().stream()
                .filter(univisionDto -> univisionDto.getType().equals("video"))
                .findFirst();

        Assertions.assertTrue(optionalVideo.isPresent());
        Assertions.assertEquals(1,optionalVideo.get().getCount());

        Optional<UnivisionDto> optionalTv = resultDto.getResults().stream()
                .filter(univisionDto -> univisionDto.getType().equals("tv"))
                .findFirst();

        Assertions.assertTrue(optionalTv.isPresent());
        Assertions.assertEquals(1,optionalTv.get().getCount());

        Optional<UnivisionDto> optionalGames = resultDto.getResults().stream()
                .filter(univisionDto -> univisionDto.getType().equals("games"))
                .findFirst();

        Assertions.assertTrue(optionalGames.isPresent());
        Assertions.assertEquals(1,optionalGames.get().getCount());

        Optional<UnivisionDto> vixPlayer = resultDto.getResults().stream()
                .filter(univisionDto -> univisionDto.getType().equals("vixplayer"))
                .findFirst();

        Assertions.assertTrue(vixPlayer.isPresent());
        Assertions.assertEquals(3,vixPlayer.get().getCount());

        Optional<UnivisionDto> videoChannel = resultDto.getResults().stream()
                .filter(univisionDto -> univisionDto.getType().equals("videochannelpromo"))
                .findFirst();

        Assertions.assertTrue(videoChannel.isPresent());
        Assertions.assertEquals(3,videoChannel.get().getCount());
    }

    private List<Widget> buildWidgets() {
        Widget widget1 = new Widget();
        widget1.setContents(buildContents("video", "vixplayer", "videochannelpromo"));
        Widget widget2 = new Widget();
        widget2.setContents(buildContents("tv", "vixplayer", "videochannelpromo"));
        Widget widget3 = new Widget();
        widget3.setContents(buildContents("games", "vixplayer", "videochannelpromo"));
        return Arrays.asList(widget1, widget2, widget3);
    }

    private List<Content> buildContents(String ... types) {
        return Arrays.stream(types)
                .map(this::buildContent)
                .collect(Collectors.toList());
    }

    private Content buildContent(String type) {
        Content content = new Content();
        content.setTitle("title");
        content.setType(type);
        return content;
    }
}
