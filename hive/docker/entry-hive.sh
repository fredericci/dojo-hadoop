hadoop fs -mkdir /tmp 
hadoop fs -mkdir /hive
hadoop fs -mkdir /hive/warehouse
hadoop fs -chmod g+w /tmp 
hadoop fs -chmod g+w /hive/warehouse

schematool -initSchema -dbType derby