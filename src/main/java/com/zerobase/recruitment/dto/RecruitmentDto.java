package com.zerobase.recruitment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zerobase.recruitment.entity.Recruitment;

import java.time.LocalDateTime;

public class RecruitmentDto {
    public record Request(
            String title,
            Integer recruiterCount,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
            LocalDateTime closingDate,
            String companyMemberId
    ){
        public Recruitment toEntity(){
            return Recruitment.builder()
                    .title(title)
                    .recruiterCount(recruiterCount)
                    .closingDate(closingDate)
                    .build();
        }
    }
}
