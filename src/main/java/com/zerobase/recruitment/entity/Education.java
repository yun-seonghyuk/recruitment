package com.zerobase.recruitment.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Education {

    private String school;
    private Integer code; // 서울대학교 : 0, 연세대학교 : 1
    private Integer degree; // 고졸: 0, 대졸: 1, 석사 2, 박사: 3
}
