# JSP
> java server Page

* 什么是jsp?
> 从用户角度看就是一个网页.从程序员角度看就是一个java类,它集成了servlet,所以可以直接说jsp既是一个servlet

* 为什么会有jsp?
> HTML多数情况下用来显示静态内容,但是有时候我们需要在网页上显示一些动态数据,比如:查询所有学生信息,根据姓名去查询具体的某个学生.这些动作都需要去查询数据库,然后在网页上显示.HTML是不支持写java代码的,jsp里面可以写java代码

## 怎么用jsp
### 指令的写法(三大指令)
 <%@  指令名字 %>

### page指令

```jsp
< %@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
```

* language
> 表命名jsp页面中可以写java代码
* contentType
>其实就是说这个文件是什么类型,告诉浏览器这个页面是什么类型的内容以及使用的是什么编码

* pageEncoding
> 页面资源编码

* extends
> 用于指定jsp翻译成java后继承的父类时是.一般不用动

* import
>导包,一般不用手写

* session
> 值只能写true和false,用于控制在这个jsp页面中是否能够直接使用session对象.默认是true,不用管

* errorpage
>指的是错误的页面,值需要给错误的页面运行.即本页面的代码运行出错,就跳转到另外的一个页面.配合iserrorPage

### include  
包含另一个jsp的内容进来
```jsp
<%@ include file="otrer.jsp"%>
```

### taglib
```jsp
<%@ taglib prefix="" uri=""%>
```
>uri:标签库路径
>prefix:标签库的别名


## JSP动作标签
<jsp:include page=""></jsp:include>
<jsp:param value="" name=""/>
<jsp:forward page=""></jsp:forward>

* jsp:include
  <jsp:include page=""></jsp:include>
>包含指定的页面,这里是动态包含.也就是不包含所有页面元素标签全部拿过来输出,而是把它的运行结果拿过来

* jsp:forward
  <jsp:forward page=""></jsp:forward>
>跳转到某个页面

* jsp:param
>在包含某个页面或者跳转到某个页面时加入这个参数
在另一个页面时,使用<%= request.getParamter("key")%>
```jsp
<jsp:forward page="other02.jsp">
    <jsp:param value="beijing" name="addresss"/>
</jsp:forward>
```

## jsp内置对象(9个)
>内置对象:可以直接在jsp页面中直接使用这些对象,不用创建
**四个作用域对象**
>表示这些对象可以存值,他们的取值范围有限定  setAttribute   getAttribute
- pageContext
- request[HTTPServletRequest]
- session[HttpSession]
- application[]
```jsp
<%
    pageContext.setAttribute("name","page");
    session.setAttribute("name","session");
    request.setAttribute("name","request");
    application.setAttribute("name","application");
%>
<%=pageContext.getAttribute("name")%>
<%=session.getAttribute("name")%>
<%=request.getAttribute("name")%>
<%=application.getAttribute("name")%>

```
### 四个作用域的区别
* pageContext
  >作用域只限于当前页面.可以通过这个对象拿到剩余的8个对象
* request
  >作用域只限于一次请求,只要服务器对该请求作出响应.这个域中的值就没有了
  request.sendRedirect()
* session
  >作用域仅限于一次会话(多次请求和响应),浏览器关了再开值就没有了
* application
  >整个工程都可以访问,服务器关闭后就不能访问

* out[JspWrite]
* response[HTTPServletResponse]

```jsp
out.Write();//先输出
response.getWrite.Write();//后输出
```
>
* page[Object,表示当前jsp翻译成java类的实例对象]
* config[ServletConfig]
* exception(isErrorPages)[Throwable,用于输出错误]
