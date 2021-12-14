#安装java
yum install java-1.8.0-openjdk

#zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties
nohup bin/zookeeper-server-start.sh config/zookeeper.properties  > a.txt &
#kafka
bin/kafka-server-start.sh config/server.properties
nohup bin/kafka-server-start.sh config/server.properties > b.txt &

./kafka-server-start.sh -daemon ../config/server.properties


