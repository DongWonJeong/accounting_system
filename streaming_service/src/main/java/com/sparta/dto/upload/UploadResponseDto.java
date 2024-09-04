package com.sparta.dto.upload;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UploadResponseDto {

    private Long videoId;
    private Long userId;
    private String title;
    private int totalPlayTime;
    private LocalDateTime uploadDate;
    private int videoViews;

    private UploadResponseDto(Long videoId, Long userId, String title, int totalPlayTime, LocalDateTime uploadDate, int videoViews) {
        this.videoId = videoId;
        this.userId = userId;
        this.title = title;
        this.totalPlayTime = totalPlayTime;
        this.uploadDate = uploadDate;
        this.videoViews = videoViews;
    }
}
