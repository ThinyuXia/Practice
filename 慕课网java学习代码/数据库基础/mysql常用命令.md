##### 连接mysql

```bash
mysql -u 用户名 -h 主机名 -p 端口名
```

##### 查看mysql服务状态，以及启动和停止mysql服务

```bash
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

```sql
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

```sql
USE 数据库名;
```

##### 展示数据库内部可用的表名称

```sql
SHOW TABLES;
```

##### 使用SHOW获取表中字段的信息

```sql
SHOW COLUMNS FROM 表名;
```

##### 使用DESCRIBE获取表中字段的信息

```sql
DESCRIBE 表名;
```

或

```sql
DESC 表名;
```

##### 查看建表语句

```sqlite
SHOW CREATE TABLE 表名;
```

##### 修改表名

```sql
RENAME TABLE 旧表名 TO 新表名
ALTER TABLE 旧表名 [AS] 新表名
```

##### 删除表

```sqlite
DROP TABLE 表名;
```

##### 给表添加字段

```sql
ALTER TABLE 表名
ADD 字段1 类型 约束 COMMENT 注释,
ADD 字段2 类型 约束 COMMENT 注释;
```

##### 修改表中字段类型和约束

```sql
ALTER TABLE 表名
MODIFY 字段1 类型 约束 COMMENT 注释,
MODIFY 字段2 类型 约束 COMMENT 注释;
```

##### 修改表中字段名

```sql
ALTER TABLE 表名
CHANGE 字段1 新名称 类型 约束 COMMENT 注释,
CHANGE 字段2 新名称 类型 约束 COMMENT 注释;
```

##### 删除表中字段

```sql
ALTER TABLE 表名
DROP 字段1,
DROP 字段2;
```

##### 查看表中设置的索引

```sql
SHOW INDEX FROM 表名;
```

##### 添加和删除索引

```sql
CREATE INDEX 索引名 ON 表名(字段名);
ALTER TABLE 表名 ADD INDEX [索引名](字段);

DROP INDEX 索引名 ON 表名;
```



##### 使用SELECT检索列

单个列

```sql
SELECT 列名[AS 别名] FROM 表名;
```

多个列

```sql
SELECT 列名[AS 别名],列名[AS 别名],列名[AS 别名] FROM 表名;
```

所有列

```sql
SELECT * FROM 表名;
```

##### 使用SELECT检索列时去重

单个列

```sql
SELECT DISTINCT 列名 FROM 表名;
```

多个列

```sql
SELECT DISTINCT 列名,列名,列名 FROM 表名;
```

所有列

* *DISTINCT关键字只能使用一次，并且放在第一个字段前面*

```sql
SELECT DISTINCT * FROM 表名;
```

* *DISTINCT关键字应用于所有列而不仅是前置它的列。如果给出SELECT DISTINCT vend_id, prod_price，除非指定的两个列都相同，否则所有行都将被检索出来。*

##### 使用SELECT检索列时限制数据条数

```sql
SELECT * FROM 表名 LIMIT 条数;
```

```sql
SELECT * FROM 表名 LIMIT 检索起始位置(差一原则) 条数;
```

##### 使用SELECT检索时对数据排序

```sql
# 默认采用ASC 升序排列
#一个排序条件
SELECT 字段名 FROM 表名 ORDER BY 字段名1  [ASC | DESC];
#多个排序条件
SELECT 字段名 FROM 表名 ORDER BY 字段名1  [ASC | DESC],字段名 2 [ASC | DESC];
```

##### 使用SELECT按条件检索数据

```sql
#一个检索条件
SELECT 字段名 FROM 表名 WHERE 条件1;
#多个检索条件
SELECT 字段名 FROM 表名 WHERE 条件1 [AND | OR] 条件2;
```

##### 使用SELECT中聚合函数

```sql
SELECT MIN(列名) FROM 表名;	#求指定列最小值，用于字符串时会求出字典序最小的字符串
SELECT MAX(列名) FROM 表名;	#求指定列最大值，用于字符串时会求出字典序最大的字符串
SELECT SUM(列名) FROM 表名; #求指定列累计和,只能用于数字
SELECT AVG(列名) FROM 表名; #求指定列平均值,只能用于数字

SELECT COUNT(*) FROM 表名; #求总记录数，包括NULL的记录
SELECT COUNT(列名) FROM 表名; #求不包含NULL的记录数
```

* *聚合函数不可以出现在WHERE子句中*

##### 使用SELECT进行分组查询

* *使用GROUP BY子句时，SELECT 子句中只能包含被分组的列和聚合函数，不可以包含其他字段，原因是分组字段的记录会对应多条未分组字段的记录*

```sql
SELECT 列名 [聚合函数(列名)] FROM 表名 GROUP BY 分组列名1[,分组列名2]; #存在两个分组列名时进行逐级分组

SELECT 列名 [聚合函数(列名)] FROM 表名 GROUP BY 分组列名1 WITH ROLLUP; #对分组结果集再次汇总计算

SELECT 列名 GROUP_CONCAT(未分组字段名) FROM 表名 GROUP BY 分组列名1; #将未分组字段值按分组字段拼接到一起

SELECT 列名 FROM 表名 GROUP BY 分组列名 HAVING 筛选条件; # 使用HAVING子句对不同组的数据进行筛选,可以使用聚合函数
```



##### SELECT语句中各种子句的执行顺序

FROM -> WHERE -> GROUP BY -> HAVING -> SELECT(聚合函数汇总) -> ORDER BY -> LIMIT

##### 多表连接查询

```sql
# 1.内连接 结果集中只保留符合连接条件的记录数
SELECT 字段1,字段2 FROM 表1 别名1 [INNER] JOIN 表2 别名2 ON 别名1.字段1 = 别名2.字段1 [JOIN ... ON ...]; #必须使用ON指定连接条件，否则会产生笛卡尔积，连接条件不可以使用聚合函数
# !!! 可将SELECT语句的查询结果作为表进行连接
SELECT 字段1,字段2 FROM 表1 别名1,表2 别名2 WHERE  别名1.字段1 = 别名2.字段1;
```

```sql
# 2.外连接 结果集中保留所有记录数
SELECT 字段1,字段2 FROM 表1 别名1 [INNER] LEFT JOIN 表2 别名2 ON 别名1.字段1 = 别名2.字段1 [JOIN ... ON ...];#左连接，右表字段记录不匹配时用null填充

SELECT 字段1,字段2 FROM 表1 别名1 [INNER] RIGHT JOIN 表2 别名2 ON 别名1.字段1 = 别名2.字段1 [JOIN ... ON ...];#右连接，左表字段记录不匹配时用null填充
```

* *外连接时查询条件写在WHERE里，不符合条件的记录会被过滤，写在ON里则不会*

##### 使用UNINO关键字将多个查询结果集合并

```sql
(结果集1) UNION (结果集2) UNION (结果集3) #结果集的字段数量和字段名必须相同才可以合并
```



