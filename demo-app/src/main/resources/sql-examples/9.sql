-- JSON columns

-- PostgreSQL offers two types for storing JSON data: json and jsonb.

--- The json data type stores an exact copy of the input text
--- jsonb data is stored in a decomposed binary format
-- -jsonb also supports indexing, which can be a significant advantage


--------------------

SELECT film_id,
       stats ->> 'watchedTotal' as watchedTotal
FROM film_extra_stats;


-------------------
-- with index scan

EXPLAIN (ANALYSE)
SELECT film_id,
       stats ->> 'watchedTotal' as watchedTotal,
       stats ->> 'avgRating'    as avgRating
FROM film_extra_stats
where stats ->> 'avgRating' > '5';


-- without index scan

EXPLAIN (ANALYSE)
SELECT film_id,
       stats ->> 'watchedTotal' as watchedTotal,
       stats ->> 'avgRating'    as avgRating
FROM film_extra_stats
where stats ->> 'watchedTotal' > '5';