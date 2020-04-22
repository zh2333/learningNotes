## http
*get方法和post方法的区别:*  
1. get方法提交的数据会拼接到浏览器地址栏的请求地址后面(`不安全`),而post方法没有
2. post方式使用流的方式写数据,数据跟在请求体中,而get方式的请求数据跟在浏览器地址栏的URL后面
3. post方法的请求体汇总包含`content-length`字段(说明数据的长度),而get方法的请求体中没有这个字段
4. 现在服务器使用的一般是post方法.get方式携带的数据大小有限制,1kbw,post携带的数据大小没有限制


## servlet
*servlet是什么?*  
`其实就是一个java程序,运行在web服务器上,用于接收和响应客户端的请求和响应.Tomcat其实就是servlet的一个容器`

## hello servlet
1. 编写一个web工程,启动一个服务器
2. 测试运行web工程
3. 新建一个类,实现 `Servlet`接口,重写接口方法
4. 配置servlet,告诉服务器,我们的应用有哪些servlet(`在web-INF下的web.xml进行配置`)
   *向Tomcat登记这个应用中有哪些servlet,名字和servlet的全路径*
   ```xml
   <servlet>
	  <servlet-name>HelloServlet</servlet-name>
	  <servlet-class>com.itheima.servlet.HelloServlet</servlet-class>
   </servlet>
   <!-- * 注册Servlet名称的映射* -->
   <servlet-mapping>
  	<servlet-name>HelloServlet</servlet-name>
      <!-- 地址栏上的匹配模式 -->
  	<url-pattern>a</url-pattern>  
   </servlet-mapping>
   ```

## Srevlet通用写法
通常继承HttpServlet类然后重写里面的doGet方法和doPost方法
```java
Servlet(接口)
    |
    |
GenericServlet
    |
    |
HttpServlet(用于处理http请求)
```

## Servlet方法
1. init方法  
   一个servlet方法只会进行一次初始化.初始化工作在请求到来时完成
2. service方法  
   每来一个请求service方法就是执行一次
3. destroy方法  
   当项目熊Tomcat中移除或者正常关闭服务器时会执行这个方法

## ServletConfig
Servlet的配置,通过这个对象,可以获取servlet在配置的时候的一些信息  
下面是一些常用的方法:
```java
//1.得到servlet配置对象,专门用于在配置servlet的信息
ServletConfig config = getServletConfig();

//获取的是配置servlet里面的servlet-name的文本内容
String servletName = config.getServletName();
System.out.println("servletname=="+servletName);

//2.可以获取具体的某一个参数
String address = config.getInitParameter("address");

//3.获取所有参数的名称
Enumeration<String> names = config.getInitParameterNames();
//遍历取出所有的参数名称以及每个参数名称对应的值
while(name.hasMoreElements()){
    String key = (String)names.nextElement();
    String value = config.getInitParameter(key);

    System.out.println("key==="+key+"   value"+value);

}

```