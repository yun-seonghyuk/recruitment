package com.zerobase.recruitment.service;

import com.zerobase.recruitment.dto.RecruitmentDto;
import com.zerobase.recruitment.entity.CompanyMember;
import com.zerobase.recruitment.entity.Recruitment;
import com.zerobase.recruitment.enums.RecruitmentStatus;
import com.zerobase.recruitment.repository.CompanyMemberRepository;
import com.zerobase.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private static final Logger log = LoggerFactory.getLogger(RecruitmentService.class);
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyMemberRepository companyMemberRepository;

    @Transactional
    public void postingRecruitment(RecruitmentDto.Request request) {
        // todo 이 회원이 기업회원인지 검증 필요
        CompanyMember companyMember =  companyMemberRepository.findByLoginId(request.companyMemberId())
                .orElseThrow(()-> new RuntimeException("기업 회원 정보 없음"));

        // todo 공고를 등록
        Recruitment recruitment = request.toEntity();
        recruitment.setCompanyMember(companyMember);
        recruitment.opening();

        recruitmentRepository.save(recruitment);
    }

    @Transactional(readOnly = true)
    public List<RecruitmentDto.Response> getRecruitments() {
        List<Recruitment> recruitments = recruitmentRepository.findAllByStatus(RecruitmentStatus.OPEN);
        return recruitments.stream().map(Recruitment::toDto).toList();
    }

    @Transactional(readOnly = true)
    public RecruitmentDto.Response getRecruitment(Long id) {
        return recruitmentRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("해당하는 공고 없음")).toDto();
    }

    @Transactional
    public RecruitmentDto.Response modifyRecruitment(Long recruitmentId, RecruitmentDto.Request request) {
        // todo 이 공고의 진짜 주인인지?
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 공고 없음"));

        if(!recruitment.getCompanyMember().getLoginId().equals(request.companyMemberId())){
            throw new RuntimeException("잘못된 기업회원 정보 입니다.");
        }

        // todo 맞으면 업데이트
        return recruitment.update(request).toDto();
    }

    @Transactional
    public void deleteRecruitment(Long recruitmentId, RecruitmentDto.Request request) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 공고 없음"));

        if(!recruitment.getCompanyMember().getLoginId().equals(request.companyMemberId())){
            throw new RuntimeException("잘못된 기업회원 정보 입니다.");
        }

        recruitmentRepository.deleteById(recruitmentId);
    }
}
