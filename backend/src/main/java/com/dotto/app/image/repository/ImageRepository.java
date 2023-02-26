package com.dotto.app.image.repository;

import com.dotto.app.post.entity.Image;
import com.dotto.app.search.service.convert.ImgNoPostNoConvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query("select new com.dotto.app.search.service.convert.ImgNoPostNoConvert (min(imgNo), dottoPost.postNo) from Image where dottoPost.postNo in(:postNo) group by dottoPost.postNo")
    List<ImgNoPostNoConvert> findTitleToImages(List<Long> postNo);
}
