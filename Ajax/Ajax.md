# Ajax
>Ajax,异步JavaScript和xml,是指一种创建交互式网页应用的网页开发技术,并 不是一种新的技术,而是多种技术的集合体.它由以下几种技术组成:
1. 使用css和HTML来表示
2. 使用DOM模型来交互和动态显示
3. 使用XMLHTTPSRequest来和服务器进行异步通信
4. 使用JavaScript来绑定和调用

## 有什么用
网页刷新局部内容.以前需要重新载入整个网页.Ajax就是为了解决局部刷新的问题,其他部分保持不动

## Ajax数据请求 Get
>创建XMLHTTPRequest对象利用此对象 与服务器进行通信时Ajax技术的核心
```js
<script type="text/javascript">
		function ajaxFunction(){
			var xmlHttp;
			try{
				xmlHttp = new XMLHttpRequest();
			
			}catch(e){
				try{
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){}
			}
			return xmlHttp;
		}
		function get(){
			//1.创建XMLHTTPRequest对象
			var request = ajaxFunction();
			//2.发送请求
			/*
			参数一:请求类型
			参数二:请求路径
			参数三:是否异步
			*/
			request.open("GET","DemoServlet01",true);
			request.send();
		}
	</script>
```

## Ajax请求和获取数据
1. GET方法
```js
request.open("GET","DemoServlet01?name=aa&age=18",true);
//3.获取响应数据
request.onreadystatechange = function(){
    if(request.readyState==2 && request.status == 200){
        alert(request.responseText);
    }
}
request.send();
```
```java
String name = Request.getParamter("name");
```
* 如果需要获得来自服务器的响应,请使用XMLHTTPRequest对象的responseText或者responseXML属性
  1. responseText 获得字符串形式的响应数据
   在服务器端即servlet上写上response.getWrite.write("文本");
   在HTML页面上的get/post方法内部写上request.responseTxt,即可获取服务器端返回的字符串
  2. responseXML 获得XML形式的响应数据

2. POST方法
调用的方式和使用的规则基本上和GET方式是相同的,唯一不同的是在发送数据.POST方法使用send方法发送数据的.并且要加上头部
```js
request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//这个头一定要写,不然数据传不过去
request.open("GET","DemoServlet01?name=aa&age=18",true);
request.send("name=aobama&age=56");
```
