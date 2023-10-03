package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.infrastructure;

import com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain.Film;
import com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class FilmRepositoryImplForV1 implements FilmRepository {

    private final SpringDataFilmRepository repository;

    @Override
    public Optional<Film> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public Optional<Film> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Film> findAllLongerThan(int length) {
        return repository.findAllByLengthGreaterThan(length);
    }

    @Override
    public Film getFilmDetails(int id) {
        // FIXME: what to do here? should the repo return some kind of view/dto/aggregate?
        //  let's return an entity and let the Controller build a DTO it needs...
        return findById(id).orElseThrow();
    }
}
