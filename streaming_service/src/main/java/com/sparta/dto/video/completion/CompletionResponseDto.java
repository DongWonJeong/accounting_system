package com.sparta.dto.video.completion;

import com.sparta.entity.User;
import com.sparta.entity.Video;
import lombok.Getter;

@Getter
public class CompletionResponseDto {

    private Long userId;
    private Long videoId;
    private boolean isCompleted;

    public CompletionResponseDto(User user, Video video, boolean isCompleted) {
        this.userId = user.getId();
        this.videoId = video.getId();
        this.isCompleted = isCompleted;
    }
}
