# JQuery
>js的代码框架

## JQuery 的load方法
用于加载servlet.很少用.一般使用get和post方法
$("#text01").load("servlet");

## GET方法
$.get(URL,callback);
url表示希望请求的URL
callback参数是请求成功能后所执行的函数
1. 不带参数
   $.get("URL");

2. 带参数
   $.get("URL?name=aa&age=18);

```js
function get(){
    //function是回调函数,data指的是从服务器端传回的数据,status是状态码
    $.get("/day16/DemoServlet02",function(data,status){
        alert("结果是:"+data);
    });
}
```

## 赋值显示
* val("aaa");
>只能放那些标签带有value属性
* HTML("aa")
* text("aa")
>这两个其实没有什么区别,如果想对着分数据做一字儿样式处理,那么要使用HTML

## post方法
$.post(URL,data,callback);
data擦书规定连同请求发送的数据,后面两个参数是可选的
callback:function(data,status){ }
```js
$.post("/day16/DemoServlet",{name:"张三",age:18},function(data,status){
    alert("结果是:"+data);
});
```

## 使用jQuery校验用户名是否被注册
```js
funciton checkname(){
    //1.获取输入框的内容
    var name = $("#name").val();
    2.发送请求
    $.post("/day16/DemoServlet",{name:name},function(data,status){
        if(data == 1){
            $("#span01").html("<font color='red'>用户名已经被注册</font>")
        }else{
            $("#span02").html("<font color='green'>用户名可用</font>")
        }
    });

}
```