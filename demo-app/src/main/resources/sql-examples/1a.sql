-- why it's so wrong:
--- sometimes the database optimizer cannot choose index-only scans
--- Unnecessary IO (unnecessary data)
--- Increased network traffic
--- More application memory
--- Conflict in JOIN Query


SELECT * FROM film;

