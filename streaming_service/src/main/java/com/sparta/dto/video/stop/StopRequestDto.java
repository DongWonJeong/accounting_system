package com.sparta.dto.video.stop;

import lombok.Getter;

@Getter
public class StopRequestDto {

    private Long userId;
    private Long videoId;
    private int currentPosition;
}
