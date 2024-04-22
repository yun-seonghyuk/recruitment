package com.zerobase.recruitment.controller;

import com.zerobase.recruitment.dto.RecruitmentDto;
import com.zerobase.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/recruitments")
    public void postingRecruitment(@RequestBody RecruitmentDto.Request request) {
        recruitmentService.postingRecruitment(request);
    }

}
