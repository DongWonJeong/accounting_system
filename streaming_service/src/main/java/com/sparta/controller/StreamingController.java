package com.sparta.controller;

import com.sparta.dto.play.PlayRequestDto;
import com.sparta.dto.play.PlayResponseDto;
import com.sparta.dto.stop.StopRequestDto;
import com.sparta.dto.stop.StopResponseDto;
import com.sparta.dto.upload.UploadRequestDto;
import com.sparta.dto.upload.UploadResponseDto;
import com.sparta.service.StreamingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    // 비디오 등록
    @PostMapping("/upload")
    public UploadResponseDto upload(@RequestBody UploadRequestDto uploadRequestDto) {
        return streamingService.upload(uploadRequestDto);
    }

    // 비디오 재생
    @PostMapping("/play")
    public PlayResponseDto play(@RequestBody PlayRequestDto playRequestDto) {
        return streamingService.play(playRequestDto);
    }

    // 비디오 정지
    @PostMapping("/stop")
    public StopResponseDto stop(@RequestBody StopRequestDto stopRequestDto) {
        return streamingService.stop(stopRequestDto);
    }

//    // 비디오 시청 완료
//    @PostMapping("/completion")
}
