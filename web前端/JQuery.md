# JQuery
*一种Js库，封装了js常用的功能代码。具有独特的链式语法以及高效灵活的css选择器*

## JQuery入门
```html
<!-- 导入JQ文件 -->
<script type = "text/javascript" src = "jquery-1.11.0.js"></script>
<script>
    //js中的文档加载事件
    window.onload = function(){
        alert("window111");
    }
    //JQ中的文档加载事件
    JQuery(document).ready(function(){
        alert("windows222");
    });
    //将JQuery简写成$
    $(document).ready(function(){
        alert("window333");
    });
    //最简形式
    $(function(){

    });
</script>
```
## js与jq对象的相互转换
使用js修改标签内的文本内容
```html
<script>
    function changejs(){
        var div1 = document.getElementById("div1");
        // div1.innerHTML = "已经被js修改了"
        //将js对象转换为jq对象调用jq的方法
        $(div1).html("内容已经被js修改了");
    }
    // 文档加载完成事件
    $(function(){
        //将按钮和事件通过id进行绑定
        $("#btn2").click(function(){
            // 获取目标div并将其内容进行修改
            // $("div1").html("内容已经被jq修改了");
            // 将jq对象转换成js对象
            var div = $("div1");
            div.innerHTML = "已经被jq修改了"
        });
    });
</script>
<body>
    <input type="button" value="js修改内容" onclick="changejs()"/>
    <input type="button" value="jq修改内容" id ="btn2" />
    <div id = "div1">
        内容将被js方式修改
    </div>
</body>
```
## jq中的动画效果
> *jQ开发步骤*:
> 1. *导入相关JQ包*
> 2. *页面加载完成事件*
> 3. *确定相关操作的事件*
> 4. *事件触发函数*
> 5. *在函数中做一些操作*
```html
<script type = "text/javascript" src = "jquery-1.11.0.js"></script>
<script>
    //页面加载完成事件
    $(fucntion(){
        //确定事件，将按钮与事件绑定
        $("#hidebtn").click(function(){
            //获取图片将其隐藏
            $("changeimg").hide();
            $("changeimg").slideUp();//向上滑动，收起图片
            $("changeimg").fadeIn();//淡入
            
        });

        $("#showbtn").click(function(){
            $("changeimg").show()
            $("changeimg").slidedown();//向下滑动
            $("changeimg").fadeOut();//淡出
        });

    });
</script>
<body>
    <input type = "button" value = "显示" id = "showbtn"/>
    <input type = "button" value = "显示" id = "hidebtn"/>
    <img src = "xxx.jpg" id = "changimg">

</body>
```
## 案例一：使用JQuery实现页面定时弹出广告
* 技术分析
```html
<script type = "text/javascript" src = "jquery-1.11.0.js"></script>
<script>
    function hideAd(){
        $("Ad").hide();
    }
    function showAd(){
        $("Ad").show();
        setTimeout("hideAd()",5000);
    }
    $(function(){
        setTimeout("#showAd()",3000);
    });
</script>

<body>
    <img src = "xxx.jpg" id = "Ad" style="display:none">
</body>
```

## JQuery选择器
### 基本选择器
* ID选择器： #
* 类选择器： .
* 元素选择器：标签名称
* 通配符选择器：* 找出页面上的所有元素
* 选择器分组：选择器1，选择器2 [选择器1内容,选择器2内容]
* 层级选择器 
  * 选择器1 > 选择器2
  * 后代选择器 父选择器 儿选择器
  * 相邻兄弟选择器：选择器1 + 选择器2
  * 找出所有的弟弟: 选择器1~选择器2
* 属性选择器 $("a[href][title='testTitle']")
* 表单选择器
  $(":text")
  $(":password")
  $("submit")
  $("radio") 
  * 表单对象属性的过滤器
  :selected  
  :checked

```html
示例：
<link src = "xxx.css"/>
<script src = "JQuery-1.11.0"></script>
<script>
    $(function(){
        $("#btn1").click(function(){
            $("body > div").css("background-color","red");
        });
        $("#btn2").click(function(){
            $("body div").css("background-color","bule")
        });
        $("#btn3").click(function(){
            $("one+div").css("background-color","pink")
        });
        $("#btn4").click(function(){
            $("one~div").css("background-color","green")
        });
        $("#btn5").click(function(){
        $("div:first").css("background-color","bule")//只选中第一个div，将剩下的全部都过滤掉
         });
    });
</script>
<body>
    <input type = "button" value = "找出所有的div" id = "btn5">
    <input type = "button" value = "找出body下的所有小div" id = "btn1">
    <input type = "button" value = "找出body下所有的div" id = "btn2">
    <input type = "button" value = "找出id为one的相邻兄弟div" id = "btn3">
    <input type = "button" value = "找出id为one的所有弟弟div" id = "btn4">


    <div id = "one">
        <div class = "mini">1-1</div>
    </div>
    <div id = "two">
        <div class = "mini">2-1/div>
        <div class = "mini">2-2</div>
    </div>
    <div id = "three">
        <div class = "mini">3-1</div>
        <div class = "mini">3-2</div>
        <div class = "mini">3-3</div>
    </div>
    <span id="span">span</span>
</body>
```

## JQuery基本过滤器
*语法*：`选择器+过滤规则`   
   > 1. :first
   > 2. :not(sector)
   > 3. :even 只保留索引值为偶数的元素
   > 4. :odd 只保留索引值为奇数的元素    
   > 5. :eq(index) 只保留指定索引的元素
   > 6. :gt(index) 保留索引值大于index的元素
   > 7. :lt(index) 保留索引值小于index的元素
   > 8. :last 只保留最后一个元素

## JQuery隔行换色
**过滤器:根据行的奇偶去改变颜色**
```js
$("tr:even:gt(0)").css("background-color","red");
$("tr:odd").css("background-color","green");
```

## 全选和全不选
```js
$("#checkAll").click(function(){
    $("input[type='checkbox']:gt()0").prop("checked",this.checked);//prop函数用于修改元素的属性，这里是checkbox的checked属性

});
```

## JQ中的DOM操作  
**添加元素的操作：**  
> 1. append  
> 2. appendTo
> 3. preappend 在前面添加
> 4. after 在自己的后面添加一个兄弟
```html
<script>
    $(function(){
        $("#btn1").click(function(){
            $("#div1").append(<font color="red">"互相伤害"</front>);
        });

    });
</script>
<body>
    <input type = "button" id = "" btn1/>
    <div id = "div1">
        这里等会要添加一些内容
    </div>
</body>
```

## JQ中遍历
**each方法，同时使用被遍历元素的索引或值**
```js
$(function(){
    var cities = ["杭州","苏州","南京","北京","上海"];//cities目前为js对象
    $(cities).each(function(i,n){
        console.log(i + "===" + n);
    });

    $.each(cities,function(i,n){
        console.log(i + ">>>" + n);
    });
});
```
*效果如下：*
![each](./img/jq遍历.png)

**传入一个函数作为参数**
```js
var cities = ["杭州","苏州","南京","北京","上海"];//cities目前为js对象

function callbakc1(i,n){
    console.log(i + ">>>" + n);
}

function bianli(arr,callback1){
    for(var i = 0;i<arr.length;i++){
        var item = arr[i];
        callback1(i,item);
    }
}
//调用遍历函数
bianli(cities,callback1);
```

## 常用函数
1. 操作对象属性
   prop("src","./img/1.png")
2. 修改css样式
   css()
3. 添加一个class样式  
   addClass()
4. 遍历  
   * $("数组对象").each(function(index,data){});
   * $.each(arr,function(index,datda){});

## trigger和triggerHandler
```html
<script src = "JQuery-1.11.0"></script>
<script>
    $(function(){
        $("#username").focus(function(){
            console.log("focus被触发了");
        });

        $("#btn1").click(function(){
            $("#username").trigger("focus");//触发事件,触发默认事件
            $("#username").triggerHandler("focus");//触发事件,不会触发默认事件
        });

    });
</script>

<body>
    <input type="text" id = "username"/>
    <input type="button" value="点击触发text的focus" id = "btn1"/>

</body>
```
## 案例2 JQuery完成表单校验
> *步骤*:
> 1. 给必填项胃部添加一个小红点
> 2. 获得用户输入的信息,进行相应的校验
> 3. 事件:获得焦点,失去焦点,按键抬起
> 4. 表单提交

```html
示例:
<script src = "JQuery-1.11.0"></script>
<script>
    $(function(){
        //在必填项后面加小红点
        $(".bitian").after("<font class='high'>*</font>");
        //绑定事件
        $(".bitian").blur(function(){
            //获得用户输入的值
            var value = $(this).val();

            //清空当前必选项后面的span
            $("this").parent().find(".formtips").remove();
            //判断当前接受的是哪一项输入is()
            if($(this).is("#username")){
                if(value.length<6){
                    $(this).parent.append("</span class = 'formtips onerror'> 用户名太短了</span>");
                }else{
                    $(this).parent.append("</span class = 'formtips onsuccess'> 用户名合法</span>");
                }
            }
            if($(this).is("#password")){
                if(value.length<3){
                    $(this).parent.append("</span class = 'formtips onerror'> 密码</span>");
                }else{
                    $(this).parent.append("</span class = 'formtips onsuccess'> 密码</span>");
                }
            }
        //链式调用,输入框获得焦点和按键抬起的时候都会执行一次校验,减少代码量
        }).focus(function(){
            $(this).triggerHandler("blur");
        }).keyup(function(){
            $(this).triggerHandler("blur");
        });

        //给表单绑定提交事件
        $("form").submit(function{
            $("btian").triggerHandler("focus");
            var len = $(".onerror").length;

            if(len>0){
                return flase;
            }else{
                return true;
            }
        });
    });
</script>
<body>
    <form>
        <div>
            用户名:<input type="text" class="bitian"/>
        </div>
        <div>
            密码:<input type="password" class="bitian"/>
        </div>
        <div>
            手机号:<input type="tel"/>
        </div>
        <div>
            <input type="submit" value="提交"/>
        </div>
    </form>
</body>
```
