package com.zerobase.recruitment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum EducationLevel {

    SEOUL_UNIV(0,"서울대학교", i -> 10 * i),
    YONSEI_UNIV(0,"연세대학교", i -> 9 * i),
    KOREA_UNIV(0,"고려대학교", i -> 9 * i),
    KAIST_UNIV(0,"카이스트", i -> 9 * i),
    HANYANG_UNIV(0,"한양대학교", i -> 8 * i),
    SEOGANG_UNIV(0,"서강대학교", i -> 8 * i),
    SUNGKYUNHANG_UNIV(0,"성균관대학교", i -> 8 * i),
    KONKUK_UNIV(0,"건국대학교", i -> 7 * i),
    SEJONG_UNIV(0,"세종대학교", i -> 7 * i),
    KYUNGPOOK_UNIV(0,"경북대학교", i -> 7 * i),
    SEOUL_HIGH(0,"서울고등학겨", i -> 6 * i);

    private int code;
    private String name;
    private Function<Integer,Integer> score;

    public static EducationLevel findByCode(int code) {
        return Arrays.stream(EducationLevel.values()).filter(e -> Objects.equals(e.code, code))
                .findFirst().orElseThrow(()-> new RuntimeException("잘못된 코드값!!"));
    }

    public Integer getScore(Integer degree) {
        return score.apply(degree);
    }
}
