package com.sparta.dto.completion;

import com.sparta.entity.User;
import com.sparta.entity.Video;
import lombok.Getter;

@Getter
public class CompletionResponseDto {

    private Long userId;
    private Long videoId;
    private boolean status;

    public CompletionResponseDto(User user, Video video, boolean status) {
        this.userId = user.getId();
        this.videoId = video.getId();
        this.status = status;
    }
}
