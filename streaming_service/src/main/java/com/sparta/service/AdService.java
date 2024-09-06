package com.sparta.service;

import com.sparta.dto.ad.info.AdInfoResponseDto;
import com.sparta.dto.ad.upload.UploadAdRequestDto;
import com.sparta.dto.ad.upload.UploadAdResponseDto;
import com.sparta.entity.Ad;
import com.sparta.repository.AdRepository;
import com.sparta.repository.VideoAdRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final VideoAdRepository videoAdRepository;

    public AdService(AdRepository adRepository, VideoAdRepository videoAdRepository) {
        this.adRepository = adRepository;
        this.videoAdRepository = videoAdRepository;
    }

    // 광고 조회
    public List<AdInfoResponseDto> getAds() {

        List<AdInfoResponseDto> adInfoResponseDto = new ArrayList<>();
        List<Ad> ads = adRepository.findAllByOrderByCreateDateAsc();

        for (Ad ad : ads) {
            adInfoResponseDto.add(new AdInfoResponseDto(ad));
        }

        return adInfoResponseDto;
    }

    // 광고 등록
    public UploadAdResponseDto uploadAd(UploadAdRequestDto uploadAdRequestDto) {

        // 중복된 광고 제목 여부
        if (adRepository.existsByTitle(uploadAdRequestDto.getTitle())) {
            throw new IllegalArgumentException("중복된 광고 입니다.");
        }

        Ad ad = new Ad(uploadAdRequestDto);

        Ad savedAd = adRepository.save(ad);

        UploadAdResponseDto uploadAdResponseDto = new UploadAdResponseDto(savedAd);

        return uploadAdResponseDto;
    }
}
