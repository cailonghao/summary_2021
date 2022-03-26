#!/bin/bash
echo "step1:remove old docker"
yum remove docker \
  docker-client \
  docker-client-latest \
  docker-common \
  docker-latest \
  docker-latest-logrotate \
  docker-logrotate \
  docker-engine
sudo rm -rf /var/lib/docker
echo "step2:安装必要工具"
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
echo "step3:添加docker仓库"
sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
echo "step4:配置缓存"
sudo yum makecache fast
echo "step5:安装docker"
sudo yum install docker-ce
echo "step6:设置开机启动"
sudo systemctl start docker
sudi systemctl enable docker

