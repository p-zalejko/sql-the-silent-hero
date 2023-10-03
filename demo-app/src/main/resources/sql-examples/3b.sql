-- Need IF-THEN logic in #SQL?

WITH moviesWithLength AS (
    SELECT film_id,
           title,
           CASE
               WHEN length < 50 THEN
                   CASE
                       WHEN length < 30 THEN 'Very Short'
                       ELSE 'Short'
                       END
               WHEN length BETWEEN 51 AND 100 THEN 'Medium'
               ELSE 'Long'
               END AS Lenth
    FROM film
)

SELECT * from moviesWithLength WHERE Lenth = 'Short'
