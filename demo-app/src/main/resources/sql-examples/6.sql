-- RANK
-- https://learnsql.com/blog/25-advanced-sql-query-examples/


SELECT customer_id,
       amount,
       RANK() OVER (ORDER BY amount DESC) as ranking
FROM public.payment ORDER BY ranking;


-- here we can use 'ranking` in WHERE
-- The WITH clause in the previous query creates a CTE called, which is a kind of virtual table that’s consumed in the main query.
WITH byRanking AS
         (
             SELECT 
                customer_id,
                amount, 
                RANK() OVER (ORDER BY amount DESC) as ranking 
            FROM public.payment ORDER BY ranking
         )

SELECT * from byRanking WHERE ranking < 100;