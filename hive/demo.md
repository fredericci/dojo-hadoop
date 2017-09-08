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

        CREATE TABLE stage.stock_fb (Day date, Open decimal(12,6), High decimal(12,6), Low decimal(12,6), Close decimal(12,6), AdjClose decimal(12,6), Volume int ) 
        row format delimited fields terminated by ',' 
        stored as textfile;

        LOAD DATA INPATH '/csv/FB.csv' OVERWRITE INTO TABLE stage.stock_fb;

        CREATE TABLE stage.stock_goog (Day date, Open decimal(12,6), High decimal(12,6), Low decimal(12,6), Close decimal(12,6), AdjClose decimal(12,6), Volume int ) 
        row format delimited fields terminated by ',' 
        stored as textfile;

        LOAD DATA LOCAL INPATH '/root/GOOG.csv' OVERWRITE INTO TABLE stage.stock_goog;

        select * 
          from stage.stock_fb f
             , stage.stock_goog g 
         where g.day=f.day;

        CREATE TABLE stage.stock_msft (Day date, Open decimal(12,6), High decimal(12,6), Low decimal(12,6), Close decimal(12,6), AdjClose decimal(12,6), Volume int ) 
        row format delimited fields terminated by ',' 
        stored as textfile
        location '/data/MSFT.csv';

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

- View 

    create view stage.view_stock AS
    select *, 'GOOG' as company 
      from stage.stock_goog 
     where open is not null
    union
    select *, 'FB' as company 
     from stage.stock_fb 
     where open is not null    
    ;

- Dinamic Data Types

    create table mobilephones 
        (
            id string,
            title string,
            cost float,
            colors array<string>,
            screen_size array<float>
    );

    insert into table mobilephones
    select "redminote7", "Redmi Note 7", 300, 
    array("white", "silver", "black"), array(float(4.5))
    
    UNION ALL
    
    select "motoGplus", "Moto G Plus", 200, array("black", "gold"), 
    array(float(4.5), float(5.5))
    ;

- Running wordcount script

        hive -f wordcount.sql --hivevar file=/root/index.html

        beeline -u jdbc:hive2:// -f wordcount.sql --hivevar file=/root/index.html

- Connecting via beeline

        beeline -u jdbc:hive2://

        