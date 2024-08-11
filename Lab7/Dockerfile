FROM ubuntu:20.04

# set environment vars
ENV SPARK_HOME=/opt/spark

# install packages
RUN \
  apt-get update && apt-get install -y \
  ssh \
  rsync \
  vim \
  default-jdk \
  nano \
  gnupg

# download and extract hadoop, set JAVA_HOME in hadoop-env.sh, update path
RUN \
  wget https://archive.apache.org/dist/spark/spark-3.4.1/spark-3.4.1-bin-hadoop3.tgz && \
  tar xzf spark-3.4.1-bin-hadoop3.tgz && \
  mv spark-3.4.1-bin-hadoop3/  $SPARK_HOME && \
  echo "export SPARK_HOME=/opt/spark" >> ~/.bashrc

# download and extract hadoop, set JAVA_HOME in hadoop-env.sh, update path
RUN \
  wget https://dlcdn.apache.org/zeppelin/zeppelin-0.11.1/zeppelin-0.11.1-bin-all.tgz && \
  wget https://dlcdn.apache.org/zeppelin/KEYS && \
  wget https://dlcdn.apache.org/zeppelin/zeppelin-0.11.1/zeppelin-0.11.1-bin-all.tgz.asc && \
  gpg --import KEYS && \
  gpg --verify zeppelin-0.11.1-bin-all.tgz.asc zeppelin-0.11.1-bin-all.tgz && \
  tar xzf zeppelin-0.11.1-bin-all.tgz

RUN \
  mv zeppelin-0.11.1-bin-all/ /opt/zeppelin && \
  echo "export ZEPPELIN_HOME=/opt/zeppelin" >> ~/.bashrc && \
  echo "export PATH=$PATH:/opt/zeppelin/bin:/opt/spark/bin:/opt/spark/sbin" >> ~/.bashrc

RUN \
  cp /opt/zeppelin/conf/zeppelin-env.sh.template /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export USE_HADOOP=false" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export ZEPPELIN_ADDR=0.0.0.0" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export ZEPPELIN_PORT=8090" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export SPARK_HOME=/opt/spark" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export SPARK_CONF_DIR=/opt/spark/conf" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  # rm is necessary to fix a bug in Zeppelin Spark-interpreter-0.11.1.jar and Scala
  rm /opt/zeppelin/interpreter/spark/._spark-interpreter-0.11.1.jar && \
  rm /opt/zeppelin/interpreter/spark/scala-2.12/._spark-scala-2.12-0.11.1.jar



# Download the data files in the user directory
RUN \
  mkdir /home/user && \  
  wget -O /home/user/churn-test-header.csv https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab7/churn-test-header.csv && \
  wget -O /home/user/churn-train-header.csv https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab7/churn-train-header.csv
  

# expose various ports
EXPOSE 8080 8088

# USER hadoop
WORKDIR /home/user

# Start a bash shell just after starting the Zeppelin daemon
RUN echo "bash" >> /opt/zeppelin/bin/zeppelin-daemon.sh

# Start the Zeppelin daemon
ENTRYPOINT ["/opt/zeppelin/bin/zeppelin-daemon.sh"]
CMD ["start"]
