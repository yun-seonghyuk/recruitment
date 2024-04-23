package com.zerobase.recruitment.entity;

import com.zerobase.recruitment.enums.ResumeStatus;
import com.zerobase.recruitment.utils.EducationListJsonConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/*
* 이력서 entity
* */

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long id;

    private String title;

    @Convert(converter = EducationListJsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Education> education;

    @Enumerated(EnumType.STRING)
    private ResumeStatus status;

    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @CreationTimestamp
    private LocalDateTime postingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    Resume(
            String title,
            List<Education> educationList,
            String loginId
    ){
        this.title = title;
        this.education = educationList;
    }

    public void open(){
        this.status = ResumeStatus.OPEN;
    }
}
