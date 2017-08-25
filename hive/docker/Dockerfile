FROM cit/dojo-hadoop

ENV HIVE_HOME /usr/local/hive
ENV HIVE_INSTALL $HIVE_HOME 
ENV DERBY_HOME=/usr/local/derby
ENV PATH $PATH:$HIVE_HOME/bin:$DERBY_HOME/bin

WORKDIR /usr/local

RUN wget http://archive.apache.org/dist/db/derby/db-derby-10.4.2.0/db-derby-10.4.2.0-bin.tar.gz && \
    tar zxf db-derby-10.4.2.0-bin.tar.gz && \ 
    mv db-derby-10.4.2.0-bin derby && \
    mkdir derby/data

RUN wget http://www-us.apache.org/dist/hive/hive-2.3.0/apache-hive-2.3.0-bin.tar.gz && \
    tar zxf apache-hive-2.3.0-bin.tar.gz && \ 
    mv apache-hive-2.3.0-bin hive

COPY entry-hive.sh /
COPY conf/hive-env.sh /usr/local/hive/conf/
COPY conf/hive-site.xml /usr/local/hive/conf/
COPY conf/hive-log4j2.properties /usr/local/hive/conf/
COPY conf/jpox.properties /usr/local/hive/conf/
COPY conf/java.policy/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/security/

RUN chmod +x /entry-hive.sh

EXPOSE 8020 8042 8088 9000 10020 19888 50010 50020 50070 50075 50090 10002 10001 10000

CMD "/entry-hive.sh"
