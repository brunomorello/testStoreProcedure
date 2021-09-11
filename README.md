## Docker MYSQL image

	docker run --name localhost_mysql5.7 -e MYSQL_ROOT_PASSWORD=dqm50vnc -p 3306:3306 -d mysql:5.7
	docker exec -it localhost_mysql5.7 bash

> Create PROC to create cats

CREATE PROCEDURE new_cat(name VARCHAR(45), gender CHAR(1), age INT)
   MODIFIES SQL DATA DYNAMIC RESULT SETS 1
   BEGIN ATOMIC
     DECLARE result CURSOR FOR SELECT * FROM CATS WHERE ID = IDENTITY();
     INSERT INTO CATS VALUES (DEFAULT, name, gender, age);
     OPEN result;    
   END
   
> Create PROC to retrieve Cats older than inputed age

CREATE PROCEDURE FIND_CATS_OLDER_THAN(IN age_in INT)
    READS SQL DATA DYNAMIC RESULT SETS 1
BEGIN ATOMIC
DECLARE result CURSOR WITH RETURN FOR SELECT * FROM CATS WHERE age >= age_in;
OPEN result;
END

> Create PROC to retrieve count of Cats older than specific age

CREATE PROCEDURE COUNT_CATS_OLDER_THAN(IN age_in INT)
    READS SQL DATA DYNAMIC RESULT SETS 1
	BEGIN ATOMIC
	DECLARE result CURSOR WITH RETURN FOR SELECT COUNT(*) FROM CATS WHERE age >= age_in;
	OPEN result;
END

> JPA with Procedure not working when result is a List of Entity - insuficient privileges for HSQLDB (gonna use mysql)

https://javadeveloperzone.com/spring-boot/spring-boot-jpa-call-mysql-procedure/ -> not work
https://www.baeldung.com/jpa-sql-resultset-mapping -> not work
https://github.com/spring-projects/spring-data-jpa/issues/1864 -> not work
https://stackoverflow.com/questions/31097667/illegalargumentexception-type-cannot-be-null -> not work
https://www.baeldung.com/spring-data-jpa-stored-procedures -> not work

## Download and run HSQLDB
> https://sourceforge.net/projects/hsqldb/files/latest/download

> Unzip hsqldb and create on root of it a file "server.properties" following the pattern:
	
	`server.database.0 = file:hsqldb/DB_NAME`
	`server.dbname.0 = DB_NAME`
 	
For this project dbname is catsys
 
> Still in hsqldb root path, start the db server
	
	`java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file.testdb --dbname0.testdb` 

To do not lock terminal session use use nohup instead
	
	`nohup java -cp lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/catsys --dbname.0 catsys &`

> Open GUI to run SQL queries

	`java -cp ../lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing`
	
> Create CAT table

`CREATE TABLE cats ( id INT NOT NULL, name VARCHAR(45), gender CHAR(1), age INT, PRIMARY KEY (ID));`