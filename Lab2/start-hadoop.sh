#!/bin/bash

# start ssh server
/etc/init.d/ssh start

echo -e "Setting Permissions\n"

# /opt/hadoop/bin/hadoop fs -mkdir /user/sdc
# /opt/hadoop/bin/hadoop fs -chown sdc /user/sdc

export HDFS_NAMENODE_USER="root"
export HDFS_DATANODE_USER="root"
export HDFS_SECONDARYNAMENODE_USER="root"
export YARN_RESOURCEMANAGER_USER="root"
export YARN_NODEMANAGER_USER="root"
export HADOOP_USER_NAME="root"

# format namenode
$HADOOP_HOME/bin/hdfs namenode -format

# start hadoop
$HADOOP_HOME/sbin/stop-all.sh

# start hadoop
$HADOOP_HOME/sbin/start-all.sh
$HADOOP_HOME/sbin/start-yarn.sh

echo -e "\n\nAll nodes started successfully\n\n"

echo -e "Running jps\n\n"

jps

bash

# keep container running
#tail -f /dev/null