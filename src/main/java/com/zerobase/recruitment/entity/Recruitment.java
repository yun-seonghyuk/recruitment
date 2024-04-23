package com.zerobase.recruitment.entity;

import com.zerobase.recruitment.dto.RecruitmentDto;
import com.zerobase.recruitment.enums.RecruitmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/*
* 공고 entity
* */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private Long id;
    private String title;
    private Integer recruiterCount;
    private LocalDateTime closingDate;

    @Enumerated(EnumType.STRING)
    private RecruitmentStatus status;

    @UpdateTimestamp
    private LocalDateTime modifyDate;
    @CreationTimestamp
    private LocalDateTime postingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_member_id")
    private CompanyMember companyMember;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitment")
    private List<Application> applicationList;


    @Builder
    public Recruitment(
            String title,
            Integer recruiterCount,
            LocalDateTime closingDate
    ){
        this.title = title;
        this.recruiterCount = recruiterCount;
        this.closingDate = closingDate;
    }

    public void opening(){
        this.status = RecruitmentStatus.OPEN;
    }

    public void closing(){
        this.status = RecruitmentStatus.CLOSE;
    }

    public RecruitmentDto.Response toDto(){
        return RecruitmentDto.Response.builder()
                .recruitmentId(this.id)
                .title(this.title)
                .recruiterCount(this.recruiterCount)
                .closingDate(this.closingDate)
                .status(this.status)
                .modifiedDate(this.modifyDate)
                .postingDate(this.postingDate)
                .companyMemberId(this.companyMember.getId())
                .companyName(this.companyMember.getCompanyName())
                .build();
    }

    public Recruitment update(RecruitmentDto.Request request){
        this.title = request.title();
        this.recruiterCount = request.recruiterCount();
        this.closingDate = request.closingDate();
        this.status = request.status();
        return  this;
    }
}
