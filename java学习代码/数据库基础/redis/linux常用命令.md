##### 一、linux系统目录

* / ：根目录
* /bin：常用的二进制命令所在目录，例如ls、cp、mkdir
* /etc：yum、rpm方式安装应用程序的默认配置文件路径
* /usr：存放用户应用程序的目录，包含两个重要子目录
  * /usr/local：编译方式安装程序的默认目录
  * /usr/src：程序源码目录
* /root：Linux超级用户目录，类似于windows的administrator
* /sbin：系统管理命令存放目录，是超级用户root可执行命令的存放地
* /proc：系统运行时，进程信息与内核信息存放在此目录
* /lib：启动系统与运行命令所需的依赖
* /boot：Linux内核与系统引导程序目录
* /dev：设备文件的目录
* /home：存放用户数据的目录
* /var：系统与软件服务运行的日志目录



##### 二、文件操作命令

```bash
cd #切换目录
```

```bas
pwd #查看当前目录
```

```bash
ls #显示目录内容
ll #长文本格式显示目录内容
```

```bash
mkdir #创建目录
mkdir -p #递归创建多级目录
mkdir -p -v #递归创建多级目录，并且打印执行过程
```

```bash
cp #复制文件与目录
cp -r #复制目录
```

```bash
mv #移动或重命名文件
```

```bash
rm #删除文件或目录
```

```bash
find #查找文件或目录
find / -name *.exe #从根路径下查找扩展名为.exe文件
```



##### 三、文本操作命令

##### 3.1vim

3.1.1 vim三种模式

* 1.普通模式：默认模式，文本只读，不可编辑
* 2.编辑模式：编辑文本模式，i键进入，esc退出
* 3.命令模式：执行保存，搜索，退出等工作

3.1.2 vim常用命令

```bash
delete #删除单个字符
x #删除单个字符
dd #删除整行
/str #全文查找str字符串，n下一个，N上一个
:%s/old/new/g #替换文件内所有old字符串为new
u #撤销最近一次操作
:wq	#退出并保存
:q! #强制退出并放弃保存
```

##### 3.2常用文本工具

```bash
echo #屏幕打印或向文本输出指定内容
# > 可以将 > 左侧命令执行后产生的内容输出到右侧的文件中
echo "内容" #打印指定内容
echo "内容" > 文件名 #将内容写入指定文件(会覆盖原文件内容)
echo "内容" >> 文件名 #将内容追加写入指定文件(不会会覆盖原文件内容)
```

```bash
cat #合并文件或查看文件内容
cat 文件名 #打印文件内容
cat -n 文件名 #打印文件内容时添加行号
cat -nE 文件名 #打印文件内容时添加行号并用$显示空行
cat 文件1 文件2 >> 文件3 #将前两个文件的内容合并到第三个文件中

cat > 文件名 << EOF #向指定文件中写入输入流，输入流最后一行以EOF结尾，代表输入结束
```

```bash
tail #显示文件尾部内容
tail -n 2 文件名 #显示文件最后两行内容 
tail -f 文件名 #动态监听文件所产生新内容
```

```ba
grep #文本过滤工具，支持正则表达式
grep 要查找的文本 文件名 #在指定文件中查找指定文本的文本行
grep -v 文本 文件名 #在指定文件中查找不包含 指定文本的文本行
ll | grep 文件名 #采用通道的方式将查询结果进行过滤
```



##### 四、文件的打包和压缩

```java
tar -zcvf 产生的压缩文件名 要压缩的文件所在路径 #压缩命令
tar -zxvf 要解压缩的文件 -C 解压后文件路径的存放路径 #解压缩命令
# z ：使用gzip方式压缩
# c ：创建新的tar.gz文件	
# v ：显示执行过程
# f ：指定文件名
# x ：解压缩tar.gz文件
# C ：指定解压缩文件的存放目录-可选
```

##### 五、应用程序安装

```bash
yum search 应用名 #在仓库中查询是否存在指定应用
yum install [-y] 应用名 #全自动下载安装应用及其依赖
yum info [应用名(可选-支持正则表达式)] #查看应用详细信息
yum list installed 应用名 #查看已安装的应用程序
rpm -ql 应用名 #查看安装后输出的文件清单
yum remove -[y] 应用名 #全自动卸载指定应用
```

##### 六、linux系统管理命令

```bash
ifconfig #获取ip地址
netstat -tulpn 或者 netstat -ano #查看网络进程m 
# netstat参数说明
# t : 显示tcp传输协议的连接状况
# u : 显示udp协议连接状况
# l : 显示处于监听状态的网络连接
#	p : 显示应用PID和程序名称
# n : 显示ip地址
# a : 显示所有连接
# o : 显示计时器
ps -ef #查看系统进程
ps -ef | grep vim #查看启动命令是 vim 的进程
kill -9 PID #强制关闭PID进程
kill -s QUIT PID #正常关闭指定进程
```

##### 七、应用服务化

```bash
systemctl start #启动服务
systemctl stop #启动服务
systemctl restart #重启服务
systemctl enable #设置开机启动
systemctl disable #禁止开机启动
systemctl status #查看服务状态
daemon-reload #重载服务配置文件
list-unit-files #列出系统中所有服务和状态
```



##### 八、用户权限分配

用户与用户组常用命令

```bash
useradd #创建新用户
passwd #密码
usermod #修改用户信息/分配组（覆盖原组）
groupadd #创建新的用户组
groups #查看当前用户所属组
chown #更改文件的属主或属组
chown 属主:属组 文件名
chmod #更改文件的访问权限
chomd 750 #清空其他用户权限 
newgrp #切换当前用户组
sudo #让普通用户拥有超级管理员的执行权限
visudo #授权命令
```

![ONwcs1.md.png](https://s1.ax1x.com/2022/05/10/ONwcs1.md.png)



##### 九、防火墙设置

```bash
firewall-cmd --state #查看防火墙状态
firewall-cmd --list-ports #查看开放端口
firewall-cmd --zone=public --permanent --add-port=8080/tcp #开放单个端口
firewall-cmd --reload #针对永久性更改需要重新加载防火墙配置
firewall-cmd --zone=public --permanent --remove-port=8080/tcp #移除单个端口
firewall-cmd --zone=public --permanent --remove-port=8000-9000 #开发范围内端口
```



##### 十、Bash Shell



