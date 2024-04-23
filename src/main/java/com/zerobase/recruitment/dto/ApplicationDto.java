package com.zerobase.recruitment.dto;

public class ApplicationDto {
    public record Request(
            Long memberId,
            Long resumeId
    ){

    }
}
