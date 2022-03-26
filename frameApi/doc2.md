# 文件系统

> linux的根目录为/ ,普通用户默认登录进入的是用户的家目录/home/**
> fhs定义的目录结构
****
FHS定义的目录结构 |目录|说明 ----|------ /bin | 常见的用户命令  
/boot | 内核和启动文件 /dev | 设备文件 /etc | 系统和服务的配置文件 /home | 普通用户的家目录 /lib | 系统函数库目录 /lost+found | ext3文件系统需要的目录 /mnt |
系统加载文件系统常用的挂载点 /opt | 第三方软件安装目录 /proc | 虚拟文件目录 /root | root用户的家目录 /sbin | 存放系统管理命令 /temp | 临时文件的存放目录 /usr |
存放与用户相关的文件和目录 /media | 系统用来挂在光驱等临时文件系统的挂载点

## 常用命令

```
ls  #查看当前目录下的文件(夹)
pwd #查看当前目录  .代表当前目录 ..代表上级目录
cd .
cd ..
```

## 文件的常见操作

### 创建文件

```
touch a.txt #已存在文件并不会更改文件，只会修改文件的创建时间
```

### 删除文件

```
rm a.txt 
```

### 移动或重命名 文件(夹)  move

```
cd /home/clh  #当前用户家目录
touch a.txt  #创建a.txt
mkdir test   #创建test文件夹
mv a.txt test/  #将a.txt 移动到test/a.txt
mv test/a.txt b.txt #a.txt移动到当前目录并改名
```

### 查看文件

```
cat a.txt # -n会显示行号
head -n 20 a.txt #查看前20行
tail -n 20 a.txt #查看后20行数据 -f 会刷新最新的数据
```

## 目录操作

### 创建目录

```
cd # 进入目录
pwd # 查看当前目录
mkdir test/test1/test2/test3 # -p /test2/..没有目录会创建目录
rm test #删除目录  -rf 递归删除所有文件
```

### 复制文件(夹) copy

```
touch a.txt
cp a.txt test/ #复制a.txt到test/a.txt
mkdir test1
cp -r test test1 #复制test到test1
```

## 文件和目录的权限

```
ll # ls -al 查看当前目录下文件(夹)的详细信息
# -rw-rw-r-- 1 clh  clh         0 Dec 23 08:01 a.txt
# 第一个代表文件类型 d代表目录 -代表普通文件2-4表示用户权限5-7组权限8-10其他用户权限
#rwx 代表读，写，执行,-表示没有
```

## 修改文件权限

```
chmod u+rwx a.txt #给a.txt添加用户读写执行的权限
chmod g+rw  a.txt #给a.txt添加用户组读写的权限
# u g o 分别代表用户 组 其他人  rwx代表读写执行权限
chmod 666 a.txt #给a.txt添加用户，用户组，其他人添加修改,执行(4+2)的权限
# 修改文件夹下的所有 需要 -R 参数
# r=4 w=2 x=1
# 文件和文件夹得权限略有不同
r -读取文件内容 列出目录
w - 修改该文件  目录中删除创建目录
x - 执行文件 可以使用cd命令进入该目录
```
## 修改文件所属用户/用户组
```
chown user a.txt # 修改文件所属用户
chown user:user a.txt #修改文件 用户和用户组
chown -R :user test  #修改目录的用户组
```
## SUID/SGID/Sticky
```
ll /etc/shadow  #普通用户无此文件权限，却可以修改密码
ll  /usr/bin/passwd 
# -rwsr-xr-x 1 root root 68208 Jul 14 22:08 /usr/bin/passwd*
# s- suid只可以用于二进制文件，所有用户都可以已root身份执行
chmod u+s /bin/binary
# Sticky 设置在目录上 任务用户都可以创建修改文件。但只有创建者和root可以删除自己的文件
```

## 文件默认权限
```
umask #0002
# 目录的默认权限是755 rwx-wx-wx
# 文件的是644  rw-rw-rw-
# 002表示--- --- -w- 第八位w被遮掉,权限为rw-rw-r---
# 首位特殊权限表示没有 suig = 4 sgid=2 sticky = 1
```
## 查看文件类型
```
file a.txt #比ll查看的更详细
```
## 查找文件
```
find /home -name 'a*' #查找文件
which passwd #查询系统命令的绝对路径
```
## 解、压缩
```
tar  -cvf abc.tar test  #打包test为abc.tar
tar -zcvf abc.tar test #打包压缩
# -c 创建新的文档 -v 显示详细的信息 -f 要操作的文件名
tar -zxvf abc.tar test #解压 
```