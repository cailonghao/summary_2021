#查看topic
bin/kafka-topics.sh --list --bootstrap-server 192.168.242.131:9092
# 创建消息
bin/kafka-console-producer.sh --bootstrap-server 192.168.242.131:9092 --topic kft