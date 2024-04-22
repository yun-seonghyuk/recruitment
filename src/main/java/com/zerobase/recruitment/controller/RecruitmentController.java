package com.zerobase.recruitment.controller;

import com.zerobase.recruitment.dto.RecruitmentDto;
import com.zerobase.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/recruitments")
    public void postingRecruitment(@RequestBody RecruitmentDto.Request request) {
        recruitmentService.postingRecruitment(request);
    }

    @GetMapping("/recruitments")
    public List<RecruitmentDto.Response> getRecruitmentList() {
        return recruitmentService.getRecruitments();
    }

    @GetMapping("/recruitments/{id}")
    public RecruitmentDto.Response getRecruitment(@PathVariable(name = "id") Long recruitmentId) {
        return recruitmentService.getRecruitment(recruitmentId);
    }

    @PutMapping("/recruitments/{id}")
    public RecruitmentDto.Response modifyRecruitment(
            @PathVariable(name = "id") Long recruitmentId,
            @RequestBody RecruitmentDto.Request request
    ) {
            return recruitmentService.modifyRecruitment(recruitmentId, request);
    }

    @DeleteMapping("/recruitments/{id}")
    public void deleteRecruitment(
            @PathVariable(name = "id") Long recruitmentId,
            @RequestBody RecruitmentDto.Request request)
    {
        recruitmentService.deleteRecruitment(recruitmentId, request);
    }
}
