package com.dotto.app.repository.genre;

import com.dotto.app.entity.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    boolean findByGenreNameExists(String genreName);
}
