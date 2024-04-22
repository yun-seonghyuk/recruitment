package com.zerobase.recruitment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zerobase.recruitment.entity.Recruitment;
import com.zerobase.recruitment.enums.RecruitmentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class RecruitmentDto {

    public record Request(
            String title,
            Integer recruiterCount,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
            LocalDateTime closingDate,
            String companyMemberId,
            RecruitmentStatus status
    ){
        public Recruitment toEntity(){
            return Recruitment.builder()
                    .title(title)
                    .recruiterCount(recruiterCount)
                    .closingDate(closingDate)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Response{
        private Long recruitmentId;
        private String title;
        private Integer recruiterCount;
        private LocalDateTime closingDate;
        private RecruitmentStatus status;
        private LocalDateTime modifiedDate;
        private LocalDateTime postingDate;
        private Long companyMemberId;
        private String companyName;
    }
}
