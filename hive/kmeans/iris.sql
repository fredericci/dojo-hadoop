CREATE DATABASE IF NOT EXISTS stage;

USE stage;

-- Create input data table to process
DROP TABLE  stage.iris;
CREATE TABLE stage.iris (
    sepal_l decimal(12,6), 
    sepal_w decimal(12,6), 
    petal_l decimal(12,6),
    petal_w decimal(12,6), 
    class string)
ROW FORMAT delimited fields terminated by ',' 
STORED as textfile;

LOAD DATA LOCAL INPATH '${iris_file}' OVERWRITE INTO TABLE stage.iris;

-- Create centroid parameters table
DROP TABLE  stage.centroids;
CREATE TABLE stage.centroids (
    sepal_l decimal(12,6), 
    sepal_w decimal(12,6), 
    petal_l decimal(12,6),
    petal_w decimal(12,6), 
    class string)
ROW FORMAT delimited fields terminated by ',' 
STORED as textfile;

LOAD DATA LOCAL INPATH '${centroids_file}' OVERWRITE INTO TABLE stage.centroids;

CREATE DATABASE IF NOT EXISTS result;
USE result;

-- Create table to store distances from 
DROP TABLE result.temp;
CREATE TABLE result.temp 
(
    sepal_l decimal(12,6), 
    sepal_w decimal(12,6), 
    petal_l decimal(12,6),
    petal_w decimal(12,6),
    distance decimal(12,6),
    centroid_class string
)
ROW FORMAT delimited fields terminated by ',' 
STORED as textfile;


INSERT INTO result.temp
SELECT i.sepal_l
     , i.sepal_w
     , i.petal_l
     , i.petal_w
     , SQRT(
             POW((i.sepal_l - c.sepal_l),2)
            +POW((i.sepal_w - c.sepal_w),2) 
            +POW((i.petal_l - c.petal_l),2)
            +POW((i.petal_w - c.petal_w),2)
        ) as distance
     , c.class
FROM stage.iris i
   , stage.centroids c;

DROP TABLE result.iris_class;
CREATE TABLE result.iris_class (classified string, expected string)
ROW FORMAT delimited fields terminated by ',' 
STORED as textfile;


INSERT INTO result.iris_class
SELECT i.class, IC.centroid_class FROM stage.iris i, 
(
select t.sepal_l, t.sepal_w, t.petal_l, t.petal_w, t.centroid_class
from (
    select min(distance) as min_distance, sepal_l, sepal_w, petal_l, petal_w 
    from result.temp
    group by sepal_l, sepal_w, petal_l, petal_w
) as dist, result.temp t
where t.sepal_l = dist.sepal_l AND
      t.sepal_w = dist.sepal_w AND 
      t.petal_l = dist.petal_l AND 
      t.petal_w = dist.petal_w AND
      t.distance = dist.min_distance
) as IC
where i.sepal_l = IC.sepal_l AND
      i.sepal_w = IC.sepal_w AND 
      i.petal_l = IC.petal_l AND 
      i.petal_w = IC.petal_w;
      
select a.diff/b.total
  from
   (select count(1) as total from  result.iris_class) b ,
    (select count(1) as diff from  result.iris_class where classified  !=  expected) a 
  ;
