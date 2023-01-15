package com.dotto.app.service.genre;

import com.dotto.app.dto.genre.GenreCreateRequest;
import com.dotto.app.dto.genre.GenreUpdateRequest;
import com.dotto.app.dto.genre.GerneDeleteRequest;
import com.dotto.app.entity.genre.Genre;
import com.dotto.app.exception.GenreNameExistsException;
import com.dotto.app.exception.GenreNotFoundException;
import com.dotto.app.repository.genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;


    @Transactional
    public boolean create (GenreCreateRequest req){

        if(genreRepository.findByGenreNameExists(req.getGenreName().trim())){
            throw new GenreNameExistsException();
        }

        genreRepository.save(new Genre(req.getGenreName()));

        return true;
    }

    @Transactional
    public boolean update(GenreUpdateRequest req){

        Genre genre = genreRepository.findByGenreName(req.getGenreName().trim()).orElseThrow(GenreNotFoundException::new);

        return genreRepository.updateGenreName(req.getGenreName(),req.getUpdateGenreName());

    }

    @Transactional
    public boolean delete(GerneDeleteRequest req){

        Genre genre = genreRepository.findByGenreName(req.getDeleteGenreName()).orElseThrow(GenreNotFoundException::new);

        genreRepository.deleteById(genre.getGno());

        return true;

    }
    

}
