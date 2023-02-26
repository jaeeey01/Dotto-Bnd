package com.dotto.app.member.entity;

import com.dotto.app.image.ImageCreateSupport;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage extends ImageCreateSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgNo;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String originName;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    public ProfileImage(String originName){
        this.name = generateName(extractExtension(originName));
        this.originName = originName;
    }

    public void initMember(Member member){
        if(this.member == null) this.member = member;
    }

    @Override
    protected String extractExtension(String originName) {
        return super.extractExtension(originName);
    }


    @Override
    protected String generateName(String extension) {
        return super.generateName(extension);
    }



}
