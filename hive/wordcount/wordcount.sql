
CREATE DATABASE IF NOT EXISTS stage;

USE stage;

DROP TABLE  stage.wordcount;
CREATE TABLE stage.wordcount (line string)
STORED as textfile;

LOAD DATA LOCAL INPATH '${file}' OVERWRITE INTO TABLE stage.wordcount;

CREATE DATABASE IF NOT EXISTS result;

USE result;

DROP TABLE result.wordcount;
CREATE TABLE result.wordcount (word string, count int)
ROW FORMAT delimited fields terminated by ',' 
STORED as textfile;

INSERT INTO result.wordcount
SELECT w.word, count(1) AS count 
  FROM ( SELECT explode(split(line, '\\s')) AS word 
           FROM stage.wordcount) w
GROUP BY w.word
ORDER BY w.word;
