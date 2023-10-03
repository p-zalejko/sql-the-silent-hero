-- JOIN 
SELECT
    cat.name,
    count(fCategory.film_id)
FROM film_category fCategory
    JOIN category cat ON cat.category_id = fCategory.category_id
    GROUP BY cat.category_id;


-- JOIN with 'using'
SELECT
    name,
    count(film_id)
FROM film_category
    JOIN category USING (category_id)
    GROUP BY category_id;