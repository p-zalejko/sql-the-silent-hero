package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.infrastructure;

import com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// a package-level scope, it's an implementation detail we don't want to show...
@Repository
interface SpringDataFilmRepository extends CrudRepository<Film, Integer> {

    Optional<Film> findByTitle(String title);

    List<Film> findAllByLengthGreaterThan(int length);

}
