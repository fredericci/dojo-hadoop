## Steps

- Starting servers

        cd /
        ./entry-hadoop.sh
        ./usr/local/hadoop/sbin/start-dfs.sh 
        ./entry-hive.sh
        jps

        Expected:
            Jps
            SecondaryNameNode
            ResourceManager
            DataNode
            NameNode

- Dummy commands

        show databases;

        show tables;

        create database dummy;

        use dummy;

        create table dummy.test (id int, name varchar(255));
        insert into test (id,name) values (1,'asdasd');

        select * from test;

- Loading csv data

        https://finance.yahoo.com/quote/FB/history?p=FB

        $ hive

        CREATE DATABASE stage;

        CREATE TABLE stage.stock_fb (Day date, Open decimal(12,6), High decimal(12,6), Low decimal(12,6), Close decimal(12,6), AdjClose decimal(12,6), Volume int ) row format delimited fields terminated by ',' stored as textfile;

        LOAD DATA INPATH '/csv/FB.csv' OVERWRITE INTO TABLE stage.Stok_FB;

        CREATE TABLE stage.stock_goog (Day date, Open decimal(12,6), High decimal(12,6), Low decimal(12,6), Close decimal(12,6), AdjClose decimal(12,6), Volume int ) row format delimited fields terminated by ',' stored as textfile;

        LOAD DATA LOCAL INPATH '/root/GOOG.csv' OVERWRITE INTO TABLE stage.Stok_GOOG;

        select * from stage.stok_fb f, stage.stok_goog g where g.day=f.day;

- Partition   

        CREATE DATABASE stocks;

        CREATE TABLE stocks.day_summary (Day date, Open decimal(12,6), High decimal(12,6), Low decimal(12,6), Close decimal(12,6), AdjClose decimal(12,6), Volume int ) 
        PARTITIONED BY (company string)
        ROW FORMAT delimited fields terminated by ',' 
        STORED AS textfile;  

        SET hive.exec.dynamic.partition = true;
        SET hive.exec.dynamic.partition.mode = nonstrict;
        SET hive.exec.max.dynamic.partitions.pernode = 400;
        
        insert into stocks.day_summary partition (company)
        select *, 'GOOG' as company from stage.stock_goog where open is not null
        union
        select *, 'FB' as company from stage.stock_fb where open is not null
        ;   

- Running wordcount script

        hive -f wordcount.sql --hivevar file=/root/index.html


- Connecting via beeline

    beeline -u jdbc:hive2://localhost:10000