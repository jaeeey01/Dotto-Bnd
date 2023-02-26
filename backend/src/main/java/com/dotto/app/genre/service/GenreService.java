package com.dotto.app.genre.service;

import com.dotto.app.genre.dto.GenreCreateRequest;
import com.dotto.app.genre.dto.GenreListResponse;
import com.dotto.app.genre.dto.GenreUpdateRequest;
import com.dotto.app.genre.dto.GenreDeleteRequest;
import com.dotto.app.genre.entity.Genre;
import com.dotto.app.exception.GenreNameExistsException;
import com.dotto.app.exception.GenreNotFoundException;
import com.dotto.app.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public GenreListResponse read(){
        List<Genre> genreLists = genreRepository.findAll();
        return new GenreListResponse(genreLists.stream().map(genre -> genre.getGenreName()).collect(Collectors.toList()));

    }
    

}
