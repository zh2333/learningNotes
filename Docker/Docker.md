## Docker概述

### 1.docker为什么会出现

一款产品: 开发--上线.应用环境, 应用配置

环境配置十分麻烦, 每一个机器都要部署环境, 配错了就十分麻烦

隔离是Docker的核心思想, 每个环境是相互隔离的



在容器技术出现之前, 都是使用虚拟化技术

虚拟机: 很笨重

docker: 也是虚拟化技术, 但是很轻量

```
vm, linux centos原生镜像隔离, 需要开启多个虚拟机
docker: 隔离, 镜像十分小巧, 运行镜像就可以了!秒级启动
```



### 2. 概述

官网:

文档地址: docs

仓库地址: hub



### 3.docker可以做什么

![image-20200906113810957](E:\learningNotes\Docker\pic\image-20200906113810957.png)

虚拟机缺点:

1. 资源占用很多

			2. 冗余步骤很多
      			3. 启动很慢



docker:

容器化技术不是模拟的一个完整的操作系统

![image-20200906114129100](E:\learningNotes\Docker\pic\image-20200906114129100.png)

docket和虚拟机技术的不同:

1. 传统虚拟机虚拟出硬件, 运行一个完整的操作系统
2. 容器内的应用直接运行在宿主机上, 容器没有自己的内核, 也没有虚拟硬件
3. 每个容器将相互隔离, 每个容器都有一个属于自己的文件系统, 互不影响



![image-20200906114733871](E:\learningNotes\Docker\pic\image-20200906114733871.png)



#### Docker的基本组成

![image-20200906134006286](E:\learningNotes\Docker\pic\image-20200906134006286.png)

**镜像image:** 镜像就像是一个模板, 可以通过这个模板来创建容器服务, Tomcat镜像 ===> run  ===> Tomcat容器, 通过这个镜像可以创建多个容器, 最终服务或者项目就是运行在容器中

**容器contanier:**Docker利用容器技术, 独立运行一个或者一组应用, 通过镜像来创建

**仓库respository**: 存放镜像的地方

> 仓库分为私有仓库和公有仓库, DockerHUb在国外



#### 安装

![image-20200906135235262](E:\learningNotes\Docker\pic\image-20200906135235262.png)



#### 运行

![image-20200906135900728](E:\learningNotes\Docker\pic\image-20200906135900728.png)



#### docker底层原理

Docker是怎么工作的?

Docker是一个client-server结构的系统, DOcker的守护进行运行在主机上, 通过接受docker-client的命令运行



Docker为什么比虚拟机快?

1. Docker有着比虚拟机更少的抽象层

   ![image-20200906140323391](E:\learningNotes\Docker\pic\image-20200906140323391.png)



#### Docker的常用命令

1. 帮助命令

   ```
   docker version
   docker info
   docker --help
   ```



##### 镜像命令

docker images

docker search

docker pull

![image-20200906141809589](E:\learningNotes\Docker\pic\image-20200906141809589.png)

docker使用联合文件系统, 分层下载

docker rmi 删除镜像

##### 容器命令

有了镜像才可以创建容器, 新建容器并启动

```shell
docker run [可选参数] image
# 参数说明
--name="Name" 容器名称
-d 后台方式运行
-it 使用交互方式运行, 进入容器查看内容
-p 指定容器端口
-P 随机指定端口

docker ps  列出正在运行的容器
```

使用docker运行centos

![image-20200906142450146](E:\learningNotes\Docker\pic\image-20200906142450146.png)

