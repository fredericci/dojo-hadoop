FROM cit/dojo-jdk:8
ARG HADOOP_VERSION=2.7.4
ARG TAR=hadoop-$HADOOP_VERSION.tar.gz

ENV HADOOP_INSTALL /usr/local/hadoop
ENV PATH $PATH:$HADOOP_INSTALL/bin
ENV PATH $PATH:$HADOOP_INSTALL/sbin
ENV HADOOP_MAPRED_HOME $HADOOP_INSTALL
ENV HADOOP_COMMON_HOME $HADOOP_INSTALL
ENV HADOOP_HDFS_HOME $HADOOP_INSTALL
ENV HADOOP_COMMON_LIB_NATIVE_DIR $HADOOP_INSTALL/lib/native
ENV YARN_HOME $HADOOP_INSTALL

WORKDIR /usr/local

RUN wget -t 10 --max-redirect 1 --retry-connrefused -O "$TAR" "http://mirror.nbtelecom.com.br/apache/hadoop/common/hadoop-$HADOOP_VERSION/hadoop-$HADOOP_VERSION.tar.gz"
RUN tar zxf "$TAR" && \
    # check tarball was extracted to the right place, helps ensure it's the right version and the link will work
    ln -sv "hadoop-$HADOOP_VERSION" hadoop && \
    rm -fv "$TAR"    

RUN sed -i 's/JAVA_HOME=${JAVA_HOME}/JAVA_HOME=\/usr\/lib\/jvm\/java-8-openjdk-amd64/g' /usr/local/hadoop/etc/hadoop/hadoop-env.sh

RUN mkdir -p /hdfs-volume/namenode
RUN mkdir -p /hdfs-volume/datanode

COPY entrypoint.sh /
COPY conf/core-site.xml /usr/local/hadoop/etc/hadoop/
COPY conf/hdfs-site.xml /usr/local/hadoop/etc/hadoop/
COPY conf/yarn-site.xml /usr/local/hadoop/etc/hadoop/
COPY conf/mapred-site.xml /usr/local/hadoop/etc/hadoop/

RUN chmod +x /entrypoint.sh

EXPOSE 8020 8042 8088 9000 10020 19888 50010 50020 50070 50075 50090

CMD "/entrypoint.sh"