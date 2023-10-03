<!-- .slide: style="text-align: center !important;"  -->


<div style="border-radius: 5px; border: 4px solid #777;background-color: rgba(255,255,255,0.65); margin-right: 200px; margin-left: 200px">
  <br/>
  <h1 style="margin: 0 0 0 0; color: #5b5a5a;" >SQL</h1> 
  <h3>the silent hero</h3>
  <br/>
</div>



# What we will see

➡️ 📃 a bit of history

➡️ 🤹 less known features

➡️ 🛠  SQL+Java in a different way



<!-- .slide: style="text-align: center !important;"  -->

# Aaccording to https://db-engines.com

- <a href="https://db-engines.com/en/systems">there are 479 database engines</a>
- <a href="https://db-engines.com/en/ranking/relational+dbms">and 164 are Relational DBMS</a>



<!-- .slide: style="text-align: center !important;"  -->

# Top 10

  <img src="images/most-popular-sql-dbs.png"/>



<!-- .slide: style="text-align: center !important;"  -->

# One <s>ring</s> SQL to rule them all




# SQL - How it started

- **early 1970s** - SEQUEL (*Structured English Query Language*) developed by IBM
- **1979** - the first commercial implementation of SQL (*Structured Query Languag*)
- **1986** - adopted as a standard by the ANSI as SQL-86

source: 
- https://docs.oracle.com/cd/B13789_01/server.101/b10759/intro001.htm
- https://en.wikipedia.org/wiki/SQL



# SQL - How it's going


<div class="container">

<div class="column">
 <strong>SQL-86 & 89</strong><br/>
	<ul>
		<li>SELECT</li>
		<li>FROM</li>
		<li>WHERE</li>
		<li>GROUP BY</li>
		<li>HAVING</li>
		<li>INSERT</li>
		<li>UPDATE</li>
		<li>DELETE</li>
		<li>UNIQUE</li>
		<li>NOT NULL</li>
	</ul>
</div>

<div class="column">
 <strong>SQL-92</strong><br/>
	<ul>
		<li>JOIN</li>
		<li>INNER JOIN</li>
		<li>LEFT JOIN</li>
		<li>RIGHT JOIN</li>
		<li>FULL JOIN</li>
		<li>NATUTAL JOIN</li>
		<li>CROSS JOIN</li>
		<li>UNION</li>
		<li>CASE</li>
		<li>ALTER TABLE</li>
	</ul>
</div>


<div class="column">
	<strong>SQL:1999</strong><br/>
	<ul>
	<li>RECURSIVE CTE</li>
	<li>GROUP BY ROLLUP</li>
	<li>GROUP BY CUBE</li>
	<li>GROUPING SETS</li>
	</ul>
</div>

<div class="column">
	<strong>SQL:2003+</strong><br/>
	<ul>
	<li>window functions</li>
	<li>...</li>
	<li>...</li>
	<li>...</li>
	</ul>
</div>

</div>



<!-- .slide: style="text-align: center !important;"  -->

# SQL is a declarative language


  <pre><code data-trim data-noescape>
SELECT column_a,column_b FROM t1
 JOIN t2 ON t1.column_a = t2.column_a
 WHERE constraint_expression
 GROUP BY col_name
 HAVING constraint_expression
 ORDER BY column ASC/DESC
  </code></pre>


<div class="fragment" data-fragment-index="1">
the question is:
</br>what is the true execution order?
</div>



<!-- .slide: style="text-align: center !important;"  -->

# SQL Execution Order 

<img src="images/sql_order.png" height="500"/>

<a href="https://dev.to/kanani_nirav/secret-to-optimizing-sql-queries-understand-the-sql-execution-order-28m1">source</a>



<!-- .slide: style="text-align: center !important;"  -->

# Query execution planner
### a place where it gets optimized...

 - generates possible plans (*paths*)
 - searches for the best one:
   - <a href="https://www.postgresql.org/docs/current/planner-optimizer.html"> by indexes</a>
   - <a href="https://www.postgresql.org/docs/current/planner-optimizer.html">by join strategies</a>
   - <a href="https://www.postgresql.org/docs/current/how-parallel-query-works.html">by paraller queries</a>
   - <a href="https://www.postgresql.org/docs/current/runtime-config-query.html#RUNTIME-CONFIG-QUERY-CONSTANTS">by cost constants</a>
   - <a href="https://www.postgresql.org/docs/current/runtime-config-resource.html">by resource consumption settings</a>
   - <a href="https://www.postgresql.org/docs/current/planner-stats.html">by statistics</a>




<!-- .slide: style="text-align: center !important;"  -->

#  Almost like JVM runtime optimizations
- <a href="https://www.ibm.com/docs/en/sdk-java-technology/8?topic=reference-jit-compiler">Just-In-Time (JIT) Compilationg</a>
- <a href="https://medium.com/codex/method-inlining-in-java-84caec9b3e18">Method Inlining</a>
- <a href="https://www.geeksforgeeks.org/dead-code-elimination/">Dead Code Elimination</a>
- <a href="https://blogs.oracle.com/javamagazine/post/loop-unrolling">Loop Unrolling</a>
- <a href="https://blogs.oracle.com/javamagazine/post/escape-analysis-in-the-hotspot-jit-compiler">Escape Analysis</a>
- <a href="https://martin.uy/blog/class-hierarchical-analysis-cha-examples-in-c1-hotspot-jvm-part-1">Class Hierarchical Analysis (CHA) </a>




<!-- .slide: style="text-align: center !important;"  -->

# just write better code!




<!-- .slide: style="text-align: center !important;"  -->

# demo
(Sakila Sample Database)

<img src="images/sakila.png" height="500"/>




<!-- .slide: style="text-align: center !important;"  -->

# demo
(Sakila Sample Database)

<img src="images/sakila-part.png" height="500"/>




<!-- .slide: style="text-align: center !important;"  -->

# demo




<!-- .slide: style="text-align: center !important;"  -->

# JSONs in databases

- <a href="https://www.postgresql.org/docs/current/datatype-json.html">PostgreSQL</a>
- <a href="https://dev.mysql.com/doc/refman/8.0/en/json.html">MySQL</a>
- <a href="https://learn.microsoft.com/en-us/sql/relational-databases/json/json-data-sql-server?view=sql-server-ver15">Microsoft SQL Server</a>
- <a href="https://blogs.oracle.com/database/post/json-datatype-support-in-oracle-21c">Oracle</a>
- <a href="https://www.sqlite.org/json1.html">SQLite</a>
- <a href="https://docs.aws.amazon.com/redshift/latest/dg/json-functions.html">Amazon Redshift</a>




<!-- .slide: style="text-align: center !important;"  -->

# REST by databases

- https://postgrest.org/en/stable/
- https://www.oracle.com/database/technologies/appdev/rest.html




<!-- .slide: style="text-align: center !important;"  -->


# THANK YOU!
## Q&A