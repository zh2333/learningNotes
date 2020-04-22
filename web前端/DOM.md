# DOM  

## 1.基础知识
文档对象模型，管理文档，增删改查，定义了访问和操作HTML文档的标准  
*一个HTML文档加载到内存中，最终是以DOM树的形式呈现的*
一个简单的DOM树如下：
![DOM树](./img/DOM树.png)
> *注释*：  
> * 其中元素、文本、属性统称为节点。HTML中定义了最这些节点/对象进行操作的方法。**HTML中的DOM对象有Document、Element、Attribute、Event**
>  * 一些常用的HTML DOM方法
> ```js
> getElementById(id) //获取带有指定id的节点或元素
> appendChind(node) //插入新的节点(元素)
> removeChild() //删除节点或者元素
> ```
>  * 一些常用的HTML DOM属性
> ```js
> innerHTML //节点(元素)的文本值
> parentNode //节点(元素)的父节点
> childNode //节点(元素)的子节点
> attribute //节点(元素)d的属性节点
> ```
> * 查找节点
> ```js
> getElementById(id) //获取带有指定id的节点或元素
> getElementsByTagName() //返回带有指定标签名称的所有元素的节点列表(集合/节点数组)
> getElementByClassName() //返回包含带有指定类名的所有元素的节点列表
> ```
> * 增加节点
> ```js
> createAttribute() //创建属性节点
> createElement() //创建元素节点
> createTextNode() //创建文本节点
> insertBefore() //在指定子节点前面插入新的节点
> appendChild() //把新的子节点添加到指定节点
> ```
> * 修改节点
> ```js
> settAttribute() //修改属性
> setAttributeNode() //
> ```

## 2.实例
 * 在div标签中动态添加p标签
  ```html
  <script>
    function addp(){
        
       
        var div = document.getElementById("div1");
        //1.创建一个p标签(创建元素节点)
        var p1 = document.createElement("p");//<p></p>
         //2.创建文本节点
        var textNode = document.createTextNode("文本内容");//p标签中包含的文本内容(创建文本节点)
        //3.将p标签和文本内容关联起来
        p.append(textNode);
        //4.将p标签添加到div标签中
        div.append(p);
    }
  </script>
  <body>
    <div>
        <input type="button" value="点击添加p标签" onclick="addp()">
    </div>
  </body>
  ```
  **结构如下**：
  ![添加p标签](./img/appendPTag.png)

  * 省市联动
  ```html
  <script>
    var provinces = [["深圳市","东莞市","惠州市","广州市"],["长沙市","岳阳市","株洲市","湘潭市"],["厦门市","福州市","潭州市","泉州市"]];
    /*
    1.onchange 事件
    2.selectProvince() 函数
    3.得到当前操作的是哪一个省份，从数组中动态取出对应城市的信息
    4.动态创建城市元素节点<option></option>
    5.添加城市节点到select中
    */
    function selectProvince(){  
        //得到当前选中的省份
        var province = document.getElementById("province");
        var value = province.value;
        var cities = provinces[value];
        var citySelect = document.getElementById("city");
        //每次选中一个新的省份都要将之前的城市option清空
        citySelect.option.length = 0;
    
        for(var i=0;i<cities.length;i++){
            var cityText = cities[i];

            //创建option节点
            var option1 = document.createElement("option");
            //创建城市文本节点
            var textNode = document.createTextNode(cityText);
            //将option和文本关联起来
            option1.append(textNode);
            //将选中省份的城市添加到DOM树种
            citySelect.append(option1)

        }
    }
  </script>
  <body>
  <!-- 选择省份 -->
    <select onchange="selectProvince()">
        <option value="-1">--请选择--</option>
        <option value="0">广东省</option>
        <option value="1">湖南省</option>
        <option value="2">福建省</option>
    </select>
    <select id="city">
        <option>--请选择--</option>
    </select>
  </body>
  ```
