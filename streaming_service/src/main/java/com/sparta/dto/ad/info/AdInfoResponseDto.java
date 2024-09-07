package com.sparta.dto.ad.info;

import com.sparta.entity.Ad;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class AdInfoResponseDto {
    private Long id;
    private String title;
    private Integer adViews;
    private LocalDateTime createDate;

    public AdInfoResponseDto(Ad ad) {
        this.id = ad.getId();
        this.title = ad.getTitle();
        this.adViews = ad.getAdViews();
        this.createDate = ad.getCreateDate();
    }
}