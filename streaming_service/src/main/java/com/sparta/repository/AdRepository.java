package com.sparta.repository;

import com.sparta.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findAllByOrderByCreateDateAsc();

    boolean existsByTitle(String title);
}
