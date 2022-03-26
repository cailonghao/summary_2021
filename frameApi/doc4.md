##shell2
###shell函数
```
---js4.sh---
#!/bin/bash
function fun(){
    echo $1,$2
    echo let $1+$2
    echo "this is a function"
}
fun 1 2
---
# 直接使用函数名就可调用函数,$1 $2 分别为方法的参数
```
##字符隔断和迭代器 IFS
```
---js5.sh--
#!/bin/bash
data="duck,fish,cat"
oldifs=$IFS
IFS=,now,
for item in $data;
do
        echo Item:$item
done
IFS=$oldIFS
---
```
###流程控制
```
---js6.sh
#!/bin/bash
path=/home/lkl/shs
if [ -d $path ];then
echo file exists;
else
echo not exist;
fi
if [[ aa = "aa" ]];
then
echo "aa = 'aa'"
fi
---
>1.算术比较 -eq 等于 -ne 不等于 -gt 大于 -lt 小于 -ge 大于或等于 -le 小于或等于
>2.文件系统比较 
>   - [ -f $file_var ]:文件是否查找成功
>   - [ -x $var ]:如果文件可执行
>   - [ -d $var ]:是否是目录
>   - [ -e $var ]:文件是否存在
>   - [ -c $var ]:是否为字符设备文件
>   - [ -b $var ]:是否为块设备文件
>   - [ -w $var ]:文件是否可写
>   - [ -r $var ]:文件是否可读
>   - [ -L $var ]:文件是否为符号链接
>3.字符串比较
>   [[ -z $str2 ]] 为空则返回true
>   [[ -n $str2 ]] 非空为true
>   [[ $str1 != $str2 ]]
```
##循环
```
---js7.sh---
#!/bin/bash
j=10
for((i=1;i<$j;i++))
do
echo "for --> $i"
done
---
# 基础用法

---js8.sh---
#!/bin/bash
echo "随机数"
for i in {0..9};
do
        echo $RANDOM;
done
---
# 随机数

---js9.sh---
#!/bin/bash
j=10
for i in $(seq 5 -1 1)
do
echo "$i";sleep 1
done
---
# seq 起始值 结束值 增量为1
# 序列数
```


