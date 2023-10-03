-- Common Table Expressions (CTE)
---  temporary tables that exist just for one query

WITH "after_2000" AS (
         SELECT film_id,
                title,
                (Select c.name from language AS C WHERE c.language_id = p.language_id),
                release_year
          FROM film AS p WHERE p.release_year > 2000
)

SELECT * from after_2000