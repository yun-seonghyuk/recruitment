package com.zerobase.recruitment.repository;

import com.zerobase.recruitment.entity.Recruitment;
import com.zerobase.recruitment.enums.RecruitmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findAllByStatus(RecruitmentStatus status);
    Optional<Recruitment> findByIdAndStatus(Long recruitmentId, RecruitmentStatus status);
}
