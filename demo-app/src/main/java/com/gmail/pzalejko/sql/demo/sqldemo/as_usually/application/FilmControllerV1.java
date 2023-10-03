package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.application;

import com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain.Film;
import com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FilmControllerV1 {

    private final FilmRepository repository;

    @GetMapping("/v1/search/filmsByTitle/{title}")
    ResponseEntity<FilmDto> findByTitle(@PathVariable String title) {
        Optional<Film> byTitle = repository.findByTitle(title);
        return byTitle
                .map(FilmDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        // what happens then:

        //  AbstractMessageConverterMethodProcessor gets called
        //  it searches for matching HttpMessageConverter
        //  finds the MappingJackson2HttpMessageConverter
        //  calls writeInternal method -> converts an object to JSON...
    }

    @GetMapping("/v1/search/filmsById/{id}")
    ResponseEntity<FilmDto> findByTitle(@PathVariable int id) {
        Optional<Film> item = repository.findById(id);
        return item
                .map(FilmDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/v1/search/longerThan/{length}")
    ResponseEntity<List<FilmDto>> findAllLongerThan(@PathVariable int length) {
        var items = repository.findAllLongerThan(length);
        var dtos = items.stream()
                .map(FilmDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/v1/films/{id}")
    ResponseEntity<FilmDetailsDto> getById(@PathVariable int id) {
        Optional<Film> item = repository.findById(id);
        return item
                .map(FilmDetailsDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    record FilmDto(int id, String title, int length) {

        public FilmDto(Film film) {
            this(film.getId(), film.getTitle(), film.getLength());
        }
    }

    record FilmDetailsDto(int id, String title, int length, String category, int casCount, String cast) {

        public FilmDetailsDto(Film film) {
            this(
                    film.getId(),
                    film.getTitle(),
                    film.getLength(),
                    film.getFilmCategory().getCategory().getName(),
                    film.getActors().size(),
                    film.getActors().stream().map(i -> i.getActor().getFullName()).collect(Collectors.joining(", "))
            );
        }
    }
}
