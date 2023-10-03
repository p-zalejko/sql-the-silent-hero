-- Need IF-THEN logic in #SQL?

SELECT film_id,
       title,
       CASE
           WHEN length < 50 THEN 'Short'
           WHEN length BETWEEN 51 AND 100 THEN 'Medium'
           ELSE 'Long'
           END AS Lenth
FROM film;





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
FROM film;