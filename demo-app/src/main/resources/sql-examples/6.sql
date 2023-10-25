-- RANK and DENSE_RANK

SELECT customer_id,
       amount,
       RANK() OVER (ORDER BY amount DESC) as ranking
FROM payment ORDER BY ranking;

SELECT customer_id,
       amount,
       DENSE_RANK() OVER (ORDER BY amount DESC) as ranking
FROM payment ORDER BY ranking;

WITH byRanking AS
         (
             SELECT 
                customer_id,
                amount, 
                RANK() OVER (ORDER BY amount DESC) as ranking 
            FROM public.payment ORDER BY ranking
         )

SELECT * from byRanking WHERE ranking = 1;