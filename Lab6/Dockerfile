FROM apache/spark:latest 

# Set user to root
USER root

# copy all the files for the lab and tune the spark shell
RUN \
  mkdir /opt/spark/work-dir/lab6 && \
  cd /opt/spark/work-dir/lab6 && \
  wget -O /opt/spark/work-dir/lab6/olympics.csv https://raw.githubusercontent.com/lionel-fillatre/BigData/main/Lab6/olympics.csv

# Working directory
WORKDIR /opt/spark/work-dir/lab6

# Start the spark shell
ENTRYPOINT ["/opt/spark/bin/spark-shell"]

# How to build an run: 
# docker build -t spark:lab6 . 
# docker run -it spark:lab6



