package com.sparta.dto.play;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlayResponseDto {

    private Long userId;
    private Long videoId;
    private int currentPosition;
    private LocalDateTime lastPlayTime;
}
