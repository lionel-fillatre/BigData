FROM ubuntu:22.04

# set environment var HADOOP_HOME
ENV HADOOP_HOME=/opt/hadoop

# install packages
RUN \
  apt-get update && apt-get install -y \
  ssh \
  rsync \
  vim \
  default-jdk \
  nano

# download and extract hadoop
RUN \
  wget https://archive.apache.org/dist/hadoop/common/hadoop-3.3.4/hadoop-3.3.4.tar.gz && \
  tar -xzf hadoop-3.3.4.tar.gz && \
  mv hadoop-3.3.4 $HADOOP_HOME

# set JAVA_HOME in .bashrc, hadoop-env.sh, update path
RUN \
  echo "export JAVA_HOME=$(dirname $(dirname $(readlink $(readlink $(which javac)))))" >> ~/.bashrc && \
  echo "export JAVA_HOME=$(dirname $(dirname $(readlink $(readlink $(which javac)))))" >> $HADOOP_HOME/etc/hadoop/hadoop-env.sh && \
  echo "export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin" >> ~/.bashrc

# create ssh keys
RUN \
  ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa && \
  cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys && \
  chmod 0600 ~/.ssh/authorized_keys

# copy hadoop configs
ADD https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab2/configs/core-site.xml $HADOOP_HOME/etc/hadoop/core-site.xml
ADD https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab2/configs/hdfs-site.xml $HADOOP_HOME/etc/hadoop/hdfs-site.xml
ADD https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab2/configs/mapred-site.xml $HADOOP_HOME/etc/hadoop/mapred-site.xml
ADD https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab2/configs/yarn-site.xml $HADOOP_HOME/etc/hadoop/yarn-site.xml


# copy ssh config
ADD https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab2/configs/ssh_config /root/.ssh/config

# copy script to start hadoop
ADD https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab2/start-hadoop.sh start-hadoop.sh

# expose various ports
EXPOSE 9870 8088 8032 50070 50075 50030 50060

# start hadoop
RUN chmod +x start-hadoop.sh

# make user directory
RUN mkdir /home/user

# User directory
WORKDIR /home/user

# Start Hadoop daemons
ENTRYPOINT ["bash", "/start-hadoop.sh"]
