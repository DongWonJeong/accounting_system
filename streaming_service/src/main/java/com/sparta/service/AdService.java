package com.sparta.service;

import com.sparta.dto.ad.info.AdInfoResponseDto;
import com.sparta.dto.ad.register.RegisterRequestDto;
import com.sparta.dto.ad.register.RegisterResponseDto;
import com.sparta.dto.ad.upload.UploadAdRequestDto;
import com.sparta.dto.ad.upload.UploadAdResponseDto;
import com.sparta.entity.Ad;
import com.sparta.entity.Video;
import com.sparta.entity.VideoAd;
import com.sparta.repository.AdRepository;
import com.sparta.repository.VideoAdRepository;
import com.sparta.repository.VideoRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final VideoAdRepository videoAdRepository;
    private final VideoRepository videoRepository;

    public AdService(AdRepository adRepository, VideoAdRepository videoAdRepository, VideoRepository videoRepository) {
        this.adRepository = adRepository;
        this.videoAdRepository = videoAdRepository;
        this.videoRepository = videoRepository;
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

    // 비디오-광고 조회
    public List<RegisterResponseDto> getRegisterVideoAd() {

        List<RegisterResponseDto> registerResponseDto = new ArrayList<>();
        List<VideoAd> videoAds = videoAdRepository.findAll();

        for (VideoAd videoAd : videoAds) {
            registerResponseDto.add(new RegisterResponseDto(videoAd));
        }

        return registerResponseDto;
    }

    // 비디오-광고 등록
    public RegisterResponseDto registerVideoAd(RegisterRequestDto registerRequestDto) {

        // 비디오 중복 여부
        Video video = videoRepository.findById(registerRequestDto.getVideoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 비디오가 존재하지 않습니다."));

        // 광고 중복 여부
        Ad ad = adRepository.findById(registerRequestDto.getAdId())
                .orElseThrow(() -> new IllegalArgumentException("해당 광고가 존재하지 않습니다."));

        VideoAd videoAd = new VideoAd(video, ad);

        VideoAd savedVideoAd = videoAdRepository.save(videoAd);

        RegisterResponseDto registerResponseDto = new RegisterResponseDto(savedVideoAd);

        return registerResponseDto;
    }
}
