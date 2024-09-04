package com.sparta.service;

import com.sparta.dto.upload.UploadRequestDto;
import com.sparta.dto.upload.UploadResponseDto;
import com.sparta.entity.Role;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import com.sparta.repository.UserRepository;
import com.sparta.repository.VideoRepository;
import org.springframework.stereotype.Service;

@Service
public class StreamingService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public StreamingService(VideoRepository videoRepository, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
    }

    // 비디오 등록
    public UploadResponseDto upload(UploadRequestDto uploadRequestDto) {

        // 사용자 조회
        User user = userRepository.findById(uploadRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 사용자의 역할이 SELLER인지 확인
        if (user.getRole() != Role.SELLER) {
            throw new IllegalArgumentException("동영상 등록할 수 있는 권한이 없습니다.");
        }

        Video video = new Video(uploadRequestDto);

        Video savedVideo = videoRepository.save(video);

        UploadResponseDto uploadResponseDto = new UploadResponseDto(savedVideo);

        return uploadResponseDto;
    }

    // 비디오 재생

    // 비디오 정지

    // 비디오 시청 완료
}
