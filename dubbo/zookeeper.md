docker run --privileged=true -d --name zookeeper --publish 2181:2181  -d zookeeper:latest

docker run --name docker-zookeeper -p 2888:2888 -p 3888:3888 -p 8080:8080 -p 2181:2181 --restart always -d zookeeper:3.4