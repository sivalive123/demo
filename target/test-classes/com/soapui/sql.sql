SELECT DISTICT AGE FROM MOVIE


SELECT theatre FROM MOVIE where  id NOT in (SELECT DISTINCT movieid FROM CONNECTION)


SELECT theatre FROM  MOVIE where id IN (SELECT movieid FROM CONNECTION WHERE detailsid NOT IN (SELECT ID FROM DETAILS WHERE AGE >9))


SELECT theatre FROM  MOVIE where id IN ( SELECT ID FROM DETAILS WHERE AGE >9 EXCEPT SELECT DISTINCT movieid FROM CONNECTION)

SELECT theatre FROM MOVIE INNER JOIN MOVIE ON MOVIE.ID == 


SELECT COUNT(MOVIEID) AS NUMBERS, MOVIEID FROM CONNECTION GROUP BY MOVIEID

SELECT ID FROM  MOVIE

SELECT COUNT(MOVIEID) AS NUMBERS, MOVIEID,theatre AS MID FROM CONNECTION GROUP BY MOVIEID LEFT JOIN MOVIE on MOVIEID = MOVIE.id
select (SELECT COUNT(CITY) FROM STATION) - (SELECT COUNT(DISTINCT CITY) FROM STATION)
or 
SELECT COUNT(CITY) - COUNT(DISTINCT CITY) FROM STATION


select TOP 1 city, LEN(city) from station order by LEN(city),city asc ;
select TOP 1 city, LEN(city) from station order by LEN(city) desc ,city asc ;
select TOP 1 city, LEN(REPLACE(TRIM(city),' ', '')) from station order by LEN(REPLACE(TRIM(city),' ', '')),city asc ;
select TOP 1 city, LEN(REPLACE(TRIM(city),' ', '')) from station order by LEN(REPLACE(TRIM(city),' ', '')) desc ,city asc ;

select distinct city from station where city like '[aeiou]%'
select distinct city from station where city like '%[aeiou]'
select DISTINCT city from station where LOWER(city) like '[aeiou]%[aeiou]'
select DISTINCT city from station where LOWER(city) like '[!aeiou]%'
select DISTINCT city from station where LOWER(city) like '!%[aeiou]'

select DISTINCT city FROM station WHERE LOWER(SUBSTRING(city, 1, 1)) NOT IN ('a','e','i','o','u');
SELECT DISTINCT CITY FROM STATION WHERE LEFT(CITY,1) NOT IN ('a','e','i','o','u');

select DISTINCT city FROM station WHERE LOWER(SUBSTRING(city, -1, 1)) NOT IN ('a','e','i','o','u');
SELECT DISTINCT CITY FROM STATION WHERE RIGHT(CITY,1) NOT IN ('a','e','i','o','u');


select SUBSTRING(name, -1,1) from STUDENTS where marks > 75


SELECT
    case when Occupation='Doctor' then Name end as Doctor,
    case when Occupation='Professor' then Name end as Professor,
    case when Occupation='Singer' then Name end as Singer,
    case when Occupation='Actor' then Name end as Actor
FROM OCCUPATIONS


DatabaseEngine -> Databases -> Tables -> Datas


DMl - Data Manipulation Language

DDL - Data Definition language

Create Databases db1
use db1 


create table t1(
  name varchar(Max) NOT NULL unique,
  city varchar(255) default "chennai",
  age int check (Age>15))

Insert into t1 values('siva','chennai',28)
Insert into t1(name, city) values('siva','chennai')

Delete from t where name='siva'
Update t1 set age=24 where name ='siva'
Alter table t1 add rollnum int
Truncate table t1
Drop table t1 
Drop database d1

select name as [Name of student] from STUDENTS


select owner.name as OWnername, pet.name as PetName from owner inner join pet where owner.petid=pet.id


#InnerJoin
SELECT CustomerID from Orders Inner Join OrderDetails on Orders.OrderID = OrderDetails.OrderID