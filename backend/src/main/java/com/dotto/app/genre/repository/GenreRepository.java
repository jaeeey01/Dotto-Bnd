package com.dotto.app.genre.repository;

import com.dotto.app.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    boolean existsByGenreName(String genreName);

    Optional<Genre> findByGenreName(String genreName);

    @Modifying
    @Query("update Genre g set g.genreName = :updateGenreName where g.Gno = :gno ")
    int updateGenreName(Long gno, String updateGenreName);
}
