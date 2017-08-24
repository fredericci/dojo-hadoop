FROM ubuntu:16.04
ARG JAVA_VERSION=8

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME

WORKDIR ~

RUN apt-get update
RUN apt-get install -y openssh-server openssh-client tar vim wget python hostname "openjdk-$JAVA_VERSION-jdk"

RUN ssh-keygen -q -t rsa -N '' -f /id_rsa
RUN cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
