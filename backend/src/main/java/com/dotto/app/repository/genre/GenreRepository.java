package com.dotto.app.repository.genre;

import com.dotto.app.entity.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    boolean findByGenreNameExists(String genreName);

    Optional<Genre> findByGenreName(String genreName);

    @Query("update Genre g set g.genreName = :updateGenreName where g.genreName = :genreName")
    boolean updateGenreName(String genreName, String updateGenreName);
}
