-- imagine, we have to call one and the same query many times...

-- prepared statements

-- When the PREPARE statement is executed, the specified statement is parsed, analyzed, and rewritten
-- When an EXECUTE command is subsequently issued, the prepared statement is planned and executed


PREPARE findByCategory (text) AS
    SELECT
        category.category_id,
        category.name,
        film.film_id,
        film.title
    FROM film_category
             JOIN category USING (category_id)
             JOIN film USING (film_id)
    WHERE name = $1;

-- DEALLOCATE findByCategory;


EXECUTE findByCategory('Comedy');
EXECUTE findByCategory('Sci-Fi');

EXPLAIN (ANALYSE ) EXECUTE findByCategory('Documentary');

EXPLAIN (ANALYSE )SELECT
    category.category_id,
    category.name,
    film.film_id,
    film.title
FROM film_category
    JOIN category USING (category_id)
    JOIN film USING (film_id)
    WHERE category.name = 'Documentary'







-- generic plan vs custom plan
