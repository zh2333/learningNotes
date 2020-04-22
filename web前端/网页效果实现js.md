# 网页图片效果的集合

## 图片轮播  
**需求**：有一组图片，每隔几秒钟就切换一张图片，一直不停的在切换   
**分析**：  
1.切换图片  
2.定时器
```js
setInterval("代码串/函数名称",间隔);//每隔多长时间就会执行一次,执行后会返回一个定时器的ID
setTimeout("代码串/函数名称",倒计时);//只会执行一次，在倒计时结束后就不再执行，执行后会返回定时器的ID
clearInterval(定时器ID);
clearTimeout(定时器ID);
```

**实现步骤**：  
* 确定事件 onload事件
* 事件触发：init()
* 函数里面进行一些交互，操作页面元素
  * 开启定时器
  * changeimage()

```js
Demo:
<script>
var index = 0;

function changeimage(){
    var img = document.getElementById("img1");

    var curindex = index % 3 + 1;
    img.src = "../img/" + curIndex + ".jpg";
    index = index + 1;
}
function init(){
    setInterval("changeInterval()",1000);
}
</script>
<body onload="init()">
<img src = "../img/0.jpg" width = "100%">
</body>
```   


>*注释*：
onload事件支持的标签有 `body,frame,img,link,script,iframe,frameset`  

## 页面定时弹出广告后隐藏   
* 技术分析
  * 定时器
  * 显示广告 
  ```js
  img.style.display = "block";
  ```
  * 隐藏广告 
  ```js
  img.style.display = "none";
  ```
  > *注释*：js中的文档加载顺序是从上往下的

* 步骤分析
  * onload() 页面加载完成的事件 
  * 事件触发函数init()
  * 在init中做一件事：
    * 启动一个定时器
    * 显示一个广告
      * 再开启一个定时器关闭广告 
```js
function hideimg(){
    var img = document.getElementById("img1");
    img.style.display = "none";
}
function showimg(){
    var img = document.getElementById("img1");
    img.style.display = "block"
    setTimeout("hideimg()",3000);
}
function init(){
    setTimeout("showimg()",3000);

}
<body onload = "init()">
<img id = "img1" src="xxx.jpg" width="100%" style="display:none">
</body>
```   

## 表单校验的完善  
校验输入框里的内容是否合法
```html
<script>
// onfocus事件
// 函数和onfocus事件绑定
//函数进行一些处理
function showtips(spanID,msg){
    //输入用户名的时候显示提示信息
    var nametips = document.getElementById(spanID)
    nametips.innerHTML = msg;
}
function checkname(){
    //当用户名输入完成，光标离开输入框，校验输入是否合法
    // onblur事件

    //获得用户输入的内容
    var name = document.getElementById("usrename");
    //对输入的内容进行校验
    if(name.length < 6){
        //获得输出的span
        var uspan = document.getElenemt("span_username");
         //显示校验结果
        uspan.innerHTML = "<font color="red" size = 2>用户名太短</font>"
        return flase
    }else{
        uspan.innerHTML = "<font color="red" size = 2>用户名可用</font>";
        rerurn true
    }
}
function checkpassword(){
    var password = document.getElementById("password");
    if(name.length < 6){
    //获得输出的span
    var uspan = document.getElementById("span_password");
    //显示校验结果
    uspan.innerHTML = "<font color="red" size = 2>密码太短</font>"
    return flase
    }else{
        uspan.innerHTML = "<font color="red" size = 2>密码可用</font>";
        return true
    }
}

function checkrepassword(){
    var orpassword = document.getElementById("password").value;
    var repassword = document.getElementById("repassword").value;
    var uspanrepassword = document.getElementById("span_repassword");
    if(orpassword != repassword){
        uspanrepassword.innerHTML = "<font color="red" size = 2>密码不一致</font>";
        return flase
    }
    else {
         uspanrepassword.innerHTML = "<font color="red" size = 2>密码一致</font>";
         return true
    }
    }
    function ckeckform(){
        var flag = ckeckname() && ckeckpassword() && checkrepassword() && ckeckmail() && checkphone()

       return flag
    }
</script>
<body>
    <form action ="../xxx.html" onsubmit = "return checkform()">
    用户名：<input type = "text" id = "username" onfocus = "showtips('span_usrename','用户名长度不能少于6个字符')" onblur = "checkname()" onkeyup = "checkname()"/><span id = "span_username"> </span><br/>
    密码：<input type = "password" id = "password" onfocus = "showtips('span_password','密码长度不能少于8位')" onblur = "checkpassword()" onkeyup = "checkpassword()"/><span  id = "span_password"><br/>
    确认密码：<input type = "password" id = "password" onfocus = "showtips('span_password','两次密码必须一致')" onblur = "checkrepassword()" onkeyup = "ckeckrepassword()"/><span id = "span_repassword"><br/>
    邮箱：<input type = "email" id = "email"/><br/>
    手机号:<input type = "text" id = "text"/><br/>

    <input type = "submit" value = "点击提交">
    </form>
</body>
```
> *注释*：  
> * **onkeyup** 案件每一次按下抬起就会触发一次时间处理函数。这里的逻辑是每输入一个用户名字符就对整个用户名进行校验  
> **onfocus** 获得焦点事件  
> **onblur** 失去焦点事件  
> * 可以像css一样，将js文件定义在外部，要使用时再从外部引入，提供代码复用
> ```html
>  <script type = "text/javascript" src = "xxx.js"></script>    
> ```

## 使用js控制下拉列表左右选择
```html
<script>
    /*
    1.点击事件 onlick
    2.事件触发函数 selectone
    3.函数操作
        将左边选中的元素移到右边
        1.获取左边被选中的元素
        2.将选中的元素添加到右边的select中
    */
    function selectone(){
        var leftSelect = document.getElementById("leftselect");
        var rightSelect = document.getElementById("rightselect");
        var options = leftSelect.options;
        for(var i = 0;i < options.length;i++){
            var option1 = options[i];
            if(option1.selected){
                rightSelect.append(option1);
            }
        }

    }

    function selectall(){
        var leftSelect = document.getElementById("leftselect");
        var rightSelect = document.getElementById("rightselect");
        var options = leftSelect.options;
        for(var i = option.length;i >= 0;i--){
            var option1 = options[i];
            rightSelect.append(option1);
        }
    }
</script>

<body>
    <table border="1px" width = "400px">
        <tr>
            <td>分类名称</td>
            <td><input type="text" value="手机数码"></td>
        </tr>
        <tr>
            <td>分类描述</td>
            <td><input type="text" value="这里面都是手机数码"></td>
        </tr>
        <tr>
        <tr>
            <td>分类商品</td>
            <td> 
                <div style="float:left">
                    已有商品<br/>
                    <!-- 双击将左边的option移动到右边 -->
                    <select multiple="multiple" id="leftselect" ondbclick = "selectone()" > 
                        <option>华为</option>
                        <option>小米</option>
                        <option>锤子</option>
                        <option>OPPO</option>
                    </select>
                    <!-- 超链接 &gt表示大于号-->
                    <a href="#" onlick="selectone()">&gt;&gt;</a><br/>
                    <a href="#" onlick="selectall()">&gt;&gt;&gt;</a>
                </div>
                <div style="float:right">
                    未有商品<br/>
                    <select multiple="multiple" id="rightselect">
                        <option>苹果x</option>
                        <option>诺基亚</option>
                        <option>vivo</option>
                        <option>坚果</option>
                    </select>
                    <!-- 超链接 &lt表示小于号-->
                    <a href="#">&lt;&lt;</a><br/>
                    <a href="#">&lt;&lt;&lt;</a>
                </div>
            </td>
        </tr>
            <!-- 合并单元格用cospan -->
            <td cospan="2"><input type="submit" value="提交"></td>
        </tr>
    </table>
</body>
```
*上述代码效果如下：*  
![js左右选择](./img/下拉效果图1.png)
