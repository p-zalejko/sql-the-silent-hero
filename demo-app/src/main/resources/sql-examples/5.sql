-- windows

SELECT
    customer_id,
    amount,
    payment_date
FROM public.payment WHERE  customer_id = 1

SELECT
    customer_id,
    amount,
    payment_date,
    SUM(amount) OVER(partition by customer_id)
FROM public.payment ORDER BY customer_id ASC


SELECT customer_id,
       amount,
       to_char(payment_date, 'DD Mon YYYY'),
       SUM(amount) OVER(partition by customer_id ORDER By payment_date)
FROM public.payment ORDER BY customer_id ASC


-- TODO
-- more examples with window functions (e.g. 2 more)



SELECT
    film_id,
    title,
    rental_duration,
    SUM(rental_duration) OVER (ORDER BY film_id) AS cumulative_duration
FROM
    film;

SELECT
    film_id,
    title,
    rental_rate,
    AVG(rental_rate) OVER (PARTITION BY film_id) AS avg_category_rental_rate
FROM
    film;