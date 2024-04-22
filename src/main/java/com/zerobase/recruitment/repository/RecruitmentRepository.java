package com.zerobase.recruitment.repository;

import com.zerobase.recruitment.entity.Recruitment;
import com.zerobase.recruitment.enums.RecruitmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findAllByStatus(RecruitmentStatus status);
}
