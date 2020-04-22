## 使用
1. 导入两个jar文件
commons-beanutils-1.8.3.jar  
commons-logging-1.1.1.jar

2. 使用
```java
Map map = request.getParameterMap();
UserBean bean = new UserBean();
BeanUtils.populate(bean map);
```

>日期类型不能转
解决:继承Converter接口,实现convert方法
```java
//注册自己的日期转换器
ConvertUtils.register(new MyDateConverter(),Date.class);

```

### 解决的问题
```java
//不用再写 这一堆代码了
String username = request.getParameter("username");
String password = request.getParameter("password");

UserBean user = new UserBean();
user.setUsername(username);
user.setPassword(password);
```