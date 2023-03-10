package com.dotto.app.feed.entity;

import com.dotto.app.feed.dto.FeedUpdateRequest;
import com.dotto.app.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Entity
@Getter
@NoArgsConstructor
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedNo;

    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member member;

    @Column(nullable = false)
    private String content;

    @Column
    private char deletedYn;

    @Column
    private int likeHit;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FeedImage> feedImages;

    public Feed(Member member, String content, List<FeedImage> feedImages){
        this.member = member;
        this.content = content;
        this.deletedYn = 'N';
        this.likeHit =  0;
        this.feedImages = new ArrayList<>();
        addedImages(feedImages);

    }

    public FeedImageUpdateResult update(FeedUpdateRequest req){
        this.content = req.getContent();
        FeedImageUpdateResult rs = findFeedImageUpdateResult(req.getAddedImg(), req.getDeletedImg());
        addedImages(rs.getAddedImages());
        deleteImages(rs.getDeletedImages());
        return rs;
    }

    public void deleted(){
        deletedY();
    }

    private char deletedY(){
        return  this.deletedYn = 'Y';
    }


    private FeedImageUpdateResult findFeedImageUpdateResult(List<String> addedImageFiles, List<Long> deletedImageIds){
        List<FeedImage> addedImages = convertImageFilesToImages(addedImageFiles);
        List<FeedImage> deletedImages = convertImageIdsToImages(deletedImageIds);
        return new FeedImageUpdateResult(addedImageFiles, addedImages, deletedImages);
    }


    private void addedImages(List<FeedImage> added){
        added.stream().forEach(feedImage -> {
            feedImages.add(feedImage);
            feedImage.initFeed(this);
        });
    }

    private void deleteImages(List<FeedImage> deletedImages){
        deletedImages.stream().forEach(image -> this.feedImages.remove(image));
    }

    private List<FeedImage> convertImageFilesToImages(List<String> imageFiles){
        return IntStream.range(0, imageFiles.size()).mapToObj(i -> new FeedImage(imageFiles.get(i))).collect(toList());
    }

    private List<FeedImage> convertImageIdsToImages(List<Long> imageIds){
        return imageIds.stream().map(id -> convertImageIdToImage(id))
                .filter(i -> i.isPresent())
                .map(i -> i.get())
                .collect(toList());
    }

    private Optional<FeedImage> convertImageIdToImage(Long imageIds){
        return this.feedImages.stream().filter(feedImage -> feedImage.getFeedImgNo().equals(imageIds)).findAny();
    }

    @Getter
    @AllArgsConstructor
    public static class FeedImageUpdateResult{
        private List<String> addedImageFiles;
        private List<FeedImage> addedImages;
        private List<FeedImage> deletedImages;
    }

}
