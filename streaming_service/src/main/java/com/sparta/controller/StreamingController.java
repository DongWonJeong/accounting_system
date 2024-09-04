package com.sparta.controller;

import com.sparta.dto.upload.UploadRequestDto;
import com.sparta.dto.upload.UploadResponseDto;
import com.sparta.service.StreamingService;
import org.springframework.web.bind.annotation.*;

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

//    // 비디오 재생
//    @PostMapping("/play")
//
//    // 비디오 정지
//    @PostMapping("/stop")
//
//    // 비디오 시청 완료
//    @PostMapping("/completion")
}
