package com.dotto.app;

import com.dotto.app.config.constants.RoleType;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.exception.RoleNotFoundException;
import com.dotto.app.feed.entity.Feed;
import com.dotto.app.feed.repository.FeedRepository;
import com.dotto.app.follow.entity.Follow;
import com.dotto.app.follow.repository.FollowRepository;
import com.dotto.app.genre.entity.Genre;
import com.dotto.app.genre.repository.GenreRepository;
import com.dotto.app.member.entity.Member;
import com.dotto.app.member.entity.Role;
import com.dotto.app.member.repository.MemberRepository;
import com.dotto.app.member.repository.RoleRepository;
import com.dotto.app.policy.entity.Policy;
import com.dotto.app.policy.repository.PolicyRepository;
import com.dotto.app.post.entity.DottoPost;
import com.dotto.app.post.entity.Image;
import com.dotto.app.post.repository.DottoPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("local")
public class InitDB {

    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;
    private final DottoPostRepository dottoPostRepository;
    private final FeedRepository feedRepository;
    private final PolicyRepository policyRepository;
    private final PasswordEncoder passwordEncoder;
    private final FollowRepository followRepository;

    private final ResourceLoader resourceLoader;

    private final GenreRepository genreRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDB() throws IOException {
        initRole();
        initMember();
        initDottoPost();
        initFeed();
        initPolicy();
        initFollow();
        initGenre();
        log.info("initialize database");
    }

    private void initRole() {
        roleRepository.saveAll(
                List.of(RoleType.values()).stream().map(roleType -> new Role(roleType)).collect(Collectors.toList())
        );
    }

    private void initMember(){
        String googleLoginType = "google";
        String kakaoLoginType = "kakao";
        memberRepository.saveAll(
                List.of(new Member("member1", passwordEncoder.encode("1234"), "nickname", "male", "01012345678"
                                , List.of(roleRepository.findByRoleType(RoleType.ROlE_NORMAL).orElseThrow(RoleNotFoundException::new)),googleLoginType,"",""
                        ),
                        new Member("member2", passwordEncoder.encode("1234"), "nickname2", "female", "01012345678"
                                , List.of(roleRepository.findByRoleType(RoleType.ROLE_ARTIST).orElseThrow(RoleNotFoundException::new)),kakaoLoginType,"",""
                        ),
                        new Member("member3", passwordEncoder.encode("1234"), "nickname3", "none", "01012345678"
                                ,List.of(roleRepository.findByRoleType(RoleType.ROLE_ADMIN).orElseThrow(RoleNotFoundException::new)),kakaoLoginType,"",""
                        )

                )
        );
        //dummy member
        IntStream.range(0, 24)
                .forEach( i -> memberRepository.save(
                        new Member("member10"+i, passwordEncoder.encode("1234"),"nickname10"+i,
                                "gender","01012345678", List.of(roleRepository.findByRoleType(RoleType.ROLE_ARTIST).orElseThrow(RoleNotFoundException::new)),kakaoLoginType,"",""))
                );
    };

    private void initDottoPost(){

        String tags = "멋져, 훈남, 최고";

        dottoPostRepository.saveAll(
                List.of(new DottoPost(memberRepository.findById("member1").orElseThrow(MemberNotFoundException::new),
                                "title1","content1","10000","9000","Y","블랙엔그레이","30",tags,"10%", List.of(new Image("test1.jpeg"))),
                        new DottoPost(memberRepository.findById("member2").orElseThrow(MemberNotFoundException::new),
                                "title2","content2","20000", "18000", "N","올드스쿨","180",tags,"",List.of(new Image("test2-1.jpeg"), new Image("test2-2.jpg"))),
                        new DottoPost(memberRepository.findById("member3").orElseThrow(MemberNotFoundException::new),
                                "title3","content3","30000","27000","N","이레즈미","270","","",List.of(new Image("test3-1.jpeg"), new Image("test3-2.jpg"), new Image("test3-3.png")))

                )

        );


        //dummy post
        IntStream.range(0, 24)
                .forEach(i-> dottoPostRepository.save(
                        new DottoPost(memberRepository.findById("member10"+i).orElseThrow(MemberNotFoundException::new),
                                "title10"+i,"content10"+i, "10000","9000","Y","레터링", "111", "멋져"+i+", 훈남"+i+", 최고"+i,"10%", List.of(new Image("dummy"+i+".jpg"
                        )))));
    };

    private void initFeed(){
        feedRepository.saveAll(
                List.of(new Feed(memberRepository.findById("member1").orElseThrow(MemberNotFoundException::new), "피드내용1", List.of()),
                        new Feed(memberRepository.findById("member2").orElseThrow(MemberNotFoundException::new), "피드내용2", List.of()),
                        new Feed(memberRepository.findById("member3").orElseThrow(MemberNotFoundException::new), "피드내용3", List.of()))
        );
    }

    private void initPolicy() throws IOException {

        String dottoPolicyContent = readFileAsString("/policy/dottoPolicyContent.txt");
        String marketingPolicyContent = readFileAsString("/policy/marketingPolicyContent.txt");
        String privatePolicyContent = readFileAsString("/policy/privatePolicyContent.txt");

        policyRepository.save(new Policy(dottoPolicyContent, privatePolicyContent, marketingPolicyContent));

    }

    private void initFollow(){
        LongStream.range(1,11).forEach(i -> followRepository.saveAll(
                List.of(
                        new Follow(memberRepository.findByMemberNoAndDeletedYnEqualsN(i).orElseThrow(MemberNotFoundException::new),
                                memberRepository.findByMemberNoAndDeletedYnEqualsN(i+1).orElseThrow(MemberNotFoundException::new)
                        )
                )
        ));

        followRepository.saveAll(
                List.of(
                        new Follow(memberRepository.findByMemberNoAndDeletedYnEqualsN(3L).orElseThrow(MemberNotFoundException::new),
                                memberRepository.findByMemberNoAndDeletedYnEqualsN(2L).orElseThrow(MemberNotFoundException::new)
                        ),
                        new Follow(memberRepository.findByMemberNoAndDeletedYnEqualsN(4L).orElseThrow(MemberNotFoundException::new),
                                memberRepository.findByMemberNoAndDeletedYnEqualsN(2L).orElseThrow(MemberNotFoundException::new)
                        )
                ));

    }

    private void initGenre(){
        LongStream.range(1,2).forEach(i -> genreRepository.saveAll(
                List.of(
                        new Genre("이레즈미"),
                        new Genre("올드스쿨"),
                        new Genre("뉴스쿨")
                )
        ));
    }

    private String readFileAsString(String fileName) throws IOException {
        String br;
        //도커용
//        ClassPathResource classPathResource = new ClassPathResource("classpath:"+fileName);

//        if(!classPathResource.exists()){
//            throw new IllegalArgumentException();
//        }

//          br = new BufferedReader(new InputStreamReader(classPathResource.getInputStream())).readLine();

        //로컬용
        File resource = ResourceUtils.getFile("classpath:"+fileName.replaceFirst("/",""));
        br = new BufferedReader(new FileReader(resource)).readLine();

        return br;
//test
    }

}
