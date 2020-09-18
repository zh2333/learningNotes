##  s微服务气象实战



### SpringBoot

1. 更快的入门
2. 开箱即用, starter
3. 具有spring的优点
4. 抛弃了传统的javaEE项目繁琐的配置, 学习过程, 让企业级应用开发过程变得简单

![image-20200913100426076](E:\learningNotes\microServiceWeather\pic\image-20200913100426076.png)

spring boot是springcloud的基石



#### 第一个springboot项目

1. 开发环境

![image-20200913100617871](E:\learningNotes\microServiceWeather\pic\image-20200913100617871.png)

2. 使用gradle构建项目

   build.gradle 文件

   ![image-20200913102000109](E:\learningNotes\microServiceWeather\pic\image-20200913102000109.png)

buildscript代码块会优先执行, 依赖可以定义在这个代码块中



2. 命令

   gradle build

   编译由gradle构建的项目



3. 搭建开发环境

   ![image-20200913103609217](E:\learningNotes\microServiceWeather\pic\image-20200913103609217.png)

4. hello-world

   ![image-20200913105734715](E:\learningNotes\microServiceWeather\pic\image-20200913105734715.png)



5. 导入

   ![image-20200913113828738](E:\learningNotes\microServiceWeather\pic\image-20200913113828738.png)

> @springBootApplication注解需要位于项目根目录, 代表项目采用自动扫描自动注入的方式



##### springBoot项目的运行方式

![image-20200913120311066](E:\learningNotes\microServiceWeather\pic\image-20200913120311066.png)

> 第一种方式需要先使用gradle build将项目打包, 生产jar包, 然后使用命令运行
>
> 第二种方式在gradle根目录直接运行该命令



### 天气系统入门

1. 开发环境

   ![image-20200913122055574](E:\learningNotes\microServiceWeather\pic\image-20200913122055574.png)

2. 天气数据来源

   ![image-20200913122331955](E:\learningNotes\microServiceWeather\pic\image-20200913122331955.png)

3. 在build.gradle文件中添加HttpClient 的依赖, 直接  百度 HttpClient maven , 去仓库中找

   ![image-20200913123336540](E:\learningNotes\microServiceWeather\pic\image-20200913123336540.png)

4. 打开项目, 根据api返回的json串, 创建vo领域模型

   api返回json串:

   ![image-20200913130120290](E:\learningNotes\microServiceWeather\pic\image-20200913130120290.png)

   创建相应的领域模型:

   天气模型:

   ![image-20200913125441574](E:\learningNotes\microServiceWeather\pic\image-20200913125441574.png)



昨日天气模型:

![image-20200913130539252](E:\learningNotes\microServiceWeather\pic\image-20200913130539252.png)

5. 创建核心业务
   * 创建service层并使用相应的serviceImpl实现
     * 通过城市id获取城市天气信息
     * 通过城市名称获取城市天气信息
   * 创建controller层, 将实现的两个接口暴露给外部



#### 使用redis提升应用并发访问能力

![image-20200913153111510](E:\learningNotes\microServiceWeather\pic\image-20200913153111510.png)

> 及时相应
>
> 减少第三方api的调用

开发环境

![image-20200913153311788](E:\learningNotes\microServiceWeather\pic\image-20200913153311788.png)

在build.gradle文件中增加redis依赖

![image-20200913153606356](E:\learningNotes\microServiceWeather\pic\image-20200913153606356.png)





#### 实现天气数据同步

天气数据半个小时执行一次

![image-20200913161115650](E:\learningNotes\microServiceWeather\pic\image-20200913161115650.png)



开发环境

![image-20200913161207197](E:\learningNotes\microServiceWeather\pic\image-20200913161207197.png)



![image-20200913161222119](E:\learningNotes\microServiceWeather\pic\image-20200913161222119.png)



在build.gradle文件中增加 quartz的依赖





根据城市xml文件格式定义领域模型city



### 集成天气系统界面(使用模板引擎)

![image-20200917184539894](E:\learningNotes\microServiceWeather\pic\image-20200917184539894.png)

* 开发环境

  ![image-20200917184612627](E:\learningNotes\microServiceWeather\pic\image-20200917184612627.png)

  ![image-20200917184623146](E:\learningNotes\microServiceWeather\pic\image-20200917184623146.png)

![image-20200917184710885](E:\learningNotes\microServiceWeather\pic\image-20200917184710885.png)



* 需求
  1. 按照不同的城市查询
  2. 查询近几天的天气信息
  3. 界面简洁优雅



* 步骤
  1. 在build.gradle 文件中集成thymeleaf包
  2. 编写HTML页面



### 单块系统如何净化为微服务架构

1. 单块架构

   ![image-20200917204115323](E:\learningNotes\microServiceWeather\pic\image-20200917204115323.png)

优点:

* 功能划分清楚
* 层次关系良好
* 每一层独立
* 部署简单
* 技术单一

缺点:

1. 功能太大
2. 升级风险高
3. 维护成本增加
4. 交付周期长
5. 可伸缩性差
6. 监控困难



#### 如果将单块架构转换为微服务架构

> 平台无关
>
> 接口中立

![image-20200917204933397](E:\learningNotes\microServiceWeather\pic\image-20200917204933397.png)

#### 微服务架构设计原则

什么是微服务?

每个服务运行在独立的进程中, 每个服务都可以有不同的技术栈

* 拆分足够微小

* 轻量级通信

  * 异步-消息中间件
  * 同步-rest

* 领域驱动原则

  服务要能反映出业务的领域模型

  天气系统服务领域:

  1. 天气采集  

  2. 天气同步
  3. 城市数据



**设计原则**

* 拆分最够微小

* 单一职责原则

* DevOps

  开发, 运维

* 不限于技术栈

  微服务不限于技术栈



**如何设计微服务系统**

* 服务拆分

* 服务注册中心

* 服务发现

* 服务消费

* 统一入口

* 配置管理

* **熔断机制**

  当请求太多, 挡住请求, 禁止访问

* 自动扩展



#### 服务拆分的易用

* 易于实现

* 易于维护

* 易于部署

  在服务中内嵌Tomcat等

* 易于更新

  每个服务都相互隔离, 更新互不影响

![image-20200917210713002](E:\learningNotes\microServiceWeather\pic\image-20200917210713002.png)



#### 拆分方法

1. 横向拆分

   ![image-20200917210802535](E:\learningNotes\microServiceWeather\pic\image-20200917210802535.png)

2. 纵向拆分

   ![image-20200917210829319](E:\learningNotes\microServiceWeather\pic\image-20200917210829319.png)

3. 使用DDD **领域驱动模型**

   ![image-20200917210906413](E:\learningNotes\microServiceWeather\pic\image-20200917210906413.png)



### 将天气预报系统改造为微服务系统

**改造目标:**

![image-20200918193634267](E:\learningNotes\microServiceWeather\pic\image-20200918193634267.png)



**天气预报系统的微服务:**

![image-20200918193707073](E:\learningNotes\microServiceWeather\pic\image-20200918193707073.png)

![image-20200918193730368](E:\learningNotes\microServiceWeather\pic\image-20200918193730368.png)



**系统数据流向:**

 ![image-20200918193750321](E:\learningNotes\microServiceWeather\pic\image-20200918193750321.png)



**系统通信设计:**

* 第三方天气接口

  ![image-20200918193930038](E:\learningNotes\microServiceWeather\pic\image-20200918193930038.png)

* 天气数据接口

  ![image-20200918194013161](E:\learningNotes\microServiceWeather\pic\image-20200918194013161.png)

* 天气预报接口

  ![image-20200918194049703](E:\learningNotes\microServiceWeather\pic\image-20200918194049703.png)



**系统的存储设计:**

