# Filter[使用频率比Listener高]
## 作用
>对客户端发出的请求进行过滤.浏览器发出,然后服务器派servlet处理.在中间就可以过滤,其实过滤器起到的就是拦截的作用.
1. 对一些敏感词汇过滤
2. 统一设置编码
3. 自动登录


### 如何使用过滤器
新建Filter,在web.xml中注册该filter,之后filter就会拦截所有到达服务器之前的请求
1. 定义一个类实现Filter接口
2. 在web.xml中注册过滤器
>/*是过滤所有请求,包括访问 HTML,jsp,servlet
```xml
  <filter>
    <display-name>FilterDemo01</display-name>
    <filter-name>FilterDemo01</filter-name>
    <filter-class>com.itheima.filter.FilterDemo01</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>FilterDemo01</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```


### Filter的生命周期
* 创建
>服务器启动的时候
* 销毁
>服务器关闭的时候

### 过滤器的执行顺序
>在web.xml中的mapping的顺序有关
>请求和响应都会经过过滤器.唯一不同的是请求会被过滤器拦截,而响应不会
```java
//放行
chain.doFilter(request,response);
```


### Filter的一些方法
1. init方法的参数FilterConfig,可以用于获取filter在注册的名字以及初始化参数
2. 如果想放行,那么在doFilter方法中使用参数chain
3. <url-pattern>/*</url-pattern>的写法格式和servlet一样
    * 全路径匹配
        /LoginServlet 控制访问LoginServlet
    * 目录匹配
        以/开始以*结束
        /demo01/*   访问demo01文件夹下的资源的请求都会被过滤
    * 以后缀名匹配  以*开始以后缀名结束
        *.jsp  访问jsp资源的请求都会诶拦截
4. dispatcher设置
<dispatcher>REQUEST</dispatcher> --- 只要是请求过来都拦截  
<dispatcher>FORWARD</dispatcher> --- 只要是转发都拦截,如果在servlet中有请求转发的动作,过滤器就会拦截  
<dispatcher>ERROR</dispatcher> --- 页面出错发生跳转
<dispatcher>INCLUDE</dispatcher> --- 包含页面的时候就拦截

