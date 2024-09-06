package com.sparta.dto.video.upload;

import lombok.Getter;

@Getter
public class UploadRequestDto {

    private Long userId;
    private String title;
    private int totalPlayTime;
}
