package com.dotto.app.post.service;

import com.dotto.app.member.entity.Member;
import com.dotto.app.config.constants.RoleType;
import com.dotto.app.post.entity.DottoPost;
import com.dotto.app.post.entity.Image;
import com.dotto.app.exception.DottoPostNotFoundException;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.exception.MemberRoleAuthorizationException;
import com.dotto.app.post.dto.*;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.post.repository.DottoPostRepository;
import com.dotto.app.member.repository.RoleRepository;
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
public class DottoPostService {

    private final MemberRepository memberRepository;
    private final DottoPostRepository dottoPostRepository;
    private final RoleRepository roleRepository;
//    private final FileService fileService;


    @Transactional
    public DottoPostCreateResponse create (DottoPostCreateRequest req){
        log.info("memberReq id  :: "+req.getMemberNo());
        Member member = memberRepository.findWithRolesByMemberNo(req.getMemberNo()).orElseThrow(MemberNotFoundException::new);
         if(!rolesArtistTF(member)){
             throw new MemberRoleAuthorizationException("권한이 없습니다");
         };

        String salesPct = salesPctCalc(req.getSalesYn(), req.getPrice(), req.getSalesPrice());

//        List<Image> images = req.getPostPhoto().stream().map(i -> new Image(i.getOriginalFilename())).collect(Collectors.toList());
       List<Image> images =  IntStream.range(0, req.getPostPhoto().size()).mapToObj(i -> new Image(req.getPostPhoto().get(i))).collect(Collectors.toList());
        String tags = tagsConvertString(req.getTags());
        DottoPost dottoPost = dottoPostRepository.save(
                new DottoPost(member, req.getTitle(),req.getContent(), req.getPrice(), req.getSalesPrice(),req.getSalesYn(),req.getGenre(),req.getTotalTime(),tags,salesPct, images)
        );
//        uploadImages(dottoPost.getImages(), req.getPostPhoto());

        return new DottoPostCreateResponse(dottoPost.getPostNo());
    }

    public DottoPostListDto readAll(PostReadCondition cond){
        return DottoPostListDto.toDto(
                dottoPostRepository.findAllCondition(cond)
        );
    }

    public DottoPostDetailDto read(Long id){
        return DottoPostDetailDto.toDto(dottoPostRepository.findByIdWithMemberAndDeletedN(id).orElseThrow(DottoPostNotFoundException::new));
    }

    @Transactional
    public DottoPostUpdateResponse update(Long id, DottoPostUpdateRequest req){
        DottoPost post = dottoPostRepository.findByIdWithMemberAndDeletedN(id).orElseThrow(DottoPostNotFoundException::new);
        String salesPct = salesPctCalc(req.getSalesYn(), req.getPrice(), req.getSalesPrice());
        String tags = tagsConvertString(req.getTags());
        DottoPost.ImageUpdateResult rs = post.update(req, salesPct, tags);
//        uploadImages(rs.getAddedImages(), rs.getAddedImageFiles());
//        deleteImages(rs.getDeletedImages());
        return new DottoPostUpdateResponse(id);
    }

    @Transactional
    public void delete(Long id){
        DottoPost post = dottoPostRepository.findByIdWithMemberAndDeletedN(id).orElseThrow(DottoPostNotFoundException::new);
        post.deleted();
//        deleteImages(post.getImages());
        dottoPostRepository.save(post);

    }

    private String tagsConvertString(List<String> tags){
       return tags.toString().replace("[","").replace("]","");
    }

    private String salesPctCalc(String salesYn, String price, String salesPrice){
        return salesYn.equals("Y")? Math.round(((1- (double) Integer.parseInt(salesPrice) / (double) Integer.parseInt(price)))*100)+"%":"";

    }

    private boolean rolesArtistTF(Member member){

        String memberRoles = String.valueOf(member.getRoles().stream()
                .map(memberRole -> memberRole.getRole())
                .map(role -> role.getRoleType())
                .map(roleType -> roleType.toString()).collect(Collectors.toList()));

        return memberRoles.contains(String.valueOf(RoleType.ROLE_ARTIST));
    }

//    private void uploadImages(List<Image> images, List<MultipartFile> fileImages){
//        IntStream.range(0, images.size()).forEach(i -> fileService.upload(fileImages.get(i), images.get(i).getName()));
//    }
//
//    private void deleteImages(List<Image> images){
//        images.stream().forEach(image -> fileService.deleted(image.getName()));
//    }
}
