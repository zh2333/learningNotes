### Docker Compose

docker compose轻松高效的管理容器.定义运行多个容器

#### 使用compose的步骤

1. 使用dockerFile定义应用环境

   dockerfile保证项目在任何地方都可以运行

2. docker-compose.yml

3. docker-compose up

   启动项目

> 批量容器编排

compose是docker官方的凯源玺项目. 需要安装

![image-20201114135733921](E:\learningNotes\Docker\pic\image-20201114135733921.png)

##### 下载安装

```sh
# 下载
sudo curl -L "https://get.daocloud.io/docker/compose/releases/download/1.25.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
# 为下载好的文件赋予可执行权限
chmod +x /usr/local/bin/docker-compose
```



##### get started

官网的一个Python计数器应用

```sh
# 创建工程目录
$ mkdir composetest
$ cd composetest

# 创建工程文件
import time

import redis
from flask import Flask

app = Flask(__name__)
cache = redis.Redis(host='redis', port=6379) # 两个服务子同一个网络下, 可以直接通过服务名访问

def get_hit_count():
    retries = 5
    while True:
        try:
            return cache.incr('hits')
        except redis.exceptions.ConnectionError as exc:
            if retries == 0:
                raise exc
            retries -= 1
            time.sleep(0.5)

@app.route('/')
def hello():
    count = get_hit_count()
    return 'Hello World! I have been seen {} times.\n'.format(count)
    
    
# 创建依赖文件 requirements.txt
flask
redis

# 创建Dockerfile
FROM python:3.7-alpine
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
CMD ["python", "app.py"]

# 创建docker-compose.yml文件
version: "3.8"
services: # 两个服务
  web:
    build: . # 第一种方式, build出一个镜像
    ports:
      - "5000:5000"
    depends_on:
    	- redis
  redis: 
    image: "redis:alpine"  # 第二种方式, 在仓库中拉取镜像
    
# 启动 compose
docker-compose up
```

自动的默认规则?

默认的服务名

文件名_ 服务名_ 服务编号

以前都是通过docker-run 启动容器

通过docker-compose编写yaml配置文件, 可以通过compose 一键启动所有服务或者一键停止所有服务

##### 流程

1. 创建网络

2. 执行docker-compose.yml问价

3. 启动服务

   ![image-20201114143119138](E:\learningNotes\Docker\pic\image-20201114143119138.png)



#### yaml规则

```sh
# 3层
version : '' # 版本
services: # 服务
	服务1: web
		# 服务配置
		images
		build
		network
		.....
	服务2: redis 
		# 服务配置
# 第三层  其他配置 网络/卷, 全局规则
volumes:
networks:
configs:
```



#### 使用docker-compose 搭建WordPress 博客系统

```sh
# 1.创建项目文件夹 wordpress
mkdir wordpress
# 2.编写docker-compose.ymal文件
version: '3.3'

services:
   db:
     image: mysql:5.7
     volumes:
       - db_data:/var/lib/mysql
     restart: always
     environment:# mysql配置
       MYSQL_ROOT_PASSWORD: somewordpress
       MYSQL_DATABASE: wordpress
       MYSQL_USER: wordpress
       MYSQL_PASSWORD: wordpress

   wordpress:
     depends_on:
       - db
     image: wordpress:latest
     ports:
       - "8000:80"
     restart: always
     environment:
       WORDPRESS_DB_HOST: db:3306
       WORDPRESS_DB_USER: wordpress
       WORDPRESS_DB_PASSWORD: wordpress
       WORDPRESS_DB_NAME: wordpress
volumes:
    db_data: {}
    
# 后台启动
docker-compose up -d
```



##### 实战-自己编写微服务上线

.....



### Docker Swarm(集群)

##### 工作模式

![image-20201114161020745](E:\learningNotes\Docker\pic\image-20201114161020745.png)

节点: 管理节点 ,  工作节点

> 操作都在manager节点上



##### 搭建集群

![image-20201114161346651](E:\learningNotes\Docker\pic\image-20201114161346651.png)



![image-20201114161410258](E:\learningNotes\Docker\pic\image-20201114161410258.png)

> 两个网络: 公网, 私网



##### 操作

1. 创建一个swarm集群, 并初始化一个manager节点到swarm中

   `docker swarm init --advertise-addr 172.24.82.149(服务器IP地址)`

   ```sh
   # 获取令牌
   docker swarm join-token manager   
   docker swarm join-token worker
   ```

   命令控制台输出:

   ![image-20201114162033141](E:\learningNotes\Docker\pic\image-20201114162033141.png)

   可以通过上述命令使得其他节点加入到刚刚创建的swarm集群中

2. 添加worker节点到刚刚的swarm集群中

   通过上述命令即可

3. 在刚刚创建的swarm集群的manager节点上查看 当前集群中的节点

   docker node ls 

   ![image-20201114162350269](E:\learningNotes\Docker\pic\image-20201114162350269.png)

4. 以manager角色加入到刚刚创建的swarm集群中

   先通过`docker swarm join-token manager`命令得到令牌, 再在这个机器上执行控制台输出的命令即可

   ![image-20201114162520610](E:\learningNotes\Docker\pic\image-20201114162520610.png)

5. 查看

   ![image-20201114162612493](E:\learningNotes\Docker\pic\image-20201114162612493.png)



##### Raft协议

> 保证大多数节点存活才可用

一个swarm集群中至少得有3个manager才能保证服务的可用





##### 体会

弹性, 动态扩缩容

从容器到服务

10个redis容器集群形成了一个服务



### docker service

以服务的形式启动相同容器的多个副本, 可以保证服务的高可用的同时可以保证弹性扩缩容

> 我们可以: 创建服务, 动态扩展服务, 动态更新服务

![image-20201114164518280](C:\Users\zhttyzhang\AppData\Roaming\Typora\typora-user-images\image-20201114164518280.png)

灰度发布



##### 实验-以服务的形式启动nginx

docker service create -p 8888:80 --name my-nginx nginx

![image-20201114165013319](E:\learningNotes\Docker\pic\image-20201114165013319.png)

使用这个命令的前提是你已创建了一个swarm集群并且是在manager节点上执行此命令

![image-20201114165642438](E:\learningNotes\Docker\pic\image-20201114165642438.png)

在该服务中创建三个该容器的副本

![image-20201114165800716](E:\learningNotes\Docker\pic\image-20201114165800716.png)

创建完毕后会动态的将这些容器分配到不同的节点上运行

> 服务, 集群中任意的节点都可以访问. 服务可以有多个副本动态扩缩容实现高可用

**移除服务**

`docker service rm 服务名`

**扩缩容**

![image-20201114170418044](E:\learningNotes\Docker\pic\image-20201114170418044.png)



#### 概念总结

![image-20201114170720617](E:\learningNotes\Docker\pic\image-20201114170720617.png)

![image-20201114170731609](E:\learningNotes\Docker\pic\image-20201114170731609.png)