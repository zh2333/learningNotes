## Docker概述

> 用镜像生成容器, 容器相当于镜像的实例化, 同一个镜像可以进行多次实例化, 且每次实例化得到的容器id是不一样的

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

[官方文档](https://docs.docker.com/engine/install/centos/)

```sh
# 卸载旧版本
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```



![image-20200906135235262](E:\learningNotes\Docker\pic\image-20200906135235262.png)

```sh
# 判断docker是否启动成功
docker version
```





#### 运行

![image-20201107195812954](E:\learningNotes\Docker\pic\image-20201107195812954.png)

**docker hello-world的运行流程**

![image-20200906135900728](E:\learningNotes\Docker\pic\image-20200906135900728.png)docker 



#### 卸载docker

```shell
sudo yum remove docker-ce docker-ce-cli containerd.io
sudo rm -rf /var/lib/docker
```



#### docker底层原理

Docker是怎么工作的?

Docker是一个client-server结构的系统, DOcker的守护进行运行在主机上, 通过接受docker-client的命令运行

![image-20201107200723962](E:\learningNotes\Docker\pic\image-20201107200723962.png)

Docker为什么比虚拟机快?

1. Docker有着比虚拟机更少的抽象层

2. docker利用的是宿主机的内核, VM需要的是Guest OS

   ![image-20200906140323391](E:\learningNotes\Docker\pic\image-20200906140323391.png)

新建一个容器的时候, docker不需要向虚拟机意向重新加载一个操作系统内核

#### Docker的常用命令, 避免引导

1. 帮助命令

   ```shell
   docker version
   docker info
   docker 命令 --help
   ```



##### 镜像命令

```shell
docker images
docker search
docker pull
```

**docker images**

![image-20201107201619258](E:\learningNotes\Docker\pic\image-20201107201619258.png)



**docker search**

![image-20201107201937282](E:\learningNotes\Docker\pic\image-20201107201937282.png)



**docker pull**

![image-20200906141809589](E:\learningNotes\Docker\pic\image-20200906141809589.png)

docker使用**联合文件系统**, **分层下载**

**docker rmi 删除镜像**

![image-20201107202549900](E:\learningNotes\Docker\pic\image-20201107202549900.png)

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
	-p ip:主机端口:容器端口
	-p 容器端口
	-p 容器端口

docker ps  列出正在运行的容器
```

使用docker运行centos

![image-20200906142450146](E:\learningNotes\Docker\pic\image-20200906142450146.png)

直接进入容器, 可以使用ls查看运行容器的centos

#### 列出所有在运行的容器

![image-20201107203618195](E:\learningNotes\Docker\pic\image-20201107203618195.png)

#### 退出容器

![image-20201107203708914](E:\learningNotes\Docker\pic\image-20201107203708914.png)

#### 删除容器

![image-20201107204712070](E:\learningNotes\Docker\pic\image-20201107204712070.png)

#### 启动和停止容器的操作

![image-20201107204830906](E:\learningNotes\Docker\pic\image-20201107204830906.png)

##### docker run 和 docker start的区别

> * docker run 后面指定的是一个镜像. docker run只有在第一次运行时使用，将镜像放到容器中，以后再次启动这个容器的时候，只需要使用命令docker start就可以。docker run相当于执行了两步操作：将镜像（Image）放到容器（Container）中，这一步过程叫做docker create，然后将容器启动，使之变成运行时容器（docker start）。
>
> * docker start指定的是一个容器. 重新启动已经存在的容器。也就是说，如果使用这个命令，我们必须先要知道这个容器的ID、或者这个容器的名字，我们可以使用docker ps命令找到这个容器的信息
>
> docker run是利用镜像生成容器，并启动容器，而docker start是启动一个之前生成过的容器



#### 后台启动docker

![image-20201108122203586](E:\learningNotes\Docker\pic\image-20201108122203586.png)

#### 查看日志

![image-20201108123516392](E:\learningNotes\Docker\pic\image-20201108123516392.png)

#### 进入正在运行的容器

docker exec -it <id> /bin/bash  # 以交互方式进入, 进入容器后开启一个姓的终端, 可以在里面操作

docker attach # 进入容器正在执行/打印的终端

#### 查看容器中的进程信息

docker top <id>

#### 查看镜像中的元数据

docker inspect  <id>

```sh
[
    {
        "Id": "0a96d1820ae12393e5f7fbd177ee3b0efac38604425dd69ce8f465ba097fd1ac",
        "Created": "2020-11-08T04:24:38.844084673Z",
        "Path": "/bin/bash",
        "Args": [],
        "State": {
            "Status": "running",
            "Running": true,
            "Paused": false,
            "Restarting": false,
            "OOMKilled": false,
            "Dead": false,
            "Pid": 29305,
            "ExitCode": 0,
            "Error": "",
            "StartedAt": "2020-11-08T04:47:06.71759357Z",
            "FinishedAt": "2020-11-08T04:40:36.060010933Z"
        },
        "Image": "sha256:0d120b6ccaa8c5e149176798b3501d4dd1885f961922497cd0abef155c869566",
        "ResolvConfPath": "/var/lib/docker/containers/0a96d1820ae12393e5f7fbd177ee3b0efac38604425dd69ce8f465ba097fd1ac/resolv.conf",
        "HostnamePath": "/var/lib/docker/containers/0a96d1820ae12393e5f7fbd177ee3b0efac38604425dd69ce8f465ba097fd1ac/hostname",
        "HostsPath": "/var/lib/docker/containers/0a96d1820ae12393e5f7fbd177ee3b0efac38604425dd69ce8f465ba097fd1ac/hosts",
        "LogPath": "/var/lib/docker/containers/0a96d1820ae12393e5f7fbd177ee3b0efac38604425dd69ce8f465ba097fd1ac/0a96d1820ae12393e5f7fbd177ee3b0efac38604425dd69ce8f465ba097fd1ac-json.log",
        "Name": "/angry_feynman",
        "RestartCount": 0,
        "Driver": "overlay2",
        "Platform": "linux",
        "MountLabel": "",
        "ProcessLabel": "",
        "AppArmorProfile": "",
        "ExecIDs": null,
        "HostConfig": {
            "Binds": null,
            "ContainerIDFile": "",
            "LogConfig": {
                "Type": "json-file",
                "Config": {}
            },
            "NetworkMode": "default",
            "PortBindings": {},
            "RestartPolicy": {
                "Name": "no",
                "MaximumRetryCount": 0
            },
            "AutoRemove": false,
            "VolumeDriver": "",
            "VolumesFrom": null,
            "CapAdd": null,
            "CapDrop": null,
            "Capabilities": null,
            "Dns": [],
            "DnsOptions": [],
            "DnsSearch": [],
            "ExtraHosts": null,
            "GroupAdd": null,
            "IpcMode": "private",
            "Cgroup": "",
            "Links": null,
            "OomScoreAdj": 0,
            "PidMode": "",
            "Privileged": false,
            "PublishAllPorts": false,
            "ReadonlyRootfs": false,
            "SecurityOpt": null,
            "UTSMode": "",
            "UsernsMode": "",
            "ShmSize": 67108864,
            "Runtime": "runc",
            "ConsoleSize": [
                0,
                0
            ],
            "Isolation": "",
            "CpuShares": 0,
            "Memory": 0,
            "NanoCpus": 0,
            "CgroupParent": "",
            "BlkioWeight": 0,
            "BlkioWeightDevice": [],
            "BlkioDeviceReadBps": null,
            "BlkioDeviceWriteBps": null,
            "BlkioDeviceReadIOps": null,
            "BlkioDeviceWriteIOps": null,
            "CpuPeriod": 0,
            "CpuQuota": 0,
            "CpuRealtimePeriod": 0,
            "CpuRealtimeRuntime": 0,
            "CpusetCpus": "",
            "CpusetMems": "",
            "Devices": [],
            "DeviceCgroupRules": null,
            "DeviceRequests": null,
            "KernelMemory": 0,
            "KernelMemoryTCP": 0,
            "MemoryReservation": 0,
            "MemorySwap": 0,
            "MemorySwappiness": null,
            "OomKillDisable": false,
            "PidsLimit": null,
            "Ulimits": null,
            "CpuCount": 0,
            "CpuPercent": 0,
            "IOMaximumIOps": 0,
            "IOMaximumBandwidth": 0,
            "MaskedPaths": [
                "/proc/asound",
                "/proc/acpi",
                "/proc/kcore",
                "/proc/keys",
                "/proc/latency_stats",
                "/proc/timer_list",
                "/proc/timer_stats",
                "/proc/sched_debug",
                "/proc/scsi",
                "/sys/firmware"
            ],
            "ReadonlyPaths": [
                "/proc/bus",
                "/proc/fs",
                "/proc/irq",
                "/proc/sys",
                "/proc/sysrq-trigger"
            ]
        },
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/533db5786ca1fff862572309b98e9ee1eee37a3534b78918681ae67c5a76f5a3-init/diff:/var/lib/docker/overlay2/7e263cd80890a1a43bc9e6e76ebda308a4f7c36fd9c2622fcf9f39d4d7a0c967/diff",
                "MergedDir": "/var/lib/docker/overlay2/533db5786ca1fff862572309b98e9ee1eee37a3534b78918681ae67c5a76f5a3/merged",
                "UpperDir": "/var/lib/docker/overlay2/533db5786ca1fff862572309b98e9ee1eee37a3534b78918681ae67c5a76f5a3/diff",
                "WorkDir": "/var/lib/docker/overlay2/533db5786ca1fff862572309b98e9ee1eee37a3534b78918681ae67c5a76f5a3/work"
            },
            "Name": "overlay2"
        },
        "Mounts": [],
        "Config": {
            "Hostname": "0a96d1820ae1",
            "Domainname": "",
            "User": "",
            "AttachStdin": true,
            "AttachStdout": true,
            "AttachStderr": true,
            "Tty": true,
            "OpenStdin": true,
            "StdinOnce": true,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
            ],
            "Cmd": [
                "/bin/bash"
            ],
            "Image": "centos",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": {
                "org.label-schema.build-date": "20200809",
                "org.label-schema.license": "GPLv2",
                "org.label-schema.name": "CentOS Base Image",
                "org.label-schema.schema-version": "1.0",
                "org.label-schema.vendor": "CentOS"
            }
        },
        "NetworkSettings": {
            "Bridge": "",
            "SandboxID": "082f14621046c284a9afbc109fd7fb1d6bee17cafdd7ae343a4cfbfdcbd72079",
            "HairpinMode": false,
            "LinkLocalIPv6Address": "",
            "LinkLocalIPv6PrefixLen": 0,
            "Ports": {},
            "SandboxKey": "/var/run/docker/netns/082f14621046",
            "SecondaryIPAddresses": null,
            "SecondaryIPv6Addresses": null,
            "EndpointID": "efcf2fdb63621652d4ffb82746098a686827035e27f9cd202f292d8375fdd3bb",
            "Gateway": "172.18.0.1",
            "GlobalIPv6Address": "",
            "GlobalIPv6PrefixLen": 0,
            "IPAddress": "172.18.0.2",
            "IPPrefixLen": 16,
            "IPv6Gateway": "",
            "MacAddress": "02:42:ac:12:00:02",
            "Networks": {
                "bridge": {
                    "IPAMConfig": null,
                    "Links": null,
                    "Aliases": null,
                    "NetworkID": "324a3d9b32423f15671e0720b4432b6f2be6ab96fe515d08935b39ac9096a183",
                    "EndpointID": "efcf2fdb63621652d4ffb82746098a686827035e27f9cd202f292d8375fdd3bb",
                    "Gateway": "172.18.0.1",
                    "IPAddress": "172.18.0.2",
                    "IPPrefixLen": 16,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "02:42:ac:12:00:02",
                    "DriverOpts": null
                }
            }
        }
    }
]
```

#### 从容器拷贝文件到主机上

![image-20201108125637147](E:\learningNotes\Docker\pic\image-20201108125637147.png)





### 练习

> docker安装Nginx

```sh
# 1.搜索镜像
docker search nginx
# 2.下载镜像
docker pull nginx
# 3.运行
docker run -d --name nginx01 -p 3304:80 nginx # 将nginx监听的80端口映射到外部主机的3304端口
# 测试
curl localhost:3304
[root@VM-0-10-centos home]# curl localhost:3304
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
    body {
        width: 35em;
        margin: 0 auto;
        font-family: Tahoma, Verdana, Arial, sans-serif;
    }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>
```

> 安装Tomcat

![image-20201108133020187](E:\learningNotes\Docker\pic\image-20201108133020187.png)

> 安装es

es十分耗内存, 需要在启动的时候加上内存限制

![image-20201108133847360](E:\learningNotes\Docker\pic\image-20201108133847360.png)



### 可视化

portanier



### Docker 镜像

#### 镜像是什么

镜像是一种轻量级, 可执行的独立软件包, 用来打吧软件运行环境和基于运行环境开发的软件, 他包含运行某个软件所需要的所有内容, 包括代码, 运行时库, 环境变量和配置文件

所有的应用, 直接打包docker镜像就可以直接跑起来

##### 如何获得镜像

* 从远程仓库下载
* 朋友拷贝给你
* 自己制作一个镜像dockerFile



#### docker镜像加载原理(UFS)

UFS, 联合文件系统: 是一种分层的, 轻量级的并且高性能的文件系统, 他支持对文件系统的修改作为一次提交来一层一层的叠加, 同时可以将不同目录挂在到同一个文件系统下.Union文件系统是Docker镜像的基础. 镜像可以通过分层来进行继承, 基于基础镜像, 可以制作各种具体的应用镜像

特性: 一次同事加载多个文件系统, 但是从外面看起来只能看到一个文件系统, 联合加载会把各层文件系统叠加起来, 这样最终的文件系统会包含所有底层的文件和目录



#### docker镜像加载原理

![image-20201108145231941](E:\learningNotes\Docker\pic\image-20201108145231941.png)

![image-20201108145615385](E:\learningNotes\Docker\pic\image-20201108145615385.png)



#### 提交一个自己的镜像

```sh
docker commit -m="提交的描述" -a="作者" 容器id  目标镜像名:[TAG]
```

> 测试  自己生成一个镜像, 在Tomcat的webAPPs目录下加入官方默认的应用

```sh
# 1. 以交互方式启动Tomcat, 并将容器的8080 端口映射到外部主机的8080 端口
docker run -it -p 8080:8080 tomcat 
# 2. 将官方默认的app拷贝到webapps 目录下
cd /usr/local/tomcat
cp -r webapps.dist/* webapps
# 3.将正在运行的, 已经增加文件的容器提交
docker commit -a="zhtty" -m="add webapps" <容器id> tomcat02:1.0
# 4.使用docker image查看 
发现新增了一个镜像, 以后使用修改过的镜像就好了
```

![image-20201108152121389](E:\learningNotes\Docker\pic\image-20201108152121389.png)

> 这就像是虚拟机的快照



### Docker数据卷

#### 什么是容器数据卷

##### docker理念回顾

将应用和环境打包成一个镜像.如果数据都在容器中, 那么我们将容器杀出, 数据就会丢失 : 需求 数据可以持久化

mysql, 容器删了, 数据也就丢失了.

因此容器之间需要有一个数据共享的技术, docker中产生的数据, 同步到本地

这就是数据卷技术. 本质上是目录的挂在, 将我们容器内的目录挂在到Linux上

![image-20201108153613714](E:\learningNotes\Docker\pic\image-20201108153613714.png)

##### 使用数据卷

> 方式1: 直接使用命令来挂载 -v

```sh
docker run -it -v 主机目录: 容器内目录

#测试
docker run -it -v /home/ceshi:/home centos /bin/bash

# 查看挂载是否成功
docker inspect <容器id>
```

![image-20201108155050996](E:\learningNotes\Docker\pic\image-20201108155050996.png)

> 好处: 我们以后要是想要修改nginx或者Tomcat或者redis等等配置, 只需要将每个工具在容器中的目录挂载到外部主机的相应目录即可, 我们仅需要在外部主机修改即可, 不需要进入内部主机



##### 实战

安装mysql

```sh
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

docker run -d -p 3310:3306 -v /home/mysql/conf:/ect/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e  MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql:5.7
```



![image-20201108161647381](E:\learningNotes\Docker\pic\image-20201108161647381.png)

在本地主机连接上腾讯云服务器上的mysql容器, 并创建一个数据库

![image-20201108163418211](E:\learningNotes\Docker\pic\image-20201108163418211.png)

腾讯云外部主机的相应目录也有数据

![image-20201108163524654](E:\learningNotes\Docker\pic\image-20201108163524654.png)



##### 具名和匿名挂载

![image-20201108163858664](E:\learningNotes\Docker\pic\image-20201108163858664.png)

所有docker容器内的卷, 没有指定目录的情况下都是在 `/var/lib/docker/volumes/卷名/_data`

我们通过具名挂载可以方便的找到我们挂载的卷, 大多数情况都是用的具名挂载

```sh
# 如何区别是具名挂载还是匿名挂载, 还是指定路径挂载
-v 容器内路径 # 匿名挂载
-v 卷名:容器路路径 # 具名挂载
-v /宿主机路径:容器内路径 # 指定路径挂载
```

##### 初识DockerFile

使用简单的dockerFIle创建一个自己的镜像, 是的镜像在启动时自动挂载目录

```sh
FROM centos # 基础镜像

VOLUME ["volume1", "volume2"]  # 匿名挂载的容器内目录

CMD echo "----end----"  # 打印一下

CMD /bin/bash # 镜像启动完成后自动计入容器的终端
```

```sh
docker build -f dockerfile1 -t zhtty/centos .  # 使用指定的dockerFile生成目标镜像
```

![image-20201108170349726](E:\learningNotes\Docker\pic\image-20201108170349726.png)



![image-20201108170821616](E:\learningNotes\Docker\pic\image-20201108170821616.png)



##### 数据卷容器

在启动容器时指定`--volumes-from`参数, 这样就可以实现容器之间的数据共享

![image-20201108171731622](E:\learningNotes\Docker\pic\image-20201108171731622.png)

> 结论: 容器之间配置信息的传递, 数据卷容器的生命周期一直持续到没有容器使用为止. 但是一旦你持久化到了本地, 这个时候, 本地的数据时不会删除的

### DockFile

docker File就是用来构建docker镜像的构建文件, 命令脚本. 通过这个脚本可以生成镜像, 镜像是一层一层的, 脚本是一个一个命令

构建步骤:

1. 编写一个dockfile文件
2. docker build 构建为一个镜像
3. docker run 运行镜像
4. dockerpush 发布镜像(DockerHub, 阿里云镜像)

##### 基础知识

1. 每个保留关键字都必须是大写字母
2. 执行从上到下执行
3. #表示注释
4. 每个指令都会创建一个新的镜像层并提交

![image-20201108173016395](E:\learningNotes\Docker\pic\image-20201108173016395.png)

dockerfile是面向开发的, 我们以后要发布项目, 做镜像, 就需要编写dockfile文件

![image-20201108173218113](E:\learningNotes\Docker\pic\image-20201108173218113.png)



##### 基础命令

```sh
FROM   # 基础镜像
MAINTAINER   # 维护者信息
RUN    # 镜像构建的时候需要运行的命令
ADD   # 步骤: Tomcat 镜像, 这个Tomcat的压缩包
WORKDIR    # 镜像的巩固走目录
VOLUME     # 挂载目录
EXPOSE     # 保留端口配置
CMD        # 指定这个容器在启动的时候需要运行的命令  替换
ENTRYPOINT # 指定这个容器在启动的时候需要运行的命令  追加
ONBUILD    # 当构建一个被集成DockerFile这个时候就会运行ONBUILD的指令. 触发指令
COPY       # 将文件拷贝到文件中
ENV        # 构建是设置环境变量
```

##### 实战测试

> 创建一个自己的centos

```sh
FROM centos

MAINTAINER zhtty<zhtty@tencent.com>

ENV MYPATH /usr/local

WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80 #服务暴露都在80端口

CMD echo $MYPATH
CMD echo "----end----"
CMD /bin/bash
```

**构建镜像**

```sh
[root@VM-0-10-centos dockerfile]# docker build -f mydockerfile -t mycentos:0.1 .
Sending build context to Docker daemon  2.048kB
Step 1/10 : FROM centos
 ---> 0d120b6ccaa8
Step 2/10 : MAINTAINER zhtty<zhtty@tencent.com>
 ---> Running in 7c3e9c7dd096
Removing intermediate container 7c3e9c7dd096
 ---> 274475d506e7
Step 3/10 : ENV MYPATH /usr/local
 ---> Running in 5b229c371ba2
Removing intermediate container 5b229c371ba2
 ---> 5eee5afcefa7
Step 4/10 : WORKDIR $MYPATH
 ---> Running in e6c08d6941ce
Removing intermediate container e6c08d6941ce
 ---> b42200f1a2f1
Step 5/10 : RUN yum -y install vim
 ---> Running in 4ad1b959e1f4
CentOS-8 - AppStream                            2.0 MB/s | 5.8 MB     00:02
CentOS-8 - Base                                 1.9 MB/s | 2.2 MB     00:01
CentOS-8 - Extras                                14 kB/s | 8.1 kB     00:00
Dependencies resolved.
================================================================================
 Package             Arch        Version                   Repository      Size
================================================================================
Installing:
 vim-enhanced        x86_64      2:8.0.1763-13.el8         AppStream      1.4 M
Installing dependencies:
 gpm-libs            x86_64      1.20.7-15.el8             AppStream       39 k
 vim-common          x86_64      2:8.0.1763-13.el8         AppStream      6.3 M
 vim-filesystem      noarch      2:8.0.1763-13.el8         AppStream       48 k
 which               x86_64      2.21-12.el8               BaseOS          49 k

Transaction Summary
================================================================================
Install  5 Packages

Total download size: 7.8 M
Installed size: 31 M
Downloading Packages:
(1/5): gpm-libs-1.20.7-15.el8.x86_64.rpm        298 kB/s |  39 kB     00:00
(2/5): vim-filesystem-8.0.1763-13.el8.noarch.rp 552 kB/s |  48 kB     00:00
(3/5): which-2.21-12.el8.x86_64.rpm             2.1 MB/s |  49 kB     00:00
(4/5): vim-enhanced-8.0.1763-13.el8.x86_64.rpm  1.8 MB/s | 1.4 MB     00:00
(5/5): vim-common-8.0.1763-13.el8.x86_64.rpm    2.3 MB/s | 6.3 MB     00:02
--------------------------------------------------------------------------------
Total                                           1.9 MB/s | 7.8 MB     00:04
warning: /var/cache/dnf/AppStream-02e86d1c976ab532/packages/gpm-libs-1.20.7-15.el8.x86_64.rpm: Header V3 RSA/SHA256 Signature, key ID 8483c65d: NOKEY
CentOS-8 - AppStream                            1.6 MB/s | 1.6 kB     00:00    
Importing GPG key 0x8483C65D:
 Userid     : "CentOS (CentOS Official Signing Key) <security@centos.org>"
 Fingerprint: 99DB 70FA E1D7 CE22 7FB6 4882 05B5 55B3 8483 C65D
 From       : /etc/pki/rpm-gpg/RPM-GPG-KEY-centosofficial
Key imported successfully
Running transaction check
Transaction check succeeded.
Running transaction test
Transaction test succeeded.
Running transaction
  Preparing        :                                                        1/1
  Installing       : which-2.21-12.el8.x86_64                               1/5
  Installing       : vim-filesystem-2:8.0.1763-13.el8.noarch                2/5
  Installing       : vim-common-2:8.0.1763-13.el8.x86_64                    3/5
  Installing       : gpm-libs-1.20.7-15.el8.x86_64                          4/5
  Running scriptlet: gpm-libs-1.20.7-15.el8.x86_64                          4/5
  Installing       : vim-enhanced-2:8.0.1763-13.el8.x86_64                  5/5
  Running scriptlet: vim-enhanced-2:8.0.1763-13.el8.x86_64                  5/5
  Running scriptlet: vim-common-2:8.0.1763-13.el8.x86_64                    5/5
  Verifying        : gpm-libs-1.20.7-15.el8.x86_64                          1/5
  Verifying        : vim-common-2:8.0.1763-13.el8.x86_64                    2/5
  Verifying        : vim-enhanced-2:8.0.1763-13.el8.x86_64                  3/5
  Verifying        : vim-filesystem-2:8.0.1763-13.el8.noarch                4/5
  Verifying        : which-2.21-12.el8.x86_64                               5/5

Installed:
  gpm-libs-1.20.7-15.el8.x86_64         vim-common-2:8.0.1763-13.el8.x86_64
  vim-enhanced-2:8.0.1763-13.el8.x86_64 vim-filesystem-2:8.0.1763-13.el8.noarch
  which-2.21-12.el8.x86_64

Complete!
Removing intermediate container 4ad1b959e1f4
 ---> a4b6499a7786
Step 6/10 : RUN yum -y install net-tools
 ---> Running in 1ac3ad84dc0f
Last metadata expiration check: 0:00:10 ago on Sun Nov  8 09:47:38 2020.
Dependencies resolved.
================================================================================
 Package         Architecture Version                        Repository    Size
================================================================================
Installing:
 net-tools       x86_64       2.0-0.51.20160912git.el8       BaseOS       323 k

Transaction Summary
================================================================================
Install  1 Package

Total download size: 323 k
Installed size: 1.0 M
Downloading Packages:
net-tools-2.0-0.51.20160912git.el8.x86_64.rpm   5.7 MB/s | 323 kB     00:00
--------------------------------------------------------------------------------
Total                                           622 kB/s | 323 kB     00:00
Running transaction check
Transaction check succeeded.
Running transaction test
Transaction test succeeded.
Running transaction
  Preparing        :                                                        1/1
  Installing       : net-tools-2.0-0.51.20160912git.el8.x86_64              1/1
  Running scriptlet: net-tools-2.0-0.51.20160912git.el8.x86_64              1/1
  Verifying        : net-tools-2.0-0.51.20160912git.el8.x86_64              1/1

Installed:
  net-tools-2.0-0.51.20160912git.el8.x86_64

Complete!
Removing intermediate container 1ac3ad84dc0f
 ---> 89a5c916e7be
Step 7/10 : EXPOSE 80
 ---> Running in 059aeec59ad2
Removing intermediate container 059aeec59ad2
 ---> e9582d111da6
Step 8/10 : CMD echo $MYPATH
 ---> Running in 3887c903e3a4
Removing intermediate container 3887c903e3a4
 ---> 54faeb41b099
Step 9/10 : CMD echo "----end----"
 ---> Running in 6c31eba88c7e
Removing intermediate container 6c31eba88c7e
 ---> 4727402b9106
Step 10/10 : CMD /bin/bash
 ---> Running in 76bc13f39822
Removing intermediate container 76bc13f39822
 ---> c34f70980373
Successfully built c34f70980373
Successfully tagged mycentos:0.1
```

**测试运行**

![image-20201108175032147](E:\learningNotes\Docker\pic\image-20201108175032147.png)

vim /  ifconfig都可以使用了

**查看mycentos的构建历史**

![image-20201108175417374](E:\learningNotes\Docker\pic\image-20201108175417374.png)

##### 实战 - 安装tomcat

**touch readme.txt**

**touch Dockerfile**

```sh
FROM centos

MAINTAINER zhtty<zhtty@tencent.com>

COPY readme.txt /usr/local/readme.txt

ADD jdk-8u11-linux-x64.tar.gz /usr/local
ADD apache-tomcat-9.0.39.tar.gz /usr/local

RUN yum -y install vim

ENV MYPATH /usr/local
WORKDIR $MYPATH


ENV JAVA_HOME /usr/local/jdk1.8.0_11
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.39
ENV CATALINA_BASE /usr/local/apache-tomcat-9.0.39
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin

EXPOSE 8080

CMD /usr/local/apache-tomcat-9.0.39/bin/startup.sh && tail -F /usr/localapache-tomcat-9.0.39/bin/logs/catalina.out
```

**构建镜像**

```sh
 docker build -t mytomcat .
```

**启动**

```sh
docker run -d -p 9090:8080 --name zhttytomcat -v /home/zhtty/build/tomcat/test:/usr/local/apache-tomcat-9.0.39/webapps/test -v /home/zhtty/build/tomcat/tomcatlogs:/usr/local/apache-tomcat-9.0.39/logs mytomcat
```

**发布项目**

直接在宿主机的挂在目录发布就可以了, 不需要进入容器

##### 小结

![image-20201108185008502](E:\learningNotes\Docker\pic\image-20201108185008502.png)



### Docker网络

![image-20201108185305819](E:\learningNotes\Docker\pic\image-20201108185305819.png)

> 容器和容器之间是可以ping通的.  宿主机也可以ping通容器

容器和容器之间的同喜是通过docker0

![image-20201108190249200](E:\learningNotes\Docker\pic\image-20201108190249200.png)

> veth-pair技术

![image-20201108190508440](E:\learningNotes\Docker\pic\image-20201108190508440.png)



#### 容器互联--link(容器通信名字服务)

> 场景: 我们编写了一个微服务, database url = ip, 项目部重启, 数据库IP换掉了, 我们希望可以处理这个问题你, 可以用名字来访问容器吗?

![image-20201108191010883](E:\learningNotes\Docker\pic\image-20201108191010883.png)

就是增加了一行hosts文件的配置

![image-20201108191615038](E:\learningNotes\Docker\pic\image-20201108191615038.png)

> 现在已经不是用--link了, 需要双向配置hosts



#### 自定义网络

> 查看所有的docker 网络

![image-20201108191806504](C:\Users\zhttyzhang\AppData\Roaming\Typora\typora-user-images\image-20201108191806504.png)

> 创建一个自己的网络

![image-20201108193438062](E:\learningNotes\Docker\pic\image-20201108193438062.png)

自定义的网络可以直接ping名字

![image-20201108193717415](E:\learningNotes\Docker\pic\image-20201108193717415.png)

![image-20201108193744646](E:\learningNotes\Docker\pic\image-20201108193744646.png)

> 好处:
>
> 不同的集群之间使用不同的网络, 保证集群是安全和健康的

##### 一个容器两个IP

![image-20201108194311822](E:\learningNotes\Docker\pic\image-20201108194311822.png)

![image-20201108194338754](E:\learningNotes\Docker\pic\image-20201108194338754.png)

现在可以从Docker0下的 tomcat01 ping通 MyNet下的Tomcat01



### 实战

部署redis集群

![image-20201108194545612](E:\learningNotes\Docker\pic\image-20201108194545612.png)

```sh
# 1.创建网络
docker network create redis --subnet  172.38.0.0/16

# 2.通过shell脚本创建6个redis节点配置文件
for port in $(seq 1 6); \
do \
mkdir -p /mydata/redis/node-${port}/conf
touch /mydata/redis/node-${port}/conf/redis.conf
cat << EOF >/mydata/redis/node-${port}/conf/redis.conf
port 6379 
bind 0.0.0.0
cluster-enabled yes 
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 172.38.0.1${port}
cluster-announce-port 6379
cluster-announce-bus-port 16379
appendonly yes
EOF
done

# 3.启动容器
docker run -p 6371:6379 -p 16371:16379 --name redis-1 \
    -v /mydata/redis/node-1/data:/data \
    -v /mydata/redis/node-1/conf/redis.conf:/etc/redis/redis.conf \
    -d --net redis --ip 172.38.0.11 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
    
docker run -p 6372:6379 -p 16372:16379 --name redis-2 \
    -v /mydata/redis/node-2/data:/data \
    -v /mydata/redis/node-2/conf/redis.conf:/etc/redis/redis.conf \
    -d --net redis --ip 172.38.0.12 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
    
docker run -p 6373:6379 -p 16373:16379 --name redis-3 \
-v /mydata/redis/node-3/data:/data \
-v /mydata/redis/node-3/conf/redis.conf:/etc/redis/redis.conf \
-d --net redis --ip 172.38.0.13 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf

docker run -p 6374:6379 -p 16374:16379 --name redis-4 \
-v /mydata/redis/node-4/data:/data \
-v /mydata/redis/node-4/conf/redis.conf:/etc/redis/redis.conf \
-d --net redis --ip 172.38.0.14 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf

docker run -p 6375:6379 -p 16375:16379 --name redis-5 \
-v /mydata/redis/node-5/data:/data \
-v /mydata/redis/node-5/conf/redis.conf:/etc/redis/redis.conf \
-d --net redis --ip 172.38.0.15 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf

docker run -p 6376:6379 -p 16376:16379 --name redis-6 \
-v /mydata/redis/node-6/data:/data \
-v /mydata/redis/node-6/conf/redis.conf:/etc/redis/redis.conf \
-d --net redis --ip 172.38.0.16 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf

# 4.创建集群
redis-cli --cluster create 172.38.0.11:6379 172.38.0.12:6379 172.38.0.13:6379 172.38.0.14:6379 172.38.0.15:6379 172.38.0.16:6379

>>> Performing hash slots allocation on 6 nodes...
Master[0] -> Slots 0 - 2730
Master[1] -> Slots 2731 - 5460
Master[2] -> Slots 5461 - 8191
Master[3] -> Slots 8192 - 10922
Master[4] -> Slots 10923 - 13652
Master[5] -> Slots 13653 - 16383
M: 858bd785b030554e3b75bf85556b6f5b08988a52 172.38.0.11:6379
   slots:[0-2730] (2731 slots) master
M: 8cd95a369dcfd06b8db3ca3e4e23a9b025353cca 172.38.0.12:6379
   slots:[2731-5460] (2730 slots) master
M: cfc5b5c1ec18ee20ea0ce75fd4148e2a1ac92571 172.38.0.13:6379
   slots:[5461-8191] (2731 slots) master
M: ad0cbc98d943ab36b02b9b602e057ce9c2ba572e 172.38.0.14:6379
   slots:[8192-10922] (2731 slots) master
M: baa57e32e6cecc2051b4a9fd527aedad5c1566bc 172.38.0.15:6379
   slots:[10923-13652] (2730 slots) master
M: ba138d1e0e88c16ad16825917af47c5b28fe1032 172.38.0.16:6379
   slots:[13653-16383] (2731 slots) master
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
...
```

### 打包微服务项目到docker

![image-20201108201132568](E:\learningNotes\Docker\pic\image-20201108201132568.png)

将Dockerfile和jar文件放到同一个目录, 后执行 `docker build -t testDocker`, 接着直接运行即可

1. 构建springboot项目
2. 大伯啊应用
3. 编写dockfile
4. 构建镜像
5. 发布运行