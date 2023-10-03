package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain;

import java.util.List;
import java.util.Optional;

// a simple interface, it's not a spring repository!
public interface FilmRepository {

    Optional<Film> findByTitle(String name);

    Optional<Film> findById(int id);

    List<Film> findAllLongerThan(int length);

    Film getFilmDetails(int id);
}
