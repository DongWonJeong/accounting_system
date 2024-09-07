package com.sparta.dto.video.info;

import com.sparta.entity.Video;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VideoInfoResponseDto {

    private Long videoId;
    private String title;
    private int totalPlayTime;
    private LocalDateTime uploadDate;
    private int videoViews;

    public VideoInfoResponseDto(Video video) {
        this.videoId = video.getId();
        this.title = video.getTitle();
        this.totalPlayTime = video.getTotalPlayTime();
        this.uploadDate = video.getUploadDate();
        this.videoViews = video.getVideoViews();
    }
}
