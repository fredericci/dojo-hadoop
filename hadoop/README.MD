# Configuring Environment

## Steps
- Clone docker project
- Build docker
    
        cd hadoop/java
        docker build -t cit/dojo-java:8 .

        cd hadoop/docker
        docker build -t cit/dojo-hadoop .

- Start hadoop docker container
 
        docker run -it --name dojo-hadoop -p 8088:8088 -p 50070:50070 -p 8042:8042 -p 19888:19888 cit/dojo-hadoop bash