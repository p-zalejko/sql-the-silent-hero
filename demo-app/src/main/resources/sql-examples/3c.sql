select * from category;

SELECT
    count(*) FILTER (WHERE o.category_id = 1) "Action movies",
    count(*) FILTER (WHERE o.category_id = 5) "Comedy movies"
FROM film_category o;