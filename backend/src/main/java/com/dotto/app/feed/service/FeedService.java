package com.dotto.app.feed.service;

import com.dotto.app.member.entity.Member;
import com.dotto.app.feed.entity.Feed;
import com.dotto.app.feed.entity.FeedImage;
import com.dotto.app.exception.FeedNotFoundException;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.feed.dto.*;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.feed.repository.FeedRepository;
import com.dotto.app.common.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class FeedService {

    private final MemberRepository memberRepository;
    private final FeedRepository feedRepository;
//    private final FileService fileService;


    public FeedListDto readAll (FeedReadCondition cond){
        Pageable pageable = PageRequest.of(cond.getPage(), cond.getSize());
        Page<Feed> feedPage = feedRepository.findAll(pageable);
        List<FeedPostDto> postDto = feedPage.getContent().stream().map(feed -> FeedPostDto.toDto(feed.getFeedNo(),feed.getContent(),feed.getLikeHit(),feed.getMember().getMemberNo())).collect(Collectors.toList());
        return FeedListDto.toDto(feedPage, postDto);
    }

    public FeedDetailDto read (Long feedNo){
        return FeedDetailDto.toDto(feedRepository.findByFeedNoWithDeletedYnEqualsN(feedNo).orElseThrow(FeedNotFoundException::new));
    }
    @Transactional
    public FeedCreateResponse create (FeedCreateRequest req){
        log.info("memberNo = {}", req.getMemberNo());
//        List<FeedImage> feedImages = req.getFeedImg().stream().map(i -> new FeedImage(i.getOriginalFilename())).collect(Collectors.toList());
        List<FeedImage> feedImages = IntStream.range(0, req.getFeedImg().size()).mapToObj(i -> new FeedImage(req.getFeedImg().get(i))).collect(Collectors.toList());
        Member member = memberRepository.findById(req.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        Feed feed = feedRepository.save(new Feed(member, req.getContent(), feedImages));
//        uploadImage(feed.getFeedImages(), req.getFeedImg());

        return new FeedCreateResponse(feed.getFeedNo());
    }

    @Transactional
    public FeedUpdateResponse update(Long feedNo, FeedUpdateRequest req){
        Feed feed = feedRepository.findByFeedNoWithDeletedYnEqualsN(feedNo).orElseThrow(FeedNotFoundException::new);
        Feed.FeedImageUpdateResult rs = feed.update(req);
//        uploadImage(rs.getAddedImages(), rs.getAddedImageFiles());
//        deletedImage(rs.getDeletedImages());
        return new FeedUpdateResponse(feed.getFeedNo());
    }

    @Transactional
    public void delete (Long feedNo){
        Feed feed = feedRepository.findByFeedNoWithDeletedYnEqualsN(feedNo).orElseThrow(FeedNotFoundException::new);
        feed.deleted();
//        deletedImage(feed.getFeedImages());
    }

//    private void uploadImage(List<FeedImage> images, List<MultipartFile> files){
//        IntStream.range(0, images.size()).forEach(i -> fileService.upload(files.get(i), images.get(i).getName()));
//
//    }

//    private void deletedImage(List<FeedImage> images){
//        IntStream.range(0, images.size()).forEach(i -> fileService.deleted(images.get(i).getName()));
//    }


}
