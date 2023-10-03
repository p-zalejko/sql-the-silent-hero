-- JSON without JSON columns


SELECT row_to_json(film)
FROM film
WHERE film_id = 1;


SELECT json_agg(json_build_object('id',film_id,
                                  'title',title,
                                  'length',length
    ))  from film WHERE film_id = 1;

SELECT json_agg(e)
FROM (SELECT film_id, title, length
      FROM film
      WHERE length > 60) e;