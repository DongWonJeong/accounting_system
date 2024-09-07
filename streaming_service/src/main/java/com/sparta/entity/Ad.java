package com.sparta.entity;

import com.sparta.dto.ad.upload.UploadAdRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="ad_views", nullable = false)
    private int adViews;

    @Column(name="create_date", nullable = false)
    private LocalDateTime createDate;

    public Ad(UploadAdRequestDto uploadAdRequestDto) {
        this.title = uploadAdRequestDto.getTitle();
        this.createDate = LocalDateTime.now();
    }
}
