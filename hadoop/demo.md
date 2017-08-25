# Demo

## Steps

- Starting servers

        cd /
        ./entrypoint.sh

- Dummy things

        hdfs dfs -ls /
        hdfs dfs -mkdir /temp
        hdfs dfs -touchz /teste.txt
        hdfs dfs -df -h

        wget http://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data
        hdfs dfs -put iris.data /
        hdfs dfs -ls -h /

- Running a mapreduce example

        hdfs dfs -put $HADOOP_INSTALL/etc/ /

        hadoop jar $HADOOP_INSTALL/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.4.jar wordcount /etc/hadoop /output/wordcount

        hdfs dfs -ls /output/wordcount
        hdfs dfs -cat /output/wordcount/part-r-00000

        hdfs dfs -rm -r -f /output/wordcount

- Running homemade mapreduce

        hadoop jar /root/hadoop.mapreduce-0.0.1.jar com.ciandt.dojo.hadoop.Main /etc/hadoop /output/mywordcount
        
        hdfs dfs -ls /output/wordcount
        hdfs dfs -cat /output/wordcount/part-r-00000

         hadoop job -list
         hadoop job -kill <jobId>

- Some links

        sudo sed -i '$ a 127.0.0.1 <docker_container>' /etc/hosts

        http://localhost:50070

        http://localhost:8088

