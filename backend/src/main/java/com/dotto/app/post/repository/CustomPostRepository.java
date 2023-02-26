package com.dotto.app.post.repository;

import com.dotto.app.post.dto.DottoPostDto;
import com.dotto.app.post.dto.PostReadCondition;
import org.springframework.data.domain.Page;

public interface CustomPostRepository {

    Page<DottoPostDto> findAllCondition(PostReadCondition cond);
}
