package com.sparta.dto.upload;

import com.sparta.entity.Video;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UploadResponseDto {

    private Long videoId;
    private Long userId;
    private String title;
    private int totalPlayTime;
    private LocalDateTime uploadDate;

    public UploadResponseDto(Video video) {
        this.videoId = video.getId();
        this.userId = video.getUser().getId();
        this.title = video.getTitle();
        this.totalPlayTime = video.getTotalPlayTime();
        this.uploadDate = video.getUploadDate();
    }
}
