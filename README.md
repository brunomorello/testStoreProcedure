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