FROM ubuntu:20.04

# set environment vars
ENV SPARK_HOME=/opt/spark

# install packages
RUN \
  apt-get update --fix-missing && apt-get install -y \
  ssh \
  rsync \
  vim \
  default-jdk \
  nano \
  gnupg

# download, extract and install Spark
RUN \
  wget https://archive.apache.org/dist/spark/spark-3.4.4/spark-3.4.4-bin-hadoop3.tgz && \
  tar xzf spark-3.4.4-bin-hadoop3.tgz && \
  mv spark-3.4.4-bin-hadoop3/  $SPARK_HOME && \
  echo "export SPARK_HOME=/opt/spark" >> ~/.bashrc

# download and extract Zeppelin
RUN \
  wget https://dlcdn.apache.org/zeppelin/zeppelin-0.12.0/zeppelin-0.12.0-bin-all.tgz && \
  wget https://dlcdn.apache.org/zeppelin/KEYS && \
  wget https://dlcdn.apache.org/zeppelin/zeppelin-0.12.0/zeppelin-0.12.0-bin-all.tgz.asc && \
  gpg --import KEYS && \
  gpg --verify zeppelin-0.12.0-bin-all.tgz.asc zeppelin-0.12.0-bin-all.tgz && \
  tar xzf zeppelin-0.12.0-bin-all.tgz

# install Zeppelin
RUN \
  mv zeppelin-0.12.0-bin-all/ /opt/zeppelin && \
  echo "export ZEPPELIN_HOME=/opt/zeppelin" >> ~/.bashrc && \
  echo "export PATH=$PATH:/opt/zeppelin/bin:/opt/spark/bin:/opt/spark/sbin" >> ~/.bashrc

# Update Zeppelin configuration files
RUN \
  cp /opt/zeppelin/conf/zeppelin-env.sh.template /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export USE_HADOOP=false" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export USE_HADOOP=false" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export ZEPPELIN_ADDR=0.0.0.0" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export ZEPPELIN_PORT=8090" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export SPARK_HOME=/opt/spark" >> /opt/zeppelin/conf/zeppelin-env.sh && \
  echo "export SPARK_CONF_DIR=/opt/spark/conf" >> /opt/zeppelin/conf/zeppelin-env.sh

# Create the user directory
RUN mkdir /home/user

# Download the data files for Lab3 in the user directory
RUN \
  mkdir /home/user/lab3 && \ 
  wget -O /home/user/lab3/les-arbres.csv https://github.com/lionel-fillatre/BigData/raw/main/Lab3/les-arbres.csv 

# Download the data files for Lab4 in the user directory
RUN \
  mkdir /home/user/lab4 && \ 
  cd /home/user/lab4 && \
  echo 'green\norange\nyellow\nred' > color.txt && \
  echo 'red apple\ngreen apple\nbanana\nkiwi\npear\norange' > fruit.txt && \
  echo 'green salad\ncarrot\nleek\npotato' > vegetable.txt && \
  cd /home/user/

# Download the data files for Lab6 in the user directory
RUN \  
  mkdir /home/user/lab6 && \ 
  wget -O /home/user/lab6/olympics.csv https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab6/olympics.csv

# Download the data files for Lab7 in the user directory
RUN \
  mkdir /home/user/lab7 && \  
  wget -O /home/user/lab7/churn-test-header.csv https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab7/churn-test-header.csv && \
  wget -O /home/user/lab7/churn-train-header.csv https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab7/churn-train-header.csv

# expose various ports
EXPOSE 8080 8088 8090

# USER hadoop
WORKDIR /home/user

# Start a bash shell just after starting the Zeppelin daemon
RUN echo "bash" >> /opt/zeppelin/bin/zeppelin-daemon.sh

# Start the Zeppelin daemon
ENTRYPOINT ["/opt/zeppelin/bin/zeppelin-daemon.sh"]
CMD ["start"]
