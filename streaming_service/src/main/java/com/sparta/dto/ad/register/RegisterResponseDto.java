package com.sparta.dto.ad.register;

import com.sparta.entity.VideoAd;
import lombok.Getter;

@Getter
public class RegisterResponseDto {

    private Long videoId;
    private Long adId;

    public RegisterResponseDto(VideoAd videoAd) {
        this.videoId = videoAd.getVideo().getId();
        this.adId = videoAd.getAd().getId();
    }
}
