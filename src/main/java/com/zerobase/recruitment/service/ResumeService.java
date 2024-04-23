package com.zerobase.recruitment.service;

import com.zerobase.recruitment.dto.ResumeDto;
import com.zerobase.recruitment.entity.Member;
import com.zerobase.recruitment.entity.Resume;
import com.zerobase.recruitment.repository.MemberRepository;
import com.zerobase.recruitment.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void postingResume(ResumeDto.Request request) {
        // todo 넘어온 회원정보가 valid 한 회원 정보인지?
        Member member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new RuntimeException("회원정보 없음!!"));

        // todo 이력서 저장
        Resume resume = request.toEntity();
        resume.open();
        resume.setMember(member);
        resumeRepository.save(resume);
    }
}
