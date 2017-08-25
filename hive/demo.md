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

        CREATE TABLE stage.Stok_FB (Day date, Open decimal(12,6), High decimal(12,6), Low decimal(12,6), Close decimal(12,6), AdjClose decimal(12,6), Volume int ) row format delimited fields terminated by ',' stored as textfile;

        LOAD DATA INPATH '/csv/FB.csv' OVERWRITE INTO TABLE stage.Stok_FB;

        CREATE TABLE stage.Stok_GOOG (Day date, Open decimal(12,6), High decimal(12,6), Low decimal(12,6), Close decimal(12,6), AdjClose decimal(12,6), Volume int ) row format delimited fields terminated by ',' stored as textfile;

        LOAD DATA LOCAL INPATH '/root/GOOG.csv' OVERWRITE INTO TABLE stage.Stok_GOOG;

        select * from stage.stok_fb f, stage.stok_goog g where g.day=f.day;

- Partition        

- Connecting via beeline

    beeline -u jdbc:hive2://localhost:10000