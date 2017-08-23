# Demo

## Steps

- Running on console

        cat mapper.py | python mapper.py
        cat mapper.py | python mapper.py | sort | python reducer.py

- Running on hadoop

        apt-get install python

        hadoop jar $HADOOP_INSTALL/share/hadoop/tools/lib/hadoop-streaming-2.7.4.jar -file ./mapper.py -mapper ./mapper.py -file ./reducer.py -reducer ./reducer.py -input /iris.data -output /py-output
      