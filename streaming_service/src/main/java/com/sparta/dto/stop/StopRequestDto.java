package com.sparta.dto.stop;

import lombok.Getter;

@Getter
public class StopRequestDto {

    private Long userId;
    private Long videoId;
    private int currentPosition;
}
