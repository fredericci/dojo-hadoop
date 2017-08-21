# Demo

## Steps

- Setting up ssh

        cd ~
        ssh-keygen -t rsa -P ''
        cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
        service ssh restart
        ssh localhost
        exit

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

        wget https://www.w3.org/TR/PNG/iso_8859-1.txt
        hdfs dfs -put iso_8859-1.txt /

        hadoop jar /usr/local/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.4.jar  wordcount /iso_8859-1.txt /output

        hdfs dfs -rm -r -f /output

- Some links

        http://localhost:50070

        http://localhost:8088

