-- windows

SELECT
    customer_id,
    amount,
    payment_date
FROM public.payment WHERE  customer_id = 1;

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





