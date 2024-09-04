package com.sparta.dto.stop;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StopResponseDto {

    private Long userId;
    private Long videoId;
    private int currentPosition;
    private LocalDateTime lastPlayTime;
}
