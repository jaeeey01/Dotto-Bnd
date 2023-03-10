package com.dotto.app.workplace.entity;

import com.dotto.app.member.entity.Member;
import com.dotto.app.workplace.dto.WorkPlaceCreateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wNo;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column
    private String workTime;

    @OneToMany(mappedBy = "workPlace", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<WorkPlaceImage> workImg;

    @Column
    private String address;

    @Column
    private String subAddress;

    public WorkPlace(Member member, WorkPlaceCreateRequest req, List<WorkPlaceImage> images){
        this.member = member;
        this.workTime = req.getWorkTime();
        this.workImg = new ArrayList<>();
        addImage(images);
        this.address = req.getAddress();
        this.subAddress = req.getSubAddress();
    }

    private void addImage(List<WorkPlaceImage> added){
        added.stream().forEach(i -> {workImg.add(i);
            i.initWorkPlace(this);
        });
    }

}
