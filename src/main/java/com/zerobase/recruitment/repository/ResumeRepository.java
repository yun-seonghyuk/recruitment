package com.zerobase.recruitment.repository;

import com.zerobase.recruitment.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByIdAndMemberId(Long resumeId, Long memberId);
}
