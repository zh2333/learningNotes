# EL表达式
*为了简化jsp代码*

* 写法格式
  ${表达式}

### 如何使用

1. 取出作用域中的值[存值还是按照原来的方法存]
   ${pageScope.name}
   >${name}区别:先从pageScope找,再到request找,再到session找,再到response里面找
   ${sessionScope.name}
   ${requestScope.name}
   ${responseScope.name}

2. 如果域中存的是数组
   ```jsp
   <%
    Sring [] a={"aa","bb","cc","dd"}
    pageContext.setAttribute("array",a);
   %>

   <!-- 使用EL表达式取出作用域数组中的值 -->
   ${array[0]}
   ``` 

### EL11个内置对象
${对象名.成员名}
* 作用域相关  
  pageScope  
  requestScope  
  sessionScope      
  applicationScope
* 请求头相关  
  header  
  headerValues  
* 请求参数相关   
  param
  ```jsp   
  <%=request.getParameter("address")%>
  ${param.address}
  ```

  params  
* 其他  
  cookie  
  initparam  
  pageCOntext  