# MySQL
## 1.数据库概述
* 什么是数据库
  * 数据库就是一个文件系统,只不过我们需要通过命令(SQL语句)来操作这个文件系统
  * 数据库是按照数据结构来组织,存储和管理数据的建立在见算计存储设备上的仓库
  * 数据库是长期存储子啊计算机内部,有组织,可共享的数据结合,数据库中的数据指的是以一定的数据模型组织,描述和存储在一起,具有尽可能小的冗余度,较高的数据独立性和易扩展性的特点并可在一定范围内为多个用户共享
* 数据库的作用
  存储数据,是数据的仓库并带有访问权限,限制不同人,不同人有不同的操作

## 2.常见的数据库
**MySQL:** 开源免费的适用于中小企业的免费数据库,sun公司被收购后,MySQL开始收费  
**marladb:** 是MySQL开源版本的一个分支,基本上所有的命令都是一样的    
**Oracle:** 甲骨文,商业软件,收费的,适用于大型电商网站  
**db2** :IBM-->解决方案-->软件和硬件-->服务器架构,我国的银行系统大多用的是db2  
**sqlserver:** window里面,政府网站一般会使用(asp.net),大学教学都是用SQLserver,其图形化界面做的不错  
**sybase:** 被淘汰   
  
**NOSQL:** 非关系型数据库:key:value   
**mongodb:**   
**redis:** 

## 关系型数据库(E-R图,实体-关系图)
主要是用来描述实体与实体之间的关系  
员工`属于`部门

> *注释:*  
> 1. 非关系型数据库中的数据的存放形式是{key:value} 
> 2. E-R图的表示   
> 实体-->方框  
> 属性-->椭圆  
> 关系-->菱形

## MySQL数据库服务器
**MySQL数据库:** 数据库管理软件  
**服务器:** 一台电脑,安装了相关的服务器软件,这些软件会监听不同的端口号,根据用户访问的端口号,提供不同的服务

## MySQL安装与卸载
* 卸载
  * 打开控制面板,删除软件
  * 删除MySQL安装目录下的所有文件C:program Files\MySQL
  * 删除MySQL数据存放文件C:\ProgramData\MySQL,隐藏文件夹

## MySQL 的SQL语句
* **DDL:**   
  *Data Define Language*,数据定义语言,定义数据库,数据表的结构
  >create(创建)  
  >drop(删除)  
  >alter(修改)
* **DML:**   
  *Data manage Language*,数据操作语言,主要是用来操作数据
  >insert 插入  
  >update 修改  
  >delete 删除
* **DCL:**  
  *Data Control Language*,数据控制语言,定义访问权限,取消访问权限,安全设置
* **DQL:**  
  *Data Query Language*,数据查询语言
  >select 查询  
  >from  子句  
  >where  子句

## 命令行操作
* 登录服务器  
  `mysql -uroot -p1234`
* 创建数据库  
  1. 简单的创建一个数据库  
   `create database 数据库的名字;`
  2. 创建数据库的时候指定字符集  
   `create database 数据库的名字 character set utf8;`
  3. 创建数据库的时候指定校验规则  
   `create database 数据库的名字 character set utf8 collate utf8-bin;`
    
* 查看数据库
  1. 查看所有的数据库  
  `show databases;`
  2. 查看某个数据库定义的语句  
  `show create database数据库的名字;`

* 修改数据库的操作
  1. 修改字符集  
  `alter database 数据库的名字 character set 字符集;`

* 删除数据库  
  `drop 数据库名字;`

* 其他数据库操作命令
  1. 切换当前使用的数据库  
   `use 数据库的名字`
  2. 查看当前使用的数据库  
   `select database();`

## 表操作
* 创建表  
  `create table 表明{列名1 数据类型 约束,列名2 数据类型 约束};`
  > *列的类型:*  
  int  
  char(固定长度)/varchar(可变长度)-->长度代表的是字符的个数不是字节个数  
  double  
  float  
  date--->年:月:日  
  time--->时:分:秒  
  datetime--->年:月:日 时:分:秒  `默认值为null`   
  timestamp--->年:月:日 时:分:秒   `默认使用当前时间`  
  text--->主要用来存放文本(小说)  
  blob--->二进制  

  > *列的约束*  
      1. 主键约束:primary key
      2. 唯一约束:unique  
      3. 非空约束: not null  


  > *创建一个表*    
      1. 分析实体:学生  
      2. 学号  
      3. 姓名  
      4. 性别  
      5. 年龄  
    ```sql
    create table student{
        sid int primary key,
        sname varchar(31) not null,
        sage int not null,
        ssex char(1) not null
    }
    ```
* 查看表  
  1. 查看所有的表   
   `show tables;`
  2. 查看表的定义  
   `show create tables;`  
  3. 查看表的结构  
   `desc 表名;`

* 修改表  
    ```sql 
    1. 添加列(add)
    alter table 表名 add 列名 列的类型 列的约束;
    2.修改列(modify)
    alter table student modify sex varchar(2);
    3.修改列名(change)
    alter table student change sex gender varchar(2);
    4.删除列(drop)
    alter table student drop 成绩;
    5.修改表名(rename)
    rename table student to teacher;
    6.修改表的字符集  
    alter table student character set gbk;
    ```
* 删除表
  ```sql
  drop table student;
  ```
## 表中数据的CRUD操作
* 插入数据
  ```sql
  insert into 表名(列名1,列名2,列名3) values(值1,值2,值3);
  --简单写法
  insert into表名(值1,值2,值3)
  --批量插入
  insert into 表名 values(值1,值2,值3),values(值1,值2,值3),values(值1,值2,值3);
  ```
  > *在命令行下插入中文的乱码问题*  
  **解决方案:**   
  修改my.ini配置(在MySQL安装路径下)  
      1. 暂停MySQL服务  
      2. 按MySQL的安装路径中找到my.ini配置文件:C:\program Files\MySQL   Server  
      3. 将57行的编码格式改成gbk  
      4. 启动MySQL服务

## 删除记录
```sql
delete from 表名 where 条件
```
> *面试问题:delete删除数据和truncate删除数据有什么差别*  
**delete:** 属于DML,删除数据是一条一条的删除  
**truncate:** 属于DDL,先删除表再重建表  
*哪一条的执行效率高?*  
如果表中数据少,delete效率高  
如果表中数据局多,truncate效率高

## 更新表记录
```sql
update 表名 set 列名1=列的值,列名2=列的值 where 条件
```

## 查询记录
```sql
select [distinct] [*] [列名1][列名2] from 表名 [where 条件]
--distinct表示去除重复的数据

--商品分类
1.分类ID
2.分类名称
3.分类描述

create table category(
    cid int primary key auto_increment,
    cname varchar(10),
    cdesc varchar(31)
);
insert into category values(null,'手机数码','电子产品,黑马生产');
insert into category values(null,'鞋靴箱包','江南皮革厂生产');
insert into category values(null,'香烟酒水','中烟制造,茅台');
insert into category values(null,'牛奶饼干','营养健康');
insert into category values(null,'馋嘴零食','好吃你就多吃点');

--所有商品
1.商品id
2.商品名称
3.商品价格
4.生产日期
5.商品分类

--商品和商品分类进行关联
create table product(
pid int primary key auto_increment,
pname varchar(10),
price double,
pdate timestamp,
cno int
);

insert into product values(null,'小米mix4',3299,null,1);
insert into product values(null,'坚果Pro',2699,null,1);
insert into product values(null,'阿迪王',99,null,2);
insert into product values(null,'老村长',88,null,3);
insert into product values(null,'劲酒',35,null,3);
insert into product values(null,'小熊饼干',1.5,null,4);
insert into product values(null,'卫龙辣条',2,null,5);
insert into product values(null,'酒鬼花生',2.5,null,5);
```

* 查询操作集合
  
  * 简单查询  
  ```sql
  select * from product;
  ```
  * 查询指定列  
  ```sql 
  select pname,price from product;
  ```
  * 别名查询 使用as 关键字(多用在多表查询)  
  ```sql
  select p.name,p.price from product as p;
  ```
  * 去掉重复的值--查询所有商品的价格     
  ```sql
    select distinct price from product;
  ```
  * select 运算查询  
  ```sql
  select *,price*0.7 from product;  
  select *,price*0.7 as 折后价 from product;
  ```
  * 条件查询[where 关键字]
指定条件
   ```sql
   --查询价格大于60的商品
   select * from product where price>60;

   --where后面的条件的写法
      --关系运算符:> >= <= = != <>
      <>:不等于,标准的sql语法
      !=:不等于,非标准的sql语法
      
      --查询商品价格不等于88的商品
      select * from product where price <> 88;
      select * from product where price != 88;

      --查询价格在88和100之间的商品
      select * from product where price>88 and price<100;
      select * from product where price between 88 and 100;

      --逻辑运算符
      and, or, not

    --like:模糊查询
    _  :代表的是一个字符
    %  :代表的是多个字符
      --查询名称中带有饼的所有商品
      select * from product where pname like '%饼%';
      --查询第二个名字是熊的所有商品
      select * from product where pname like '_熊%'
    
    --in 在某个范围内获得值
        ---查询商品分类为1,4,5的所有商品
        select * from product where cno in(1,4,5);
    
    --排序查询  order by 关键字
    asc : ascend 升序(,默认)
    desc : descend 降序
        --查询所有商品 按照价格进行排序
        select * from product order by price desc;
        --查询名称中带有小的商品按照降序排序
         select * from product where pname like '%熊%' order by price desc;
   ```
   * 聚合函数  
    sum() 求和  
    avg() 求平均值  
    count() 统计数量  
    max() 最大值  
    min() 最小值  
    ```sql
    --获得所有商品的价格总和
    select sum(price) from product;
    ...
    --获得所有商品的个数
    select count(*) from product
    --注意:where关键字后面的条件不能使用聚合函数.having关键字后面可以接聚合函数
    select * from product where price> (select avg(price) from product)是合法的
    ```

    * 分组 group by
    ```sql
    --根据cno字段分组,分组后统计每一类商品的数量
    select cno,count(*) from product group by cno;

    --根据cno分组,分组统计每组商品的平均价格,并且平均价格>60
    select cno,avg(price) from product group by cno having avg(price) > 60;
    ```
    * sql语句中各个关键字出现的位置 
    ```sql
    select ... from ... where ... group by ... having ... order by ...
    ```

    * sql语句中各个关键字执行的顺序  
    ```sql
    from ... where ... group by ... having ... select ... order by ...
    ```


