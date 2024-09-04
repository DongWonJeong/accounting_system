package com.sparta.entity;

import com.sparta.dto.upload.UploadRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="total_play_time", nullable=false)
    private Integer totalPlayTime;

    @Column(name="video_views", nullable=false)
    private Integer videoViews;

    @Column(name="upload_date", nullable=false)
    private LocalDateTime uploadDate;

    @Column(name="status", nullable=false)
    private Boolean status;

}
