## 打印
>echo $SHELL # 查看当前shell
>sudo dpkg-reconfigure dash  #ubuntu默认不是bash 切换bash(执行选择否)
```
echo "hello world " #echo会打印字符串
echo `ls` #单引号(tab上面)会当做命令执行
echo -e "hello\tworld" #转义需要-e 条件
echo -e "\e[1;31m This is red \e[0m" 
 #打印带颜色的文本,重置=0 黑色=40 红色=41 绿色=42 黄色=43 蓝色=44 白色=47
```
###编写个hello world--js1.sh
```
---js1
.sh---
#!/bin/bash
a=hello
b=" world"
echo $a$b
---
#chmod a+x js1.sh #文件添加执行权限
sh js1.sh #执行脚本
```
##数学运算
shell 不能直接运算，需要借助额外命令,基础运算let,(()),高级运算expr,bc
```
---js2.sh---
#!/bin/bash
a=20
b=10
echo "a=$a,b=$b"
let c=a+b
echo "a+b=$c"
let a++
echo "自加a= $a"
let a+=b
echo "简写a=$a"
aa=$[a+100]
aaa=$((a+200))
echo "aa = $aa , aaa = $aaa"
n1=2
n2=15
result=`expr $n1 / $n2`
echo "result = $result"
echo "================"
echo "4*2"|bc
n3=`echo "scale=2; $n2/$n1"|bc`
echo $n3
---
# 只有bc支持浮点运算 scale精度
```
>chmod a+x js1.sh #文件添加执行权限<br/>
>没空格的不需要引号，有括号的需要引号包裹
##文件描述符与重定向
>最常见的文件描述符 是0-stdin,1-stdout,2-stderr(标准输入输出，错误)
```
echo "hello world" > temp.txt #>会清空原文件, >>追加模式,> 等同于1> 将标准输出重定向到文件
ls = 2>temp.txt # 会将错误信息重定向到temp
ls =2 2>error.txt 1>succ.text #分开定向
ls &> temp.txt #重定向到同一文件
# 利用重定向写入多行
cat <<EOF >TEMP.TXT
>XXX
>CCC
>EOF
```
## 数组和关联数组
```
---js3.sh
array=(list set)
array[3]=stack
echo "index=0 --> ${array[0]}"
echo "array = ${array[*]}"
echo "数量 = ${#array[*]}"
echo "关联数组"
declare -A animal
animal["cat"]="tom"
animal["mouse"]="jerry"
echo "cute ${animal["cat"]}"
echo "key* = ${!animal[*]}"
echo "value* = ${animal[*]}"
echo "size = ${#animal[*]}"
---
#declare -A 声明一个关联数组 
```
##使用别名
```
alias hello='echo "hello world"' 
hello   #输出 hello world
unalias hello #c 
cat ~/.bashrc # 写在配置文件总会生效
```
## tput 
```
tput clear      # 清除屏幕
tput sc         # 记录当前光标位置
tput rc         # 恢复光标到最后保存位置
tput civis      # 光标不可见
tput cnorm      # 光标可见
tput cup x y    # 光标按设定坐标点移动
```


