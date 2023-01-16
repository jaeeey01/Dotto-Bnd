package com.dotto.app.service.genre;

import com.dotto.app.dto.genre.GenreCreateRequest;
import com.dotto.app.dto.genre.GenreUpdateRequest;
import com.dotto.app.dto.genre.GenreDeleteRequest;
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
    public boolean create (GenreCreateRequest req) {

        if(genreRepository.existsByGenreName(req.getGenreName())){
            throw new GenreNameExistsException();
        }

        genreRepository.save(new Genre(req.getGenreName()));

        return true;
    }

    @Transactional
    public boolean update(GenreUpdateRequest req){

        Genre genre = genreRepository.findByGenreName(req.getGenreName().trim()).orElseThrow(GenreNotFoundException::new);
        int rs = genreRepository.updateGenreName(genre.getGno(), req.getUpdateGenreName());


        return rs == 1;

    }

    @Transactional
    public boolean delete(GenreDeleteRequest req){

        Genre genre = genreRepository.findByGenreName(req.getGenreName()).orElseThrow(GenreNotFoundException::new);

        genreRepository.deleteById(genre.getGno());

        return true;

    }
    

}
