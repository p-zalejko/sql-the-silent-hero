-- select in many places


-- SELECT <Attribute> "custom name"
-- FROM <Table>
-- WHERE <Conditions>

SELECT title "Film title",
       (Select c.name from language AS C WHERE c.language_id = f.language_id) "Language",
       length "Length"
FROM (Select * from film where film.film_id > 2) AS f
WHERE f.film_id IN (select film_id from film WHERE f.length >130)