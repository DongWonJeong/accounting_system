package com.sparta.dto.stop;

import com.sparta.entity.VideoHistory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StopResponseDto {

    private Long userId;
    private Long videoId;
    private int currentPosition;
    private LocalDateTime lastPlayTime;

    public StopResponseDto(VideoHistory videoHistory) {
        this.userId = videoHistory.getUser().getId();
        this.videoId = videoHistory.getVideo().getId();
        this.currentPosition = videoHistory.getCurrentPosition();
        this.lastPlayTime = videoHistory.getLastPlayTime();
    }
}
