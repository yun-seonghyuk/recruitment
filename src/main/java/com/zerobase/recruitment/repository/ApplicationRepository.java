package com.zerobase.recruitment.repository;

import com.zerobase.recruitment.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByRecruitmentId(Long recruitmentId);
}
