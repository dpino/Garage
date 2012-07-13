Garage
******

Before deploying this project you need to create an empty database. Default database is postgreSQL.

Create database
---------------

<pre>
> CREATE USER garage WITH PASSWORD 'garage';
> CREATE DATABASE garagedev;
> GRANT ALL PRIVILEGES ON DATABASE garagedev TO garage;
</pre>


Compile & deploy
----------------

<pre>
$ mvn clean install
$ mvn jetty:stop jetty:run
</pre>

Open website at localhost:8080
