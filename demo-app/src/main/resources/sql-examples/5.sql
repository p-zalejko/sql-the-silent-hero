-- windows

SELECT
    customer_id,
    amount,
    payment_date
FROM payment WHERE  customer_id = 1;

SELECT
    customer_id,
    amount,
    SUM(amount) OVER(partition by customer_id),
    payment_date
FROM payment ORDER BY customer_id ASC;


SELECT customer_id,
       amount,
       SUM(amount) OVER(partition by customer_id ORDER By payment_date),
       to_char(payment_date, 'DD Mon YYYY')
FROM public.payment ORDER BY customer_id ASC





