CREATE TABLE film_extra_stats
(
    film_id       INT PRIMARY KEY,
    stats JSONB
);

-- indexes in postgresql:
--- https://www.postgresql.org/docs/current/indexes-types.html

CREATE INDEX ON film_extra_stats((stats->>'avgRating'));
CREATE INDEX ON film_extra_stats((stats->>'productionCompany'));


INSERT INTO film_extra_stats (film_id, stats)
VALUES
    (1, '{ "watchedTotal": 10, "avgRating": 5, "productionCompany": "Company A" }'),
    (2, '{ "watchedTotal": 50, "avgRating": 8, "productionCompany": "Company B" }'),
    (3, '{ "watchedTotal": 14, "avgRating": 6, "productionCompany": "Company C" }');

