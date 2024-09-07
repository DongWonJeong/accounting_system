package com.sparta.controller;

import com.sparta.dto.ad.info.AdInfoResponseDto;
import com.sparta.dto.video.completion.CompletionRequestDto;
import com.sparta.dto.video.completion.CompletionResponseDto;
import com.sparta.dto.video.info.VideoInfoResponseDto;
import com.sparta.dto.video.play.PlayRequestDto;
import com.sparta.dto.video.play.PlayResponseDto;
import com.sparta.dto.video.stop.StopRequestDto;
import com.sparta.dto.video.stop.StopResponseDto;
import com.sparta.dto.video.upload.UploadRequestDto;
import com.sparta.dto.video.upload.UploadResponseDto;
import com.sparta.service.StreamingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    // 비디오 조회
    @GetMapping("/list")
    public List<VideoInfoResponseDto> getVideos() {
        return streamingService.getVideos();
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

    // 비디오 시청 완료
    @PostMapping("/completion")
    public CompletionResponseDto completion(@RequestBody CompletionRequestDto completionRequestDto) {
        return streamingService.completion(completionRequestDto);
    }
}
