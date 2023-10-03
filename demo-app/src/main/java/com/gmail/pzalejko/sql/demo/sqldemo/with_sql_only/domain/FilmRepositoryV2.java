package com.gmail.pzalejko.sql.demo.sqldemo.with_sql_only.domain;

import java.util.Optional;

// a simple interface, it's not a spring repository!
public interface FilmRepositoryV2 {

    Optional<String> findByTitle(String name);

    Optional<String> findById(int id);

    String findAllLongerThan(int length);

    String getFilmDetails(int id);
}
