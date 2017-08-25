#!/usr/bin/env bash

rm -rf ~/.ssh
mkdir ~/.ssh
ssh-keygen -f ~/.ssh/id_rsa -t rsa -N ''
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
service ssh restart && \
    /usr/local/hadoop/sbin/start-dfs.sh && \
    /usr/local/hadoop/sbin/start-yarn.sh && \
    hdfs namenode -format && \
    /usr/local/hadoop/sbin/start-dfs.sh
