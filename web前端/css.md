# CSS层叠样式表
css样式定义在head中，用<style> </style> 标签修饰

## 1.css选择器
css选择器一共有三种，分别是元素选择器，类选择器，标签选择器
* 元素选择器  
  用于选择HTML语言中定义的元素，比如img，div，span 
    ```css
    div{
    color:red;
    font-size:10px;
    }
    ```
* 类选择器  
  用于修饰被定义为类别的样式(class = 'shuiguo')
  
  ```css
  .shuiguo{
    color:red;
    font-size:10px; 
  }
  ```
* ID选择器
  用于修饰指定名称的元素的样式  (id = 'div1')
  
  ```css
  #div1{
    color:red;
    font-size:10px;
  }
  ```
* 选择器分组    
  修改一组选择器里面的属性  
  ```html  
  语法：
    选择器1,选择器2,选择器3{  
    属性  
  }
  ```
* 属性选择器  
  修改包含某种属性的标签的样式  
    ```html
    包含一个属性
    a[title]{
        color:red;
    }
    ```
    ```html
    包含多个属性  
    a[href][title]{
        color:red;
    }
    ```
* 后代选择器  
  修改被某个标签包含的某个标签的样式，中间以空格隔开
  ```html
  <!-- 修改h1 标签中的em标签的样式 -->
  h1 em{
      color:red;
  }
  ```


## 2.css样式的引入方式
* 内部样式   
  上面定义在head中
* 外部样式   
  新建一个.css文件,如果需要在一个HTML文件中使用该样式，在head中添加link标签即可
  ```html
  示例：
  <!--rel 表示引入的是一个css样式表，href表示路径-->
  <link rel="stylesheet" href="style.css"/>
  ```
* 行内样式    
  直接在标签中添加style属性  
  
  ```html
  <div class="shuiguo" style="color:green">甘蔗</div>
  ```

## 3.css浮动  
应用：*流式布局-图片和文字混排时设计文字环绕*  
根据浏览器窗口的大小，自动改变页面内容的布局   
> float 属性，在正常的文本流中不占空间
  * left 向左浮动
  * right 向右浮动
> clear 属性，清除浮动，both：两边都不允许浮动，left:左边不允许浮动，right:右边不允许浮动    


```html  
<div style="float:left;color:green">甘蔗</div>
```


##  4.块标签   
使用表格对页面布局进行设计的方式太生硬，用块标签。相当于表格中每一个单元格都是一块内容，这样后期在调整页面布局的时候会很方便
* div 标签   
  会对本*div*标签之后的内容进行自动换行
* span   
  不会对本*span*标签后的内容进行自动换行

##  5.扩展：css选择器的优先级   
多个选择器都修饰一个标签时
> 优先级：  
> * 按照标签检索准确度来分：行内选择器 > ID选择器 > 类选择器 > 元素选择器  
> * 同一种类型的选择器：就近原则(那个选择器距离标签近，就使用哪一个标签)

##  6.盒子模型  
* 内边距   
  控制盒子的内距离  
  padding-top  
  padding-left  
  padding-right  
  padding-bottom 
  >注释  
  padding：10px;//上下左右都是10px  
  padding：10px 20px;//上下是10px,左右是20px 
  padding：10px 20px 30px;//上10px,下30px,左20px  
  padding:10px,20px,30px,40px;//上 右下左，顺时针方向
* 外边距  
  控制盒子与盒子之间的距离  
  margin

## 7.绝对定位  
position:absolute  
top  
left
