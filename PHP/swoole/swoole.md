# swoole



## 一.概述

![image-20200808135716294](C:\Users\zhttyzhang\AppData\Roaming\Typora\typora-user-images\image-20200808135716294.png)

 

### 1.什么是swoole

- swoole是PHP的异步，并行， 高性能网络通信引擎， 提供了PHP语言的异步多线程服务器

- 异步TCP/UDP网络客户端，异步MySQL， 异步Redis数据库连接池， AsyncTask, 消息队列， 毫秒定时器， 异步文件读写， 异步DNS查询
- swoole内置了Http/websocket服务器端/客户端， http2.0服务器端

### 2.swoole提供的功能库

- http服务， 编写一个简单的web server
- tcp、udp服务， 编写一个消息接收处理系统
- 异步， 可以异步的处理请求
- 并发， 可以并打的处理同一个业务逻辑
- 协程， 相比线程更稳定和好用
- socket通讯处理技术
- 毫秒级定时器， 可以在PHP中使用定时器了

### 3.swoole的应用场景

![image-20200808140837526](C:\Users\zhttyzhang\AppData\Roaming\Typora\typora-user-images\image-20200808140837526.png)

游戏， 直播....

### 4.swoole安装

![image-20200808141103807](C:\Users\zhttyzhang\AppData\Roaming\Typora\typora-user-images\image-20200808141103807.png)



### 二.TCP服务端

创建server的步骤：

- 构建Server对象

  ```php
  //创建Server对象，监听 127.0.0.1:9501 端口
  $serv = new Swoole\Server('127.0.0.1', 9501, SWOOLE_BASE, SWOOLE_SOCK_TCP);//第三个参数是运行模式, 分为SWOOLE_BASE和SWOOLE_PROCESS
  ```

- 设置运行时参数

  ```php
  $serv->set(array(
      'worker_num' => 4, //
      'daemonize' => true, //守护进程， 设置在后台运行
      'backlog' => 128, //
  ));
  ```

- 注册事件回调机制

  ```php
  $serv->on('Connect', 'my_onConnect');
  $serv->on('Receive', 'my_onReceive');
  $serv->on('Close', 'my_onClose');
  ```

  另一种写法是采用匿名函数的写法：

  ```php
  $serv->on('connect', function ($serv, $fd){
      echo "Client:Connect.\n";
  });
  $serv->on('receive', function ($serv, $fd, $reactor_id, $data) {
      $serv->send($fd, 'Swoole: '.$data);//向connection发送数据
      $serv->close($fd);
  });
  $serv->on('close', function ($serv, $fd) {
      echo "Client: Close.\n";
  });
  ```

  参数说明：

  - `$reactor_id`是来自于哪个reactor线程
  - `$fd`是`TCP`客户端连接的标识符，在`Server`实例中是唯一的，在多个进程内不会重复
  - `fd` 是一个自增数字，范围是`1 ～ 1600万`，fd超过`1600万`后会自动从`1`开始进行复用
  - `$fd`是复用的，当连接关闭后`fd`会被新进入的连接复用 (不会立即复用, 而是到达1600万以后开始寻找空闲fd复用)
  - 正在维持的TCP连接`fd`不会被复用

  绑定事件和相应的函数的对应触发关系

  如上：

  1. 连接服务器时触发my_onConnect函数
  2. 收到数据时触发my_onReceive函数
  3. 关闭连接时， 触发my_onclose函数

- 启动服务器

  ```php
  $serv->start();
  ```

  四种回调函数风格：	
  
  > ## 匿名函数
  >
  > ```
  > $server->on('Request', function ($req, $resp) use ($a, $b, $c) {
  >     echo "hello world";
  > });
  > ```
  >
  > > 可使用`use`向匿名函数传递参数
  >
  > ## 类静态方法
  >
  > ```
  > class A
  > {
  >     static function test($req, $resp)
  >     {
  >         echo "hello world";
  >     }
  > }
  > $server->on('Request', 'A::Test');
  > $server->on('Request', array('A', 'Test'));
  > ```
  >
  > > 对应的静态方法必须为`public`
  >
  > ## 函数
  >
  > ```
  > function my_onRequest($req, $resp)
  > {
  >     echo "hello world";
  > }
  > $server->on('Request', 'my_onRequest');
  > ```
  >
  > ## 对象方法
  >
  > ```
  > class A
  > {
  >     function test($req, $resp)
  >     {
  >         echo "hello world";
  >     }
  > }
  > 
  > $object = new A();
  > $server->on('Request', array($object, 'test'));
  > ```

### 二.TCP客户端

客户端不仅仅使用swoole提供的客户端， 可以使用socket， 也可以是别的语言实现的客户端

```php
$client = new Swoole\Client(SWOOLE_SOCK_TCP，SWOOLE_SOCK_SYNC);
if (!$client->connect('127.0.0.1', 9501, -1)) { //连接到客户端
    exit("connect failed. Error: {$client->errCode}\n");
}
$client->send("hello world\n"); //发送数据
echo $client->recv(); //打印客户端收到的来自服务器的回复数据
$client->close();
```

> **同步阻塞客户端**

函数原型：

```php
function Client->connect(string $host, int $port, float $timeout = 0.5, int $flag = 0) : bool
```

**参数：**

- `$host`是远程服务器的地址，`1.10.0`或更高版本已支持自动异步解析域名，`$host`可直接传入域名
- `$port`是远程服务器端口
- `$timeout`是网络IO的超时，包括`connect/send/recv`，单位是s，支持浮点数。默认为`0.5s`，即`500ms`
- $flag参数在`UDP`类型时表示是否启用`udp_connect` 设定此选项后将绑定`$host`与`$port`，此`UDP`将会丢弃非指定`host/port`的数据包。
- `$flag`参数在`TCP`类型，`$flag=1`表示设置为非阻塞socket，connect会立即返回。如果将`$flag`设置为1，那么在`send/recv`前必须使用`swoole_client_select`来检测是否完成了连接

**返回结果：**

- 成功返回`true`
- 失败返回`false`，请检查`errCode`属性获取失败原因



### 三.异步客户端

​	服务端不能及时返回数据内容

```php
$client = new Swoole\Client(SWOOLE_SOCK_TCP，SWOOLE_SOCK_SYNC);
```

客户端必须要先绑定事件再才能连接

```php
$client = new Swoole\Client(SWOOLE_SOCK_TCP，SWOOLE_SOCK_SYNC);

//1.绑定事件
$client -> on('connect', fucntion($client) {//连接到服务端
    
});

$client -> on('receive', fucntion($client) {//发送数据
    $client -> send("hello");
});

$client -> on('receive', fucntion($client， $data) {//收到数据
    echo "receive data: ".$data;
});

$client -> on('error', fucntion($client) {//连接出错触发的事件
    
});

$client -> on('close', fucntion($client) {//关闭连接
    
});

//2.连接到客户端
if (!$client->connect('127.0.0.1', 9501, -1)) { 
    exit("connect failed. Error: {$client->errCode}\n");
}
```



### 四.udp服务端

udp绑定的事件和tcp是不同的

服务器在收到udp数据包时会触发`onPacket`事件

```php
$server('packet', function($server, $data, $clientInfo) {
    echo "接收到客户端的消息";
    $server -> sendto($clientInfo['address'],$clientInfo['port'], 'udp data');//udp不建立连接, 因此需要通过IP地址和端口号来发送, 并且发送函数是sendto
});
```



