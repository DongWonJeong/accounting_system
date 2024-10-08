package com.sparta.service;

import com.sparta.dto.video.completion.CompletionRequestDto;
import com.sparta.dto.video.completion.CompletionResponseDto;
import com.sparta.dto.video.info.VideoInfoResponseDto;
import com.sparta.dto.video.play.PlayRequestDto;
import com.sparta.dto.video.play.PlayResponseDto;
import com.sparta.dto.video.stop.StopRequestDto;
import com.sparta.dto.video.stop.StopResponseDto;
import com.sparta.dto.video.upload.UploadRequestDto;
import com.sparta.dto.video.upload.UploadResponseDto;
import com.sparta.entity.*;
import com.sparta.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StreamingService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final VideoAdRepository videoAdRepository;
    private final VideoHistoryRepository videoHistoryRepository;

    public StreamingService(VideoRepository videoRepository, UserRepository userRepository, AdRepository adRepository,
                            VideoAdRepository videoAdRepository, VideoHistoryRepository videoHistoryRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.videoAdRepository = videoAdRepository;
        this.videoHistoryRepository = videoHistoryRepository;
    }

    // 비디오 조회
    public List<VideoInfoResponseDto> getVideos() {

        List<VideoInfoResponseDto> videoInfoResponseDto = new ArrayList<>();
        List<Video> videos = videoRepository.findAllByOrderByUploadDateAsc();

        for (Video video : videos) {
            videoInfoResponseDto.add(new VideoInfoResponseDto(video));
        }

        return videoInfoResponseDto;
    }

    // 비디오 등록
    public UploadResponseDto upload(UploadRequestDto uploadRequestDto) {

        // 사용자 여부
        User user = userRepository.findById(uploadRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 사용자의 역할이 SELLER인지 확인
        if (user.getRole() != Role.SELLER) {
            throw new IllegalArgumentException("동영상 등록할 수 있는 권한이 없습니다.");
        }

        // 중복 동영상 여부
        if (videoRepository.existsByUserIdAndTitle(uploadRequestDto.getUserId(), uploadRequestDto.getTitle())) {
            throw new IllegalArgumentException("중복된 동영상 입니다.");
        }

        Video video = new Video(uploadRequestDto,user);

        Video savedVideo = videoRepository.save(video);

        UploadResponseDto uploadResponseDto = new UploadResponseDto(savedVideo);

        return uploadResponseDto;
    }

    // 비디오 재생
    public PlayResponseDto play(PlayRequestDto playRequestDto) {

        // 사용자 여부
        User user = userRepository.findById(playRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 등록된 비디오 여부
        Video video = videoRepository.findById(playRequestDto.getVideoId())
                .orElseThrow(() -> new IllegalArgumentException("비디오를 찾을 수 없습니다."));

        // 비디오의 소유자와 현재 사용자가 같은지 확인
        boolean isVideoOwner = video.getUser().equals(user);

        // 비디오 조회수 증가 -> 사용자가 비디오 소유자가 아닐 경우
        if (!isVideoOwner) {
            video.setVideoViews(video.getVideoViews() + 1);
            videoRepository.save(video);
        }

        // 비디오 시청 기록 확인 및 불러오는 메서드
        VideoHistory videoHistory = videoHistoryRepository.findByUserIdAndVideoId(playRequestDto.getUserId(), playRequestDto.getVideoId())
                .orElseGet(() -> new VideoHistory(user, video, 0, LocalDateTime.now()));

        // 비디오 시청 기록 저장
        videoHistory = videoHistoryRepository.save(videoHistory);

        // 비디오와 연결된 광고 시청 처리
        List<VideoAd> videoAds = videoAdRepository.findAllByVideoId(video.getId());

        // 중간값 계산
        double middlePlayTime = video.getTotalPlayTime() / 2.0;

        for (VideoAd videoAd : videoAds) {
            Ad ad = videoAd.getAd();

            // 현재 시점이 비디오 총 길이의 중간보다 클 경우에만 조회수 증가
            if (videoHistory.getCurrentPosition() >= middlePlayTime) {
                // 광고 시청 횟수 증가 -> 비디오 소유자가 아닐 경우
                if (!isVideoOwner) {
                    ad.setAdViews(ad.getAdViews() + 1);
                    adRepository.save(ad);
                }
            }
        }

        PlayResponseDto playResponseDto = new PlayResponseDto(videoHistory);

        return playResponseDto;
    }

    // 비디오 정지
    public StopResponseDto stop(StopRequestDto stopRequestDto) {

        // 사용자와 비디오 조회
        User user = userRepository.findById(stopRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Video video = videoRepository.findById(stopRequestDto.getVideoId())
                .orElseThrow(() -> new IllegalArgumentException("비디오를 찾을 수 없습니다."));

        // 비디오의 총 길이(totalPlayTime) 가져옴
        long totalPlayTime = video.getTotalPlayTime();

        // 현재 위치(currentPosition) 값 검증
        long currentPosition = stopRequestDto.getCurrentPosition();

        if (currentPosition > totalPlayTime) {
            throw new IllegalArgumentException("현재 위치가 비디오 총 길이를 초과했습니다.");
        }

        // 비디오 시청 기록 여부
        VideoHistory videoHistory = videoHistoryRepository.findByUserIdAndVideoId(stopRequestDto.getUserId(), stopRequestDto.getVideoId())
                .orElseThrow(() -> new IllegalArgumentException("사용자와 비디오 시청 기록이 없습니다."));

        // 비디오 시청 기록 업데이트
        videoHistory.setCurrentPosition(stopRequestDto.getCurrentPosition());
        videoHistory.setLastPlayTime(LocalDateTime.now());
        VideoHistory updatedVideoHistory = videoHistoryRepository.save(videoHistory);

        StopResponseDto stopResponseDto = new StopResponseDto(user, video, updatedVideoHistory.getCurrentPosition(), updatedVideoHistory.getLastPlayTime());

        return stopResponseDto;
    }

    // 비디오 시청 완료
    public CompletionResponseDto completion(CompletionRequestDto completionRequestDto) {

        // 사용자와 비디오 조회
        User user = userRepository.findById(completionRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Video video = videoRepository.findById(completionRequestDto.getVideoId())
                .orElseThrow(() -> new IllegalArgumentException("비디오를 찾을 수 없습니다."));

        // 비디오 시청 기록 조회
        VideoHistory videoHistory = videoHistoryRepository.findByUserIdAndVideoId(completionRequestDto.getUserId(), completionRequestDto.getVideoId())
                .orElseThrow(() -> new IllegalArgumentException("비디오 시청 기록이 없습니다."));

        // 시청 완료 여부
        boolean isCompleted = videoHistory.getCurrentPosition() == video.getTotalPlayTime();

        // 시청 완료 상태 업데이트
        if (isCompleted) {
            videoHistory.setIsCompleted(true);
            videoHistoryRepository.save(videoHistory);
        }

        CompletionResponseDto completionResponseDto = new CompletionResponseDto(user, video, isCompleted);

        return completionResponseDto;
    }
}
