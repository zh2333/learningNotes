# XML

## 一.XML
1. 概念  
xml,extendible makeup language,可扩展标记语言

2. xml的作用
   * 可以用来保存数据
   * 可以用来做配置文件
   * 数据传输载体(服务器和客户端之间传输数据)

3. xml的文档结构
   **倒状树状结构**
   ```xml
   一个简单的xml文件内容:
   <?xml version="1.0" encoding="UTF-8"?>
   <stu>
    <name>zhangsan</name>
    <age>23</age>
   </stu>

   一个稍微复杂一点的xml文件内容:
   <?xml version="1.0" encoding="UTF-8"?>
   <stus>
    <stu>
        <name>zhangsan</name>
        <age>29</age>
        <desc>我爱科大</desc>
    </stu>
    <stu>
        <name>lisi</name>
        <age>34</age>
        <desc>我爱南大</desc>
    </stu>
   </stus>
   ```

4. 定义xml
   * 文档声明   
    <?xml version="1.0" encoding="UTF-8" standalone="no"?>  
    version表示解析xml文件内容时使用哪个版本的解析器  
    encoding表示用哪种编码格式去解析xml的字符  
    standalone表示这个xml文件是否是独立的.no表示会依赖或关联其他文档
   * encoding详解  
    在使用xml的时候,用什么解码去解析  --->   解码
    电脑上的文件在存储文件的时候,存储的是这些文件对应的二进制,只有用对应的解码格式去解析这些二进制才能得到正确的初始信息
   * 让xml能正常显示中文
    1.encoding为gbk 或者gb2312
    2.如果encoding为utf-8,那么在保存文件时也要保存为utf-8的编码格式
    3.保存时看到的ANSI对应的就是gbk编码
   * xml元素定义  
    <>括起来的就是元素,标签是成对出现的
    1.文档声明下面的第一个标签是根标签,根标签:<stu></stu>
    2.标签里面可以嵌套标签
    3.空标签,即是开始也是结束,一般配合属性来用,类似于HTML里面的元素标签<img/>标签  
    4.标签可以自定义  
    命名尽量简单,做到见名知义
   * xml属性定义  
    定义在元素里面,<元素名称 属性名称="值">
    ```xml
    <stu id="10086">
        <name>zhangsan</name>
        <age>29</age>
    </stu>
    ```
   * xml注释
    和HTML的注释一样,且必须放置在文档声明的下面
     <!--  -->
    ```xml
    <stu id="10086">
        <!-- <name>zhangsan</name> -->
        <age>29</age>
    </stu>
    ```

    * CDATA  
    1.非法字符:在xml中,仅有"<"和"&"是非法的.省略号,引号和大于是合法的,但是把它们替换为实体引用是个好习惯  
    < ===> &lt;  
    > ===> &gt;   
    & ===> &amp;  

    2.CDATA内部所有的东西都会被解析器忽略掉  
    如果某段字符串里面有过多的字符,并且里面包含了类似标签或者关键字的这种文字,不想让xml的解析器去解析,可以使用<![CDATA[字符串]]>
    <web><![CDATA[<a href="http://www.baidu.com">我爱科大</a>]]></web>

5. xml解析方式  
   * DOM    
    document object model,把整个xml全部读到内存中,形成树状结构.整个文档称之为document对象,属性对应Attribute对象,所有的元素节点对应Element对象,文本也可以称之为txt对象.以上所有对象都可以称之为Node节点.可以对文档进行增删操作
   * SAX
    Simple API for XML,基于事件驱动.读取一行,解析一行
    不可以进行增删,只能查询
     *针对以上两种解析方式,给出的解决方案有哪些?*  
        jaxp  
        jdom  
        dom4j ---> 比较常用

6. dom4j 入门解析案例
```xml
<?xml version="1.0" encoding="UTF-8"?>
<stus>
<stu>
    <name>zhangsan</name>
    <age>29</age>
    <address>深圳</address>
</stu>
<stu>
    <name>lisi</name>
    <age>34</age>
    <address>北京</address>
</stu>
</stus>
```

7. Dom4j 的Xpath使用  
dom4j里面支持Xpath的写法.Xpath其实是xml的路径语言,支持我们在解析xml使快速定位到xml文件
* 添加jar包依赖
  jaxen-1.1-bata-6.jar


8. xml约束 [了解]  
   如下的文档,属性的ID值是一样的.这在生活中是不可能出现的.并且第二个学生的姓名有好几个.一般也很少出现
```xml
<?xml version="1.0" encoding="UTF-8"?>
<stus>
<stu id="10086">
    <name>zhangsan</name>
    <age>29</age>
    <address>深圳</address>
</stu>
<stu id="10086">
    <name>lisi</name>
    <name>zhangsan</name>
    <age>34</age>
    <address>北京</address>
</stu>
</stus>
```
如何规定文件中的ID值唯一,或者元素只能出现一次,不能出现多次?甚至规定里面只能出现具体的元素名字
* DTD  
  语法自成一派,可读性不好
* Schema  
  其实就是一个xml,使用xml语法,解析起来很方便.SChema的文本内容比DTD多
