# JavaScript   
一种直译式脚本语言   
* 什么是脚本语言  
  java语言：源代码-->编译成.class文件-->java虚拟机才能执行  
  脚本语言：源码-->解释执行   
  js代码由浏览器解释执行


  >注释：    
  HTML：决定了页面的框架   
  css:美化页面   
  js:提供用户交互   

|js的组成| | |
|---|---|---|
|ECMAScript|DOM|BOM|

  ECMAScript：js的核心部分，规范了语法规范  
  DOM：文档对象模型，用来管理页面   
  BOM：浏览器对象模型，前进，后退，页面刷新，地址栏，历史记录，屏幕宽高    

  >注释：   
  1.变量弱类型  var i = 1  
  2.区分大小写  
  3.语句结束之后的分号，可以有也可以没有      
  4.js代码写在script标签内部  

* js的数据类型  
  * 基本类型
    * string
    * number
    * boolean
    * undefined
    * null
  * 引用类型  
  对象,内置对象
  * 类型转换
  js内部转换

  >注释：  
  “===”：值和类型全都相等  
  “==”：值相等即可  

* js的输出  
  *  向页面输出  
  ```html
  document.write("无敌！")
  innerHTML()
  ```
  * 直接弹窗
  ```html 
  alert()
  ```
  * 向控制台输出
  ```html
  console.log()
  ```
  * 获取页面元素  
  ```html
  document.getElementByid("id的名称")
  ```
* js声明变量和函数  
  * 声明变量  
  var 变量名称 = 变量的值  
  * 声明函数  
  var 函数的名称 = function{

     }  
  function 函数的名称(参数名称1,参数名称2,...){

    }

* js 开发步骤  
  1.确定事件  
  2.事件会触发一个函数  
  3.函数里面通常都会去操作页面元素，做一些交互  
  ```html
  **简单交互**
  <script>
    function dianwo(){
        alert("我被点击了")
    }
  </script>

  <input type = "button" value = "点击我" onclick = "dianwo">//onclick代表点击事件将这个按钮与函数进行绑定
  ```
  ```html
  **点击对页面元素进行改变**
  <script>
    function dianwo(){
        <!-- alert("我被点击了") -->
        var div = document.getElementById("div1")
        div.innerHTML = "<font color="red">内容被替换了</font>"
    }
  </script>

  <input type = "button" value = "点击我" onclick = "dianwo">
  <div id = "div1">这里的内容在点击按钮后会被替换<div>
  ```
  ```html
  **数据校验**
  <!-- 
      校验用户名，长度不能少于6位
      1.确定事件：提交事件 onsubmmit
      2.事件触发函数 oncheckForm()
      3.函数中做一些校验
   -->
   <script>
   function checkForm(){
       //获取用户输入的内容
        var input1 = document.getElementById("username");
        var uValue = input1.value;

        if(uValue.length >= 6){
            return true;
        }else{
            alert("用户名太短了！")
        }
        return false;
   }
   </script>
   <form action = "xxx.html" onsubmmit = "return checkForm()" >//提交事件比较特殊，必须有返回值
   用户名:<input type="text" id = "username"/><br />
   <input type = "submit" value = "提交" />
   </form> 
  ```