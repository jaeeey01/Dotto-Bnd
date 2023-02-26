package com.dotto.app.search.service;

import com.dotto.app.search.dto.ArtistList;
import com.dotto.app.search.dto.DottoPostList;
import com.dotto.app.search.dto.MemberList;
import com.dotto.app.search.dto.SearchResponse;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.post.repository.DottoPostRepository;
import com.dotto.app.image.repository.ImageRepository;
import com.dotto.app.search.service.convert.ImgNoPostNoConvert;
import com.dotto.app.search.service.convert.PostNoConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final MemberRepository memberRepository;
    private final DottoPostRepository dottoPostRepository;
    private final ImageRepository imageRepository;


    public SearchResponse search(String searchKeyword){
        List<MemberList> normal = memberRepository.findBySearchNormalNickname(searchKeyword);
        List<ArtistList> artist = memberRepository.findBySearchArtistNickname(searchKeyword);
        List<DottoPostList> dottoPosts = searchDottoPosts(searchKeyword);

        return new SearchResponse( normal, artist , dottoPosts);
    }


    private List<DottoPostList> searchDottoPosts(String searchKeyword){
        List<PostNoConvert> searchTitleToPostNo = dottoPostRepository.findBySearchTitleToDottoPosts(searchKeyword);
        List<Long> searchPostNo = searchTitleToPostNo.stream().map(PostNoConvert::getPostNo).collect(Collectors.toList());
        List<ImgNoPostNoConvert> searchImgNo = imageRepository.findTitleToImages(searchPostNo);

        return dottoPostRepository.findBySearchPostNotoDottoPosts(searchImgNo.stream().map(ImgNoPostNoConvert::getImgNo).collect(Collectors.toList()));
    }
}
