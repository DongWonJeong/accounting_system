package com.sparta.controller;

import com.sparta.dto.ad.info.AdInfoResponseDto;
import com.sparta.dto.ad.upload.UploadAdRequestDto;
import com.sparta.dto.ad.upload.UploadAdResponseDto;
import com.sparta.service.AdService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    // 광고 조회
    @GetMapping("/list")
    public List<AdInfoResponseDto> getAds() {
        return adService.getAds();
    }

    // 광고 등록
    @PostMapping("/upload")
    public UploadAdResponseDto uploadAd(@RequestBody UploadAdRequestDto uploadAdRequestDto) {
        return adService.uploadAd(uploadAdRequestDto);
    }
}
