package com.zerobase.recruitment.controller;

import com.zerobase.recruitment.dto.ResumeDto;
import com.zerobase.recruitment.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/resumes")
    public void postingResume(@RequestBody ResumeDto.Request request) {
        resumeService.postingResume(request);
    }

}
