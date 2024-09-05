package com.sparta.dto.stop;

import com.sparta.entity.User;
import com.sparta.entity.Video;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StopResponseDto {

    private Long userId;
    private Long videoId;
    private int currentPosition;
    private LocalDateTime lastPlayTime;

    public StopResponseDto(User user, Video video, int currentPosition, LocalDateTime lastPlayTime) {
        this.userId = user.getId();
        this.videoId = video.getId();
        this.currentPosition = currentPosition;
        this.lastPlayTime = lastPlayTime;
    }
}
