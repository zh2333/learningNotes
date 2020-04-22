# JSTL
Jsp Standard Tag Library,jsp标准标签库,英语监护jsp的代码编写,替换<%%>的写法,一般和EL表达式配合

### 使用
1. 将jar文件拷贝进入WEB-INF/lib
   jstl.jar,standard.jar
2. 使用taglib指令导入
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

### 常用标签
```jsp
//声明一个变量name,对象的值是zhangsan
<c:set var="name" value="zhangsan" scope="session"></c:set>//默认存到page域中

//判断test里面的表达式是否满足,如果满足就执行c:if标签中的输出,c:if是没有else的
<c:set var="age" value="18"></c:set>
<c:if test="${ age > 16}" var="flag" scope="session">
    年龄大于16岁
</C:if>

//遍历,从1开始遍历到10,得到的结果赋值给i,步长为1
<c:foreach begin="1" end="10" var="i" step="1"></c:foreach>

//遍历对象
<%
List list = new ArrayList();

list.add(new User("zhangsan",18));
list.add(new User("lisi",28));
list.add(new User("wangwu",38));
list.add(new User("maliu",48));
list.add(new User("qingqi",58));

//添加到域中
pageContext.setAttribute("list",list);
%>
//items表示遍历哪一个对象,这里必须写EL表达式
//var用变量接受遍历的结果
<c:forEach var="user" items="${list}">
    ${user.name}---${user.age}
</c:forEach>
```