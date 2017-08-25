# Demo

## Steps

### wordcount
- Running on console

        cat mapper.py | python mapper.py
        cat mapper.py | python mapper.py | sort | python reducer.py

- Running on hadoop

        apt-get install python

        hadoop jar $HADOOP_HOME/share/hadoop/tools/lib/hadoop-streaming-2.7.4.jar  -file ./mapper.py -mapper mapper.py -file ./reducer.py -reducer reducer.py -input ./mapper.py -output ./output

### kmeans-classifier
- Running on console

        cat iris.data | python mapper.py
        cat iris.data | python mapper.py | sort | python reducer.py

- Running on hadoop

        hadoop jar $HADOOP_HOME/share/hadoop/tools/lib/hadoop-streaming-2.7.4.jar  -file ./mapper.py -mapper mapper.py -file ./reducer.py -reducer reducer.py -file ./iris.data  -input ./iris.data -output ./output
      