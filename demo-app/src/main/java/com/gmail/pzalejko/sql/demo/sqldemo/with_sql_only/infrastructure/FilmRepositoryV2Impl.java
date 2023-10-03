package com.gmail.pzalejko.sql.demo.sqldemo.with_sql_only.infrastructure;

import com.gmail.pzalejko.sql.demo.sqldemo.with_sql_only.domain.FilmRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class FilmRepositoryV2Impl implements FilmRepositoryV2 {

    // db: https://www.jooq.org/img/sakila.png
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @SneakyThrows
    @Override
    public Optional<String> findByTitle(String title) {
        try {
            // row_to_json -> entire row is mapped to JSON, all fields...
            var namedParameters = new MapSqlParameterSource().addValue("title", title);
            var response = jdbcTemplate.queryForObject(
                    """
                            SELECT row_to_json(film)
                            FROM film
                            WHERE title = :title;
                            """,
                    namedParameters,
                    String.class
            );

            return Optional.ofNullable(response);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    @Override
    public Optional<String> findById(int id) {
        try {
            // custom "DTO"
            var namedParameters = new MapSqlParameterSource().addValue("id", id);
            var response = jdbcTemplate.queryForObject(
                    """
                            SELECT json_build_object('id',film_id,
                                                           'title',title,
                                                           'length',length
                             ) from film WHERE film_id = :id;
                              """,
                    namedParameters,
                    String.class
            );

            return Optional.ofNullable(response);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    // here a String, not a list!
    public String findAllLongerThan(int length) {
        try {
            // a list of objects, but as single String
            var namedParameters = new MapSqlParameterSource().addValue("length", length);
            var response =  jdbcTemplate.queryForObject(
                    """
                            SELECT json_agg(e)  FROM (
                                SELECT film_id, title, length
                                      FROM film
                                      WHERE length > :length
                                ) e;
                                           """,
                    namedParameters,
                    String.class
            );

            return Optional.ofNullable(response).orElse("[]");
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("something went wrong, does the film exist?", e);
        }
    }

    @Override
    public String getFilmDetails(int id) {
        try {
            var namedParameters = new MapSqlParameterSource().addValue("film_id", id);

            return jdbcTemplate.queryForObject(
                    """
                                    WITH
                                        -- 1st CTE: find by filmId
                                        FilmById AS (
                                            SELECT film_id, title, length FROM film WHERE film_id = :film_id
                                        ),
                                                                
                                        -- 2nd CTE: append category name (join two other tables)
                                        FilmWithCategory AS (
                                        SELECT
                                            f.film_id,
                                            f.title,
                                            f.length,
                                            c.name as "Cateory"
                                        FROM FilmById f
                                            LEFT JOIN film_category using (film_id)
                                            LEFT JOIN category c using (category_id)
                                        ),
                                                                
                                        -- 3rd CTE: add a lost of cast (join two other tables, concatenate columns)
                                        FilmWithCategoryAndActors AS (
                                            SELECT
                                                FilmWithCategory.film_id as "id",
                                                FilmWithCategory.title as "title",
                                                FilmWithCategory.length as "length",
                                                FilmWithCategory."Cateory",
                                                (Select count(*) from film_actor fa WHERE fa.film_id = FilmWithCategory.film_id) as "castCount", -- not so fast solution...
                                                STRING_AGG(a.first_name || ' ' || a.last_name, ', ') as "Cast"
                                            FROM FilmWithCategory
                                                LEFT JOIN film_actor fa using (film_id)
                                                LEFT JOIN actor a on a.actor_id = fa.actor_id
                                            group by FilmWithCategory.title, FilmWithCategory.length, FilmWithCategory."Cateory", FilmWithCategory.film_id
                                            order by "id"
                                       )
                                                                
                                    SELECT row_to_json(f) from FilmWithCategoryAndActors f;
                            """,
                    namedParameters,
                    String.class
            );
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("something went wrong, does the film exist?", e);
        }
    }
}
