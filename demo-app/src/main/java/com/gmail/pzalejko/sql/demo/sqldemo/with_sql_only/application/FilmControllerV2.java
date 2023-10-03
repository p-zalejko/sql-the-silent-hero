package com.gmail.pzalejko.sql.demo.sqldemo.with_sql_only.application;

import com.gmail.pzalejko.sql.demo.sqldemo.with_sql_only.domain.FilmRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FilmControllerV2 {

    private final FilmRepositoryV2 repository;

    @GetMapping(value = "/v2/search/filmsByTitle/{title}", produces = "application/json")
    ResponseEntity<String> findByTitle(@PathVariable String title) {
        Optional<String> byTitle = repository.findByTitle(title);
        return byTitle
                // one step less (i.e. no mapping to DTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/v2/search/filmsById/{id}", produces = "application/json")
    ResponseEntity<String> findById(@PathVariable int id) {
        Optional<String> byTitle = repository.findById(id);
        return byTitle
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/v2/search/longerThan/{length}", produces = "application/json")
    ResponseEntity<String> findAllLongerThan(@PathVariable int length) {
        String items = repository.findAllLongerThan(length);
        return ResponseEntity.ok(items);
    }

    @GetMapping(value = "/v2/films/{id}", produces = "application/json")
    ResponseEntity<String> getById(@PathVariable int id) {
        String items = repository.getFilmDetails(id);
        return ResponseEntity.ok(items);
    }

    // no more DTO classes...

    // what happens then:
    //  AbstractMessageConverterMethodProcessor gets called
    //  it searches for matching HttpMessageConverter
    //  finds the StringHttpMessageConverter
    //  calls writeInternal method -> just sends a string ...
}
