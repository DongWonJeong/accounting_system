package com.sparta.dto.ad.info;

import com.sparta.entity.VideoAd;
import lombok.Getter;

@Getter
public class VideoAdInfoResponseDto {

    private Long videoId;
    private Long adId;

    public VideoAdInfoResponseDto(VideoAd videoAd) {
        this.videoId = videoAd.getVideo().getId();
        this.adId = videoAd.getAd().getId();
    }
}
