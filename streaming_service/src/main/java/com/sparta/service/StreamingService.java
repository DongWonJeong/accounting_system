package com.sparta.service;

import com.sparta.dto.play.PlayRequestDto;
import com.sparta.dto.play.PlayResponseDto;
import com.sparta.dto.stop.StopRequestDto;
import com.sparta.dto.stop.StopResponseDto;
import com.sparta.dto.upload.UploadRequestDto;
import com.sparta.dto.upload.UploadResponseDto;
import com.sparta.entity.Role;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import com.sparta.entity.VideoHistory;
import com.sparta.repository.UserRepository;
import com.sparta.repository.VideoHistoryRepository;
import com.sparta.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StreamingService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final VideoHistoryRepository videoHistoryRepository;

    public StreamingService(VideoRepository videoRepository, UserRepository userRepository, VideoHistoryRepository videoHistoryRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.videoHistoryRepository = videoHistoryRepository;
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

        // 비디오 조회수 증가
        video.setVideoViews(video.getVideoViews() + 1);
        videoRepository.save(video);

        // 비디오 시청 기록 확인 및 불러오는 메서드
        VideoHistory videoHistory = videoHistoryRepository.findByUserIdAndVideoId(playRequestDto.getUserId(), playRequestDto.getVideoId())
                .orElseGet(() -> new VideoHistory(user, video, 0, LocalDateTime.now()));

        VideoHistory savedvideoHistory = videoHistoryRepository.save(videoHistory);

        PlayResponseDto playResponseDto = new PlayResponseDto(savedvideoHistory);

        return playResponseDto;
    }

    // 비디오 정지
    public StopResponseDto stop(StopRequestDto stopRequestDto) {

        // 비디오 시청 기록 여부
        VideoHistory videoHistory = videoHistoryRepository.findByUserIdAndVideoId(stopRequestDto.getUserId(), stopRequestDto.getVideoId())
                .orElseThrow(() -> new IllegalArgumentException("사용자와 비디오 시청 기록이 없습니다."));

        // 비디오 시청 기록 업데이트
        videoHistory.setCurrentPosition(stopRequestDto.getCurrentPosition());
        videoHistory.setLastPlayTime(LocalDateTime.now());
        VideoHistory updatedVideoHistory = videoHistoryRepository.save(videoHistory);

        StopResponseDto stopResponseDto = new StopResponseDto(updatedVideoHistory);

        return stopResponseDto;
    }

    // 비디오 시청 완료
}
