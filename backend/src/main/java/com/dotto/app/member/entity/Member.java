package com.dotto.app.member.entity;

import com.dotto.app.config.constants.RoleType;
import com.dotto.app.follow.entity.Follow;
import com.dotto.app.member.dto.MemberProfileUploadRequest;
import com.dotto.app.common.entity.EntityDate;
import com.dotto.app.policy.entity.PolicyAgree;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(  //연관된 엔티티들을 함께 조회 (roles)
    name = "Member.roles",
        attributeNodes = @NamedAttributeNode(value = "roles", subgraph = "Member.roles.role"),

        subgraphs = @NamedSubgraph(name = "Member.roles.role", attributeNodes = @NamedAttributeNode("role"))
)
public class Member extends EntityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberNo")
    private Long memberNo;

    @Column(nullable = false, unique = true)
    private String id;

    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String gender;

    @Column(nullable = false)
    private String phone;

    private String intro;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberRole> roles;

    @Column(nullable = false)
    private String loginType;


    @Column
    private String deletedYn;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "memberNo")
    private ProfileImage profileImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo")
    private PolicyAgree policyAgree;

    @OneToMany(mappedBy = "following")
    private List<Follow> following;

    @OneToMany(mappedBy = "follower")
    private List<Follow> follower;

    @Column
    private String contactType;

    @Column
    private String contactId;

    public Member(String id, String password, String nickname, String gender, String phone, List<Role> roles, String loginType, String contactType, String contactId){
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.roles = roles.stream().map(r -> new MemberRole(this,r )).collect(Collectors.toSet());
        this.loginType = loginType;
        this.deletedYn = "N";
        this.contactType = contactType;
        this.contactId = contactId;
    }

    public void update(String nickname, String intro){
        this.nickname = nickname;
        this.intro = intro;
    }

    public void roleSwitcher(){
        this.roles = roleSetSwitch();
    }

    public void roleSwitchArtist(){
        this.roles = roleSetArtist();
    }


    public ProfileImageUpdateResult uploadProfile(MemberProfileUploadRequest req){
        ProfileImageUpdateResult rs = findProfileUpdatedResult(req.getUploadProfile());
        addedProfileImage(convertFilesToImage(rs.getAddedImageFile()));
        return rs;
    }
    public void deletedProfile(ProfileImage deleteImage){
        deletedProfileImage(deleteImage);
    }
    private ProfileImageUpdateResult findProfileUpdatedResult(String addedFile){
        ProfileImage addedImages = convertFilesToImage(addedFile);
        return new ProfileImageUpdateResult(addedFile, addedImages);
    }

    public void deleted(){
        setDeleted();
    }

    private void setDeleted(){
        this.deletedYn = "Y";
    }

    private void addedProfileImage(ProfileImage images){
        images.initMember(this);
    }

    private void deletedProfileImage(ProfileImage deleteImages){
        if(deleteImages.equals(this.profileImage)) this.profileImage = null;
    }

    private ProfileImage convertFilesToImage(String file){
        return new ProfileImage(file);
    }

    @Getter
    @AllArgsConstructor
    public static class ProfileImageUpdateResult{
        private String addedImageFile;
        private ProfileImage addedImages;
    }

    private Set<MemberRole> roleSetSwitch(){
        return Collections.unmodifiableSet((Set<MemberRole>) new MemberRole(this, new Role(RoleType.ROLE_SWITCHER)));
    }
    private Set<MemberRole> roleSetArtist(){
        return  Collections.unmodifiableSet((Set<MemberRole>) new MemberRole(this, new Role(RoleType.ROLE_SWITCHER)));
    }
}
