package com.sparta.repository;

import com.sparta.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    boolean existsByUserIdAndTitle(Long userId, String title);

    List<Video> findAllByOrderByUploadDateAsc();
}
