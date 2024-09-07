package com.sparta.repository;

import com.sparta.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoAdRepository extends JpaRepository<VideoAd, Long> {
    List<VideoAd> findAllByVideoId(Long id);
}
