##### 连接mysql

```bash
mysql -u 用户名 -h 主机名 -p
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

##### 给表中字段添加约束

```sql
ALTER TABLE 表名 ADD 约束 字段名;
```

##### 修改表中字段顺序

```sql
ALTER TABLE	表名 CHANGE 字段名1 
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

##### 删除表的外键约束

```sql
AlTER TABLE 表名 DROP FOREIGN KEY 外键名
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
SELECT * FROM 表名 LIMIT 检索起始位置(差一原则),条数;
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



##### 使用SELECT子查询

* 不要在WHERE子句中使用子查询，因为每判断一条记录，都会查询一次
* 不要在SELECT子句中使用子查询，因为每输出一条记录，都会查询一次
* 子查询可写在WHERE，FROM，SELECT子句中，建议写在FROM子句中，因为FROM子句只会执行一次，查询效率较高

```sql
#WHERE 子句中的多行子查询
SELECT 字段名 FROM 表名 WHERE 字段 比较运算符 [IN / ALL / ANY]; 

SELECT 字段名 FROM 表名 WHERE [NOT] EXISTS(子查询); #使用EXISTS之后，WHERE子句中只含EXISTS条件
```



##### 使用INSERT添加数据

```sql
# 一条记录
INSERT INTO 表名 (字段1,字段2...) VALUES(值1,值2...);

# 多条记录
INSERT INTO 表名 (字段1,字段2...) VALUES(值1,值2...),(值1,值2...);

# MYSQL中INSERT方言
INSERT [INTO] 表名 SET 字段名=值,字段名=值...;

# 使用IGNORE处理插入冲突,忽略主键冲突,唯一值冲突的记录
 INSERT IGNORE 表名 SET 字段名=值,字段名=值...;
```



##### 使用UPDATE更新数据

```sql
# IGNORE忽略更新后会产生冲突的记录
UPDATE [IGNORE] 表名 SET 字段名=值,字段名=值... [WHERE 条件] [ORDER BY 字段名] [LIMIT 修改记录条数];

# UPDATE语句表连接可以修改多张表的记录
UPDATE 表1 [LEFT | RIGHT]JOIN 表2 ON 条件 SET 字段1=值,字段2=值... [WHERE ...];
UPDATE 表1,表2 SET 字段1=值,字段2=值... WHERE 条件;

```



##### 使用DELETE删除数据

```sql
# IGNORE忽略删除有外键约束的记录
DELETE [IGNORE] FROM 表名 [WHERE 条件] [ORDER BY 字段名] [LIMIT 修改记录条数];

DELETE [IGNORE] 表名(想删除记录的表) FROM 表名 [LEFT | RIGHT]JOIN 表名 ON 条件 [WHERE 条件] [ORDER BY 字段名] [LIMIT 修改记录条数];
```

使用TRUNCATE在事务机制 之外清空数据表

```sql
TRUNCATE TABLE 表名;
```



MYSQL中的基本函数 

```sql
#数字函数
ABS() #绝对值
ROUND() #四舍五入
FLOOR() #下取整
CEIL() #上取整
POWER(x,y) #幂函数
LOG() #对数
LN() #自然对数
SQRT() #开平方
PI() 
SIN()
COS()
TAN()
COT()

#字符函数
LOWER() #字符串转小写
UPPER() #字符串转大写
LENGTH() #字符串长度
LEFT(s，n) #从左到右截取n个字符
RIGHT(s,n) #从右到左截取n个字符
CONCAT(s1,s2) #拼接字符串，可接受多个参数
INSTR(s1,s2) #s2字符串在s1字符串出现位置,不存在时返回0,没有差一原则
INSERT(s1,index,0,s2) #插入字符串
INSERT(s1,index,n,s2) #在index的位置替换n个字符
REPLACE(s,s1,s2) #替换字符串
SUBSTR(s,起始位置,结束位置) #截取字符串 
SUBSTRING(s,起始位置,偏移量) #截取字符串
LPAD(s,填充后字符串长度,字符) #向字符串左侧填充字符
RPAD(s,填充后字符串长度,字符) #向字符串右侧填充字符
TRIM(s) #去除字符串首尾空格


#日期函数
NOW() #获取当前日期和时间 yyyy-MM-dd hh:mm:ss
CURDATE() #获取当前日期 yyyy-MM-dd
CURTIME() #获取当前时间 hh:mm:ss
DATE_FORMAT(日期,表达式) #格式化日期 %Y 年 %M 月(名称) %m(数字 ) %d 日 %w 星期(数字) %W 星期(名称) %U 第几周 %H 24小时 %h 12小时 % i分钟 %s 秒 %j 第几天
#日期不能直接相加减，日期也不能与数字相加减
DATE_ADD(日期,INTERVAL 偏移量 时间单位) #日期偏移计算
DATE_DIFF(日期1,日期2) #计算两个日期相差天数

#条件函数
IFNULL(val1,val2) #若val为NULL,则用val2替换val1
IF(条件,val1,val2) #三目运算符
CASE
 WHEN 条件1 THEN 值1
 WHEN 条件2 THEN 值2
 ELSE 值N
END

#分组函数
GROUP_CONCAT() #根据GROUP BY得到的数据进行拼接，用逗号
```





##### 数据库事务机制(MYSQL5.0以后支持)

事务的ACID属性

* 原子性：一个事务中的操作要么全部成功，要么全部失败，事务执行后不允许停留在某个中间状态
* 一致性：不管在任何给定的时间，并发事务有多少，事务必须保证运行结果的一致性
* 隔离性：事务不受其他并发事务的影响，在给定的时间内，该事务是数据库唯一运行的事务
* 持久性：事务一旦提交，结果就是永久性的，即便发生宕机，仍然可靠事务日志完成数据的持久化

```sql
START TRANSACTION;
SQL语句
[COMMIT | ROLLBACK]

#修改当前会话的事务隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL READ 事务隔离级别;
# READ UNCOMMITTED 读取未提交数据
# READ COMMITTED 读取已提交数据
# REPEATABLE READ 重复读数据 默认
# SERIALIZABLE  序列化
```



##### 数据导入和导出

* 数据导出的纯粹是业务数据
* 数据备份，备份的是数据文件，日志文件，索引文件等等

```bash
# 把业务数据导出成sql文件
mysqldump -uroot -p [no-data](写上no-data代表只导出表结构，否则导出表结构和数据) 逻辑库名称 > 导出路径

# 必须在mysql命令行导入sql文件
USE 逻辑库名;
SOURCE sql文件路径;

# 数据量大时把数据导出成文本文档，导出时要备份相应表结构
# 1.文本文档相对sql文件体积小，只包含数据，不包含sql语句
# 2.文本文档在导入时MYSQL不进行词法分析和语法优化，直接写入数据，写入速度非常快
```
