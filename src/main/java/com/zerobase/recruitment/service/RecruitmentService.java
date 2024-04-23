package com.zerobase.recruitment.service;

import com.zerobase.recruitment.dto.ApplicationDto;
import com.zerobase.recruitment.dto.RecruitmentDto;
import com.zerobase.recruitment.entity.Application;
import com.zerobase.recruitment.entity.CompanyMember;
import com.zerobase.recruitment.entity.Recruitment;
import com.zerobase.recruitment.entity.Resume;
import com.zerobase.recruitment.enums.ApplicationStatus;
import com.zerobase.recruitment.enums.RecruitmentStatus;
import com.zerobase.recruitment.repository.ApplicationRepository;
import com.zerobase.recruitment.repository.CompanyMemberRepository;
import com.zerobase.recruitment.repository.RecruitmentRepository;
import com.zerobase.recruitment.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyMemberRepository companyMemberRepository;
    private final ResumeRepository resumeRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public void postingRecruitment(RecruitmentDto.Request request) {
        // todo 이 회원이 기업회원인지 검증 필요
        CompanyMember companyMember = companyMemberRepository.findByLoginId(request.companyMemberId())
                .orElseThrow(() -> new RuntimeException("기업 회원 정보 없음"));

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
                () -> new RuntimeException("해당하는 공고 없음")).toDto();
    }

    @Transactional
    public RecruitmentDto.Response modifyRecruitment(Long recruitmentId, RecruitmentDto.Request request) {
        // todo 이 공고의 진짜 주인인지?
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 공고 없음"));

        if (!recruitment.getCompanyMember().getLoginId().equals(request.companyMemberId())) {
            throw new RuntimeException("잘못된 기업회원 정보 입니다.");
        }

        // todo 맞으면 업데이트
        return recruitment.update(request).toDto();
    }

    @Transactional
    public void deleteRecruitment(Long recruitmentId, RecruitmentDto.Request request) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 공고 없음"));

        if (!recruitment.getCompanyMember().getLoginId().equals(request.companyMemberId())) {
            throw new RuntimeException("잘못된 기업회원 정보 입니다.");
        }

        recruitmentRepository.deleteById(recruitmentId);
    }

    @Transactional
    public void applyRecruitment(Long recruitmentId, ApplicationDto.Request request) {
        // todo 개인 회원이면서 valid 한 이력서 정보이다.
        Resume resume = resumeRepository.findByIdAndMemberId(request.resumeId(), request.memberId())
                .orElseThrow(() -> new RuntimeException("이력서 정보를 찾을 수 없습니다."));

        //todo 존재하는 공고여야 한다!
        Recruitment recruitment = recruitmentRepository.findByIdAndStatus(recruitmentId, RecruitmentStatus.OPEN)
                .orElseThrow(() -> new RuntimeException("해당하는 공고 없음"));

        Application application = Application.builder()
                .recruitment(recruitment)
                .resume(resume)
                .status(ApplicationStatus.APPLY_FINISHED)
                .build();

        applicationRepository.save(application);
    }

    @Transactional(readOnly = true)
    public List<ApplicationDto.Response> getApplications(Long recruitmentId, Long companyMemberId) {
        // todo valid 한 기업회원 정보인가?
        companyMemberRepository.findById(companyMemberId)
                .orElseThrow(()-> new RuntimeException("조회 권한 없음!!"));

        // todo 지원자들 정보 조회
        List<Application> applicationList = applicationRepository.findAllByRecruitmentId(recruitmentId);

        return applicationList.stream().map(a -> ApplicationDto.Response.builder()
                .applicationId(a.getId())
                .status(a.getStatus())
                .applicationDate(a.getApplicationDate())
                .resumeId(a.getResume().getId())
                .resumeTitle(a.getResume().getTitle())
                .educationList(a.getResume().getEducation())
                .name(a.getResume().getMember().getName()).build()).toList();
    }
}
