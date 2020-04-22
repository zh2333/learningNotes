# Tomcat

## 安装
1. 直接将下载好的安装包解压，找到bin\startup.bat
2. 双击安装
3. 启动之后再浏览器窗口输入localhost:8080，查看是否出现Tomcat界面
> *注释：*  
> 安装失败记录：  
> 情况：窗口一闪而过，没有继续出现安装输出信息  
> 解决：  
> 1.查看java环境是否全部都配置好。结果：java环境全部都配置好了(排除)   
> 2.将startup.bat拖入到命令行窗口，提示CATALINA_HOME这个环境变量没有正确配置，按照网上的教程，在系统变量中新建CATALINA_HOME，在变量值中输入Tomcat的解压路径，重新双击startup.bat，界面输出安装信息，问题解决

## 把自己的东西发布到Tomcat上
1. 将文件直接拷贝到Tomcat解压目录的`webapps`目录下面
   * 直接放到ROOT目录下面，在地址栏输入localhost:8080/文件
   * 新建一个目录，放到新建目录下，在地址栏输入localhost:8080/新建目录/文件
2. 配置虚拟路径  
   在conf/server.xml这个文件中的<HOST>标签下，新建一个标签`<Context Docbase="项目路径" path="/a"></Context>` a用来指代前面的路径，是`虚拟路径`一定要以`/`打头  。  
   在浏览器地址栏输入localhost:8080/a/文件名  即可
3. 配置虚拟路径  
   在conf/Catalina/localhost路径下新建一xml文件,在文件里面填入内容`<Context Docbase="项目路径"></Context>`  
   在地址栏输入localhost:8080/文件名/文件

## 给myeclipse配置Tomcat
`window/preferences/server/add`,添加Tomcat的解压路径,然后新建web project,点击菜单栏的`manage deployments` 选择刚刚新建的工程,点击add,将新建的web工程添加到Tomcat服务器中  
运行时,右键工程,选择`Run As --> Myeclipse Web Application`

## 将servlet初始化的时机提前
在<servlet></servlet>标签下加入标签</load-on-startup>2<load-on-startup>,数字越小启动的时机越早