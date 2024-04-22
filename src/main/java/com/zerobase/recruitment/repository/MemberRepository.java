package com.zerobase.recruitment.repository;

import com.zerobase.recruitment.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
