package com.sparta.repository;

import com.sparta.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

    boolean existsByUserIdAndTitle(Long userId, String title);
}
