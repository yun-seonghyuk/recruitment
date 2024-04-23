package com.zerobase.recruitment.dto;

import com.zerobase.recruitment.entity.Education;
import com.zerobase.recruitment.entity.Resume;

import java.util.List;

public class ResumeDto {

    public record Request(
        String title,
        List<EducationDto> educationList,
        String loginId
    ){
        public Resume toEntity(){
            return Resume.builder()
                    .title(title)
                    .educationList(educationList.stream()
                            .map(e-> Education.builder().school(e.school).degree(e.degree).build()).toList())
                    .build();

        }
    }

    public record EducationDto(
        String school,
        Integer degree // 고졸 : 0, 대졸: 1, 석사: 2, 박사: 3
    ){

    }
}
