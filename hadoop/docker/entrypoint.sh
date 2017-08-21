#!/usr/bin/env bash

/usr/local/hadoop/sbin/start-dfs.sh
/usr/local/hadoop/sbin/start-yarn.sh
hdfs namenode -format