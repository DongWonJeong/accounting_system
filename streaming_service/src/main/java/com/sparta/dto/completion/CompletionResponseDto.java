package com.sparta.dto.completion;

import lombok.Getter;

@Getter
public class CompletionResponseDto {

    private Long userId;
    private Long videoId;
    private boolean status;
}
