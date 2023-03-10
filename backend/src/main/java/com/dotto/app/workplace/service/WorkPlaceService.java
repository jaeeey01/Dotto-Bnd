package com.dotto.app.workplace.service;

import com.dotto.app.workplace.dto.WorkPlaceCreateRequest;
import com.dotto.app.workplace.dto.WorkPlaceCreateResponse;
import com.dotto.app.member.entity.Member;
import com.dotto.app.config.constants.RoleType;
import com.dotto.app.workplace.entity.WorkPlace;
import com.dotto.app.workplace.entity.WorkPlaceImage;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.exception.MemberRoleAuthorizationException;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.workplace.repository.WorkplaceRepository;
import com.dotto.app.common.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class WorkPlaceService {

    private final WorkplaceRepository workplaceRepository;
    private final MemberRepository memberRepository;

//    private final FileService fileService;


    @Transactional
    public WorkPlaceCreateResponse create (WorkPlaceCreateRequest req){
        Member member = memberRepository.findByMemberNoAndDeletedYnEqualsN(req.getMemberNo()).orElseThrow(MemberNotFoundException::new);

        log.info("workPlace memberNo, memberRoleType ={}, {}",member.getMemberNo(), member.getRoles().stream().map(r -> r.getRole()).map(role -> role.getRoleType()).collect(Collectors.toList()));
        //임시 권한 제어 로직
        if(!artistCheck(member)) {
            log.info("artistCheck ={}" ,artistCheck(member));
            throw new MemberRoleAuthorizationException("내 작업실을 작성할 권한이 없습니다.");
        }
        log.info("artistCheck ={}" ,artistCheck(member));
//        List<WorkPlaceImage> images = req.getWorkImg().stream().map(i -> new WorkPlaceImage(i.getOriginalFilename())).collect(Collectors.toList());
        List<WorkPlaceImage> images = IntStream.range(0, req.getWorkImg().size()).mapToObj(i -> new WorkPlaceImage(req.getWorkImg().get(i))).collect(Collectors.toList());

        WorkPlace wk = workplaceRepository.save(new WorkPlace(member, req, images));
//        uploadImages(images, req.getWorkImg());

        return new WorkPlaceCreateResponse(wk.getMember().getMemberNo());

    }

    private boolean artistCheck(Member member){
       return member.getRoles().stream().map( r -> r.getRole()).map( role -> role.getRoleType()).anyMatch(roleType -> roleType.equals(RoleType.ROLE_ARTIST));

    }

//    private void uploadImages(List<WorkPlaceImage> images, List<MultipartFile> fileImages){
//        IntStream.range(0, images.size()).forEach(i -> fileService.upload(fileImages.get(i), images.get(i).getName()));
//    }

}
