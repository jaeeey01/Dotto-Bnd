package com.dotto.app.service.genre;

import com.dotto.app.dto.genre.GenreCreateRequest;
import com.dotto.app.entity.genre.Genre;
import com.dotto.app.exception.GenreNameExistsException;
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
    

}
