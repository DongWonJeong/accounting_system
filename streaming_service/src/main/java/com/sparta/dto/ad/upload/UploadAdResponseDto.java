package com.sparta.dto.ad.upload;

import com.sparta.entity.Ad;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UploadAdResponseDto {

    private Long id;
    private String title;
    private LocalDateTime createDate;

    public UploadAdResponseDto(Ad ad) {
        this.id = ad.getId();
        this.title = ad.getTitle();
        this.createDate = ad.getCreateDate();
    }
}
