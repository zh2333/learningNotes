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

![image-20200919101050717](E:\learningNotes\microServiceWeather\pic\image-20200919101050717.png)



#### 天气数据采集微服务实现

![image-20200919101137643](E:\learningNotes\microServiceWeather\pic\image-20200919101137643.png)

从第三方接口同步天气数据到本地redis



#### 天气数据api微服务

![image-20200919105354609](E:\learningNotes\microServiceWeather\pic\image-20200919105354609.png)

将天气查询接口暴露给外部



#### 天气预报微服务

通过浏览器UI界面调用天气数据api微服务, 并将数据展示在前端

![image-20200919111549820](E:\learningNotes\microServiceWeather\pic\image-20200919111549820.png)

#### 城市数据API微服务的实现

![image-20200919114055404](E:\learningNotes\microServiceWeather\pic\image-20200919114055404.png)





### spring Cloud

用于微服务的管理

#### spring cloud简介

* 配置管理

* 服务注册

* 服务发现

* 断路器

  用于保护系统, 防止过载

* 负载均衡

* 智能路由

* 微代理

* 服务间调用



#### spring boot和spring cloud的关系

* spring boot是构建spring cloud的基石

  ![image-20200919131740677](E:\learningNotes\microServiceWeather\pic\image-20200919131740677.png)







#### spring cloud配置入门

![image-20200919131845514](E:\learningNotes\microServiceWeather\pic\image-20200919131845514.png)



![image-20200919131901177](E:\learningNotes\microServiceWeather\pic\image-20200919131901177.png)



![image-20200919131918771](E:\learningNotes\microServiceWeather\pic\image-20200919131918771.png)



#### spring cloud子项目介绍

* spring cloud config

  配置中心, 利用git来集中管理程序的配置

* spring cloud NetFix

  继承众多组件

* spring cloud bus

  消息总线, 利用分布式消息将服务和服务实例连接在一起, 用于在一个集群中传播状态的变化, 比如配置更改的事件, 可与spring cloud config联合实现热部署

* spring cloud for cloud foundry



### 微服务注册与发现

#### 如何发现服务

* 通过URL来访问服务

  问题:

  1. 需要绑定主机
  2. 难记
  3. 难以做到负载均衡

* 服务注册于发现

  通过服务名称来发现服务

  1. 服务注册

     **Eureka**

  2. 服务发现



#### 集成Eureka Server

![image-20200919133524372](E:\learningNotes\microServiceWeather\pic\image-20200919133524372.png)



![image-20200919133558534](E:\learningNotes\microServiceWeather\pic\image-20200919133558534.png)



**项目配置**

![image-20200919135452353](E:\learningNotes\microServiceWeather\pic\image-20200919135452353.png)



#### 集成Euraka Client 

![image-20200919135833454](E:\learningNotes\microServiceWeather\pic\image-20200919135833454.png)



#### 实现天气预报系统的服务注册于发现

![image-20200919141644928](E:\learningNotes\microServiceWeather\pic\image-20200919141644928.png)



### 微服务的消费

#### 微服务消费模式

* 服务直连模式

  特点

  1. 简单明了  restTemplate
  2. 平台无关性
  3. 无法保证服务的可用性
  4. 生产环境用的少

* 客户端发现模式

  1. 服务实例启动后, 将自己的位置信息提交到服务注册表
  2. 客户端从服务注册表查询, 来获取可用的服务实例
  3. 客户端自行使用负载均衡算法从多个服务器实例中选择一个

![image-20200919151427231](E:\learningNotes\microServiceWeather\pic\image-20200919151427231.png)

* 服务端发现模式

  ![image-20200919151459934](E:\learningNotes\microServiceWeather\pic\image-20200919151459934.png)

负载均衡器部署在服务端





#### 常见的微服务的消费者

* HttpClient

  ![image-20200919151613525](E:\learningNotes\microServiceWeather\pic\image-20200919151613525.png)



​	![image-20200919151633078](E:\learningNotes\microServiceWeather\pic\image-20200919151633078.png)



​	![image-20200919151643583](E:\learningNotes\microServiceWeather\pic\image-20200919151643583.png)



* Ribbon

  基于客户端的负载均衡工具

  ![image-20200919151738482](E:\learningNotes\microServiceWeather\pic\image-20200919151738482.png)



![image-20200919151753242](E:\learningNotes\microServiceWeather\pic\image-20200919151753242.png)



​	![image-20200919151804075](E:\learningNotes\microServiceWeather\pic\image-20200919151804075.png)



​	![image-20200919151829406](E:\learningNotes\microServiceWeather\pic\image-20200919151829406.png)		通过服务名称来访问

​		![image-20200919151912904](E:\learningNotes\microServiceWeather\pic\image-20200919151912904.png)



* Feign

  用于实现服务之间的相互调用, 服务间互相访问

  > feign, springcloud, spring boot的版本关系是强依赖的 
  >
  > 如果要在一个微服务中调用另一个微服务就要使用到feign, 首先要在Application 类上新增加一个注解@EnableFeignClients , 然后在service目录下新建一个接口, 并且指定要访问的服务.其次在接口中定义一个和想要访问的服务中的方法相同名称的方法, 并加上相同的@GetMapping注解.
  >
  > 例如, 我现在想要调用城市微服务中的listCity方法, 首先在service包下定义一个cityClient接口, 然后在这个接口中定义一个相同的listCity方法, 并加上注解
  >
  > ```java
  > @FeignClient("msa-weather-city-eureka")
  > @Service
  > public interface CityClient {
  > 	
  > 	@GetMapping("cities")
  > 	List<City> listCity() throws Exception;
  > 
  > }
  > ```
  >
  > 然后在你要使用该方法的地方将CityClient接口使用@Autowired注入, 并调用.这样就能实现在一个方法中调用另一个服务的方法

  ![image-20200919151939730](E:\learningNotes\microServiceWeather\pic\image-20200919151939730.png)

  ​	开发环境

  ​	![image-20200919152006419](E:\learningNotes\microServiceWeather\pic\image-20200919152006419.png)



![image-20200919162652207](E:\learningNotes\microServiceWeather\pic\image-20200919162652207.png)

​	![image-20200919162510468](E:\learningNotes\microServiceWeather\pic\image-20200919162510468.png)

 



#### 实现服务的负载均衡以及高可用

![image-20200919165708688](E:\learningNotes\microServiceWeather\pic\image-20200919165708688.png)

> 实现微服务的高可用, 可以将一个微服务多开几个实例. 当一个挂了, 其他的不影响



### API网关

统一API入口的组件, 验证访问者等作用

#### API网关意义

* 可以集合多个API.

  >  如果一个微服务要调用另一个微服务的API, 只需要将请求交给API网关即可, 由API网关来转发, 调用者无需关注被调用者长什么样子

* 统一API入口

  ![image-20200919181115047](E:\learningNotes\microServiceWeather\pic\image-20200919181115047.png)

* 避免将内部信息泄露给外部

  保证系统安全.避免未授权的api访问

* 为微服务添加额外的安全层

* 支持混合通信协议

* 有效的降低苟江微服务的复杂性

* 微服务模拟与虚拟化



* 弊端

  1. 在架构上需要额外考虑更多的编排与管理

  2. 路由配置要统一管理

  3. 可能引发单点故障

     API网关挂了, 整个系统的服务都不可用

#### 常见API网关

* NGINX

  ![image-20200919181630717](E:\learningNotes\microServiceWeather\pic\image-20200919181630717.png)

* Zuul

  ![image-20200919181702212](E:\learningNotes\microServiceWeather\pic\image-20200919181702212.png)

  ​	![image-20200919182207271](E:\learningNotes\microServiceWeather\pic\image-20200919182207271.png)

* kong

  ![image-20200919181751292](E:\learningNotes\microServiceWeather\pic\image-20200919181751292.png)





#### 集成Zuul

![image-20200919182235599](E:\learningNotes\microServiceWeather\pic\image-20200919182235599.png)

​	![image-20200919182306911](E:\learningNotes\microServiceWeather\pic\image-20200919182306911.png)

> 使用Zuul, 要在Application 类上面新增加一个注解@EnableZuulProxy  
>
> ![image-20200919183729493](E:\learningNotes\microServiceWeather\pic\image-20200919183729493.png)
>
> **红框的地方要严格和后面需要匹配的路径一致**

![image-20200919181919255](E:\learningNotes\microServiceWeather\pic\image-20200919181919255.png)



​	**配置**

![image-20200919181943150](E:\learningNotes\microServiceWeather\pic\image-20200919181943150.png)



​	**架构**

​	**![image-20200919182015130](E:\learningNotes\microServiceWeather\pic\image-20200919182015130.png)**



​	天气预报微服务的API网关路由规则:

​		![image-20200919184235757](E:\learningNotes\microServiceWeather\pic\image-20200919184235757.png)



### 微服务的集中化配置

##### 为什么要集中化配置

* 微服务数量多, 配置多
* 手工管理配置繁琐



##### 配置分类

* 按照配置来源划分

  源代码, 文件, 数据库连接, 远程调用

* 按照配置的环境划分

  开发环境, 测试环境, 预发布环境,生产环境

* 按照配置的集成阶段划分

  编译时, 打包时, 运行时

* 按照加载方式

  启动加载和动态加载



##### 配置中心的要求

![image-20200920101527608](E:\learningNotes\microServiceWeather\pic\image-20200920101527608.png)



##### 配置工具

![image-20200920101626374](E:\learningNotes\microServiceWeather\pic\image-20200920101626374.png)



#### 使用config实现的配置中心Server端

![image-20200920101733760](E:\learningNotes\microServiceWeather\pic\image-20200920101733760.png)





![image-20200920101745006](E:\learningNotes\microServiceWeather\pic\image-20200920101745006.png)



![image-20200920101752997](E:\learningNotes\microServiceWeather\pic\image-20200920101752997.png)





![image-20200920101806685](E:\learningNotes\microServiceWeather\pic\image-20200920101806685.png)



![image-20200920101822967](E:\learningNotes\microServiceWeather\pic\image-20200920101822967.png)



> 配置项目完成后可以在浏览器地址栏中输入localhost:8888/auther/dev, 查看配置信息



#### 使用Config实现的配置中心Client端

​	![image-20200920104043935](E:\learningNotes\microServiceWeather\pic\image-20200920104043935.png)





![image-20200920104054379](E:\learningNotes\microServiceWeather\pic\image-20200920104054379.png)

​	![image-20200920104104379](E:\learningNotes\microServiceWeather\pic\image-20200920104104379.png)

​	![image-20200920104127383](E:\learningNotes\microServiceWeather\pic\image-20200920104127383.png)



​	![image-20200920104142462](E:\learningNotes\microServiceWeather\pic\image-20200920104142462.png)





##### 配置中心文件的命名规则

![image-20200920105728700](E:\learningNotes\microServiceWeather\pic\image-20200920105728700.png)



![image-20200920105932633](E:\learningNotes\microServiceWeather\pic\image-20200920105932633.png)



### 熔断机制

当系统请求数量过载, 对于新到来的请求, 不再为其服务, 而是返回一个默认值, 比如一个提示, 



#### 服务熔断

* 断路器

  返回一个默认值

* 断路器模式

  ![image-20200920111848032](E:\learningNotes\microServiceWeather\pic\image-20200920111848032.png)

  当请求失败的次数超过一定的阈值, 断路器就会打开, 代理开始运作, 响应给请求放一个固定的值.当服务初步恢复时, 断路器会进入半打开模式, 允许少量的请求通过, 用于缓冲

  

  **Hystrix**

  ![image-20200920112328598](E:\learningNotes\microServiceWeather\pic\image-20200920112328598.png)

  

  ![image-20200920112347814](E:\learningNotes\microServiceWeather\pic\image-20200920112347814.png)



#### 熔断的意义

* 保持系统稳定

* 减少性能损耗

* 可以做到及时响应

  错误提示信息

* 熔断器的阈值可以定制



#### 熔断器的功能

* 异常处理

* 日志记录

* 测试失败的操作

* 手动复位

  强行关闭断路器

* 并发

* 加速短路

* 重试失败请求



#### 熔断和降级的区别

* 相似性

  1. 目的一致

     保护系统

  2. 表现形式

     让用户感知到当前服务不可用

  3. 粒度一致

     服务的级别

* 区别

  1. 触发条件不同
     * 服务熔断一般是由某个服务引起的
     * 服务降级是从系统整体的负载来考虑的
  2. 管理目标的层次不同
     * 熔断, 每个微服务都需服务熔断, 是没有层次的
     * 降级, 是从业务角度考虑, 是有层次的, 一般降级是从最外围的服务开始



#### 集成Hystrix

