-- imagine we have a complex query (many joins, aggregations etc.)
-- we don't want to call it with different parameters so using prepared statements isn't an option here...

-- just a query
SELECT
    category.category_id,
    category.name,
    count(film_category.film_id) "how_many"
FROM film_category
    JOIN category USING (category_id)
    GROUP BY category.category_id
    ORDER BY how_many DESC;


-- view
DROP VIEW IF EXISTS film_category_count_view;
CREATE VIEW film_category_count_view AS
    SELECT
        category.category_id,
        category.name,
        count(film_category.film_id) "how_many"
    FROM film_category
             JOIN category USING (category_id)
    GROUP BY category.category_id
    ORDER BY how_many DESC;

SELECT * from film_category_count_view;
-- EXPLAIN (ANALYSE ) SELECT * from film_category_count_view;







-- materialized view
DROP MATERIALIZED VIEW IF EXISTS film_category_count_mw;

CREATE MATERIALIZED VIEW film_category_count_mw
AS
    SELECT
        category.category_id,
        category.name,
        count(film_category.film_id) "how_many"
    FROM film_category
             JOIN category USING (category_id)
    GROUP BY category.category_id
    ORDER BY how_many DESC
WITH NO DATA;

SELECT * FROM  film_category_count_mw;
-- EXPLAIN (ANALYSE ) SELECT * FROM  film_category_count_mw;

-- an index on the view
CREATE UNIQUE INDEX film_name_idx
    ON film_category_count_mw (name);

-- reload data
REFRESH MATERIALIZED VIEW film_category_count_mw;



-- view vs materialized view
--- on-the-fly vs persisted
--- up-to-date vs not up-to-date
--- indexes
--- can cause performance problems
