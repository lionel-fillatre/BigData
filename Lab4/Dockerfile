FROM apache/spark:latest 

# Set user to root
USER root

# copy all the files for the lab and tune the spark shell
RUN \
  mkdir /opt/spark/work-dir/lab4 && \
  cd /opt/spark/work-dir/lab4 && \
  wget -O /opt/spark/work-dir/lab4/commands.scala https://github.com/lionel-fillatre/BigData/raw/main/Lab4/commands.scala && \
  echo 'green\norange\nyellow\nred' > color.txt && \
  echo 'red apple\ngreen apple\nbanana\nkiwi\npear\norange' > fruit.txt && \
  echo 'green salad\ncarrot\nleek\npotato' > vegetable.txt  && \
  echo '"\e[A":history-search-backward' >> /etc/inputrc && \
  echo '"\e[B":history-search-forward' >> /etc/inputrc

# Working directory
WORKDIR /opt/spark/work-dir/lab4

# Start the spark shell
ENTRYPOINT ["/opt/spark/bin/spark-shell"]

# How to build an run: 
# docker build -t spark:lab4 . 
# docker run -it spark:lab4



