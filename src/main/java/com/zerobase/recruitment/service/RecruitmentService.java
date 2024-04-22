package com.zerobase.recruitment.service;

import com.zerobase.recruitment.dto.RecruitmentDto;
import com.zerobase.recruitment.entity.CompanyMember;
import com.zerobase.recruitment.entity.Recruitment;
import com.zerobase.recruitment.repository.CompanyMemberRepository;
import com.zerobase.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private static final Logger log = LoggerFactory.getLogger(RecruitmentService.class);
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyMemberRepository companyMemberRepository;

    @Transactional
    public void postingRecruitment(RecruitmentDto.Request request) {
        // todo 이 회원이 기업회원인지 검증 필요
        CompanyMember companyMember =  companyMemberRepository
                .findByLoginId(request.companyMemberId())
                .orElseThrow(()-> new RuntimeException("기업 회원 정보 없음"));

        // todo 공고를 등록
        Recruitment recruitment = request.toEntity();
        recruitment.setCompanyMember(companyMember);
        recruitment.opening();

        recruitmentRepository.save(recruitment);
    }

}
