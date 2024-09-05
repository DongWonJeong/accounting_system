package com.sparta.entity;

import com.sparta.dto.play.PlayRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video_history")
public class VideoHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(name="current_position", nullable = false)
    private int currentPosition;

    @Column(name="last_play_time", nullable = false)
    private LocalDateTime lastPlayTime;

    public VideoHistory(User user, Video video, int currentPosition, LocalDateTime lastPlayTime) {
        this.user = user;
        this.video = video;
        this.currentPosition = currentPosition;
        this.lastPlayTime = lastPlayTime;
    }
}
