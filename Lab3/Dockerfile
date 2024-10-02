FROM apache/spark:latest 

# Set user to root
USER root

# download and extract hadoop, set JAVA_HOME in hadoop-env.sh, update path
RUN \
  wget -O les-arbres.csv https://github.com/lionel-fillatre/BigData/raw/main/Lab3/les-arbres.csv && \
  echo '"\e[A":history-search-backward' >> /etc/inputrc && \
  echo '"\e[B":history-search-forward' >> /etc/inputrc

# Start the spark shell
ENTRYPOINT ["/opt/spark/bin/spark-shell"]

# How to build an run: 
# docker build -t spark:v1 . 
# docker run -it spark:v1


