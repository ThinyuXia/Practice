##### 连接mysql

```sqlite
mysql -u 用户名 -h 主机名 -p 端口名
```

##### 查看mysql服务状态，以及启动和停止mysql服务

```mys
sudo service mysql status
sudo service mysql start
sudo service mysql stop
```

##### 初始化root用户密码命令

```sql
ALTER 'root'@'localhost' IDENTIFIED BY "123456"
# localhost代表仅允许主机连接
```

##### 显示可用的数据库名称

```
SHOW DATABASES;
```

##### 创建数据库

```sql
CREATE DATABASE name
```

##### 删除数据库

```sql
DROP DATABASE name;
```

##### 选择要使用的数据库

```
USE 数据库名;
```

##### 展示数据库内部可用的表名称

```sq
SHOW TABLES;
```

##### 使用SHOW获取表中字段的信息

```
SHOW COLUMNS FROM 表名;
```

##### 使用DESCRIBE获取表中字段的信息

```
DESCRIBE 表名;
```

或

```
DESC 表名;
```

##### 查看建表语句

```sqlite
SHOW CREATE TABLE 表名;
```

##### 删除表

```sqlite
DROP TABLE. 表名;
```



##### 使用SELECT检索列

单个列

```
SELECT 列名 FROM 表名;
```

多个列

```
SELECT 列名,列名,列名 FROM 表名;
```

所有列

```
SELECT * FROM 表名;
```

##### 使用SELECT检索列时去重

单个列

```
SELECT DISTINCT 列名 FROM 表名;
```

多个列

```
SELECT DISTINCT 列名,列名,列名 FROM 表名;
```

所有列

```
SELECT DISTINCT * FROM 表名;
```

##### 

* *DISTINCT关键字应用于所有列而不仅是前置它的列。如果给出SELECT DISTINCT vend_id, prod_price，除非指定的两个列都相同，否则所有行都将被检索出来。*

##### 使用SELECT检索列时限制数据条数

```
SELECT * FROM 表名 LIMIT <条数>;
```



```
SELECT * FROM 表名 LIMIT <检索起始位置(差一原则)> <条数>;
```



