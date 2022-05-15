##### 1.redis启动命令

```bash
./redis-server redis.conf
```

##### 2.redis常用基本配置

```bash
daemonize yes #启用后台运行，默认为no
port 端口号 #设置端口号，默认6379
logfile 日志文件 #设置日志文件
databases 数据库数量 #设置redis数据库总量,是数量不是下标的范围
dir 数据文件目录 #设置数据文件存储目录
requirepass 密码 #设置使用密码
```

##### 3.redis通用命令

```bash
select 0 #选择0号数据库
set name lily #设置key = name ，value=lily
mset key1 val1 key2 val2 ... #一次性设置多个值 
get hello #获得key=hello的值
mget key1 key1 ... #一次性获取多个值
keys he* #根据pattern查询符合条件的key
dbsize #返回key的总数
exists a #查询key=a的键是否存在
del a #删除key=a的数据
expire hello 20 #设置key=hello 20秒后过期
ttl hello #查看key=a的过期剩余时间
incr key #将指定key的数字值自增1
decr key #将指定key的数字值自减1
incrby key  value#将指定key的数字值增加value
decr key #将指定key的数字值减少value
rpush listkey a b c #在数组右侧尾部添加a，b，c三个元素 
lpush listkey a b c #在数组左侧尾部添加a，b，c三个元素
rpop listkey #从右侧弹出一个元素
lpop listkey 2#左侧弹出2个元素
lrange listkey 0 -1 #输出数组所有元素

```

