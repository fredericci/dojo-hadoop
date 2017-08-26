# Configuring Environment

## Steps
- Clone the project
- Build docker

        docker build -t cit/dojo-java:8 - < java/docker/Dockerfile

        cd hadoop/docker
        docker build -t cit/dojo-hadoop .

        cd ../../hive/docker
        docker build -t cit/dojo-hive .

- Start hadoop docker container

  - Hadoop Container
 
        docker run -it --name dojo-hadoop -p 8088:8088 -p 50070:50070 -p 8042:8042 -p 19888:19888 cit/dojo-hadoop bash

  - Hadoop + Hive Container

        docker run -it --name dojo-hive -p 8088:8088 -p 50070:50070 -p 50075:50075 -p 8042:8042 -p 19888:19888 -p 10002:10002 -p 10001:10001 -p 10000:10000 cit/dojo-hive bash
