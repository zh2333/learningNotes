## Kafka

kafka是一个分布式的基于发布订阅模式的消息队列



### 1.传统消息队列应用场景之异步处理

![image-20200823114052468](E:\learningNotes\Kafka\kafka\image-20200823114052468.png)

好处:

> 1. 解耦
>
>    允许独立的扩展或者修改两边的处理过程, 只要确保他们遵守相同的接口约束
>
> 2. 可恢复性
>
>    系统的一部分组件失效时, 不会影响到整个系统.消息队列降低了进程间的耦合度, 所以即使一个处理消息的进程挂掉, 加入队列总的消息仍然可以在系统恢复后被处理
>
> 3. 缓冲
>
>    有助于控制和优化数据流经过系统的速度, 解决生产消息和消费消息的处理速度不一样的情况
>
> 4. 灵活性 & 峰值处理能力
>
>    在访问量剧增的情况下, 应用仍然需要基础发挥作用, 但是这样的突发流量并不常见.消息队列能够使关键组件顶住突发的访问压力, 而不会因为突发的流量而崩溃.不是非得加机器

最主要就是解耦和削峰



### 2.消息队列的两种模式

* 点对点

  y一对一, 消费者主动拉取数据, 消息收到后消息清除

  消息生产者生产消息并发送到Queue找那个, 然后消息消费者从Queue中取出并消费消息. 消息被消费后;Queue中不再存有该消息, 因此不存在重复消费的情况.Queue支持存在多个消费者, 但是对于一个消息而言, 只会有一个消费者可以消费. (消费者主动拉取)

  ![image-20200823121608966](E:\learningNotes\Kafka\kafka\image-20200823121608966.png)

* 发布订阅模式

  一对多, 消费者消费数据之后不会清除消息

  消息生产者将消息发布到topic中, 同事有多个消息消费者订阅消费该消息.和点对点方式不同, 发布到topic中的消息会被所有的订阅者消费.

  ![image-20200823122140396](E:\learningNotes\Kafka\kafka\image-20200823122140396.png)

> 1. 消息队列主动向消费者推送消息
>    * 推送速度可能会超过了消费者的消费能力
> 2. 消费者主动拉取(Kafka采用的是基于发布订阅模式中的消费者主动拉取的方式)
>    * 需要消费者主动轮询消息队列



### 3.Kafka架构

集群由每一个**broker**组成, broker可以是启动了kafka进程的服务器

每一个broker中有不同的**topic**, topic用于将不同类型的消息分类

每个topic中由分不同的**partition**, 用于topic的**负载均衡**, 提高了并发. 一种topic可以位于两个broker上, 因此partition也可以位于不同的broker上

每一个partition都有**leader**和**follower**, 用于消息冗余. follower只提供消息备份的功能, 存消息和小飞数据只在kafka中进行

一个**consumer group**里面可以放多个消费者, 一个分区中的消息只能被一个组中的某一个消费者消息

> *  消费速度最好的时候是消费者组中消费者的数量和分区数相等的情况
>
> * 消费者也要存储一些消息, 防止消费消息的时候服务器突然宕机. 消费者将消费到的位置信息存在ZooKeeper, 即消费到哪了

![image-20200823124745303](E:\learningNotes\Kafka\kafka\image-20200823124745303.png)



### 4.安装配置使用

* 启动kafka集群的脚本

![image-20200823130134701](E:\learningNotes\Kafka\kafka\image-20200823130134701.png)



### 5.Kafka工作流程和文件存储机制

![image-20200823135621198](E:\learningNotes\Kafka\kafka\image-20200823135621198.png)

> 1. follower和leader在不同的机器上
>
> 2. 消费者需要保存offset, offset是每一个partition单独维护的
> 3. topic是逻辑上的概念, partition是物理上的概念, 每个partition对应于一个log文件, 该log文件中存储的就是producer生产的额数据.producer生产的数据会被不断追加在该log文件的末端, 且每条数据都有自己的额offset.消费者组中每个消费者都会实时记录自己消费到哪个offset, 以便出错时恢复, 从上次的位置继续消费



文件存储机制(**.index文件和.log文件**)

XXX.log用来存放分区里面的消息数据,

![image-20200823141655334](E:\learningNotes\Kafka\kafka\image-20200823141655334.png)

由于生产者生产的消息会不断追加到log文件末尾, 为防止log文件过大导致数据定位效率低下, Kafka采取了**分片**和**索引**机制, 将每个partition分为多个segment.每个segment对应两个文件 ----  .index文件和.log文件. 这些文件位于一个文件夹下, 该文件夹的命名规则为topic名称 + 分区序号. 

例如first这个topic有三个分区, 则其对应的文件夹为first-0, first-1, first-2 

**先通过二分查找找到自己想要的数据的索引在哪个.index文件中, 再去这个文件中找到对应消息的索引(消息在.log文件中存储的起始索引), 再加上消息的大小, 就能够完全确定一个消息在.log文件中的起始位置和结束位置.然后根据这两个位置就能迅速的取到想要的消息, 这就是为什么kafka将消息数据存储在磁盘上却依然很快的原因.**

![image-20200823142913934](E:\learningNotes\Kafka\kafka\image-20200823142913934.png)



### Kafka生产者

#### 1.分区策略

> 分区的原因:
>
> * 方便在集群中扩展, 每个partition可以通过调整以适应它所在的机器, 而一个topic是由多个partition组成, 因此整个集群可以适应任意大小的数据
> * 可以提高并发

#### 2.分区原则

我们需要将producer发送的数据疯转成一个Producerrecord对象

* 指明partition的情况下, 直接将指明的值直接作为partition的值
* 没有指明partition值但是有key的情况下, 将key的哈市值与topic的partition数进行取余得到partition的值
* 既没有partition的值也没有key值得情况下, 第一次调用时随机生成一个整数(后续在这个值的基础上递增), 将这个值与topic可用的partition总数取余得到partition的值

![image-20200823144242807](E:\learningNotes\Kafka\kafka\image-20200823144242807.png)上述就是ProducerRecord的构造方法及其重载的构造方法



#### 3.数据可靠性保证

**为了保证producer发送的数据能够可靠的发送到指定的topic上, topic的每个partition收到producer发送的数据后都要想producer发送ack, ruguo producer收到ack就会进行下一轮发送, 否则重新发送数据**

![image-20200823145103937](E:\learningNotes\Kafka\kafka\image-20200823145103937.png)



副本数据同步策略:

![image-20200823145324055](E:\learningNotes\Kafka\kafka\image-20200823145324055.png)

range比第一种更好. range按照组来划分, 第一种按照单个消费者来划分



##### ISR

采用第二种方案后, 设想一下情景: leader收到数据, 所有的额follower都开始同步数据,  但是有一个follower因为某种故障, 迟迟不能与leader进行同步, 那leader就要一直等待下去直到它完成同步, 才能发送ack, 怎么解决?

> leader维护一个动态的in-sync-set(ISR), 意为何leader保持同步的follower集合, 当ISR中的follower完成数据同步后, leader就会给follower发送ack, 如果follower长时间未向leader同步数据, 则该follower将被踢出ISR. leader发生故障后, 就会从ISR中选举出新的leader(**延迟低, 数据多的follower**)



##### ack应答机制

对于某些不太重要的数据, 对数据的可靠性要求不是很高, 能够容忍数据的少量丢失, 所以没必要等ISR中的follower全部接受成功.所以kafka为用户提供了三种可靠级别, 用户根据对可靠性和延迟的要求进行权衡

**参数: acks**

0: producer不等待broker的ack

1: producer等待broker的ack, partition的leader落盘成功后返回ack

-1: producer等待broker的ack, partition的leader和follower全部落盘成功后才返回ack(ISR中的follower).可能会造成数据重复的问题.follower同步完成后, broker发送ack之前, leader发生=故障, 那么会造成数据重复

​	![image-20200823152814488](E:\learningNotes\Kafka\kafka\image-20200823152814488.png)

##### 数据一致性问题

leader写完后, 两个follower同步数据.一个follower同步了8条, 另一个同步了9条.这时leader挂了, 从这两个follower中产生一个新的leader, 选完后, 原来的leader又上线了, leader和follower之间的数据不一致

**HW** 和 **LEO**

![image-20200823153416325](C:\Users\zhttyzhang\AppData\Roaming\Typora\typora-user-images\image-20200823153416325.png)

HW存在的情况下, 消息队列只会暴露12条数据.HW保证的是消费者消费数据的一致性

前面保证的是消费者消费数据的一致性, 那存储一致性怎么保证?

1. follower故障

   follower发生故障后悔被临时踢出ISR, 待该follower恢复后, follower会读取本地磁盘记录的上次的HW, 并将**log文件中高于HW的部分截取掉,** 从HW开始向leader进行同步, 等该follower的LEO大于等于该partition的HW, 即follower追上leader之后就可以重新加入ISR了.

2. leader发生故障

   leader发生故障后会从ISR中选择出一个新的leader, 之后, 为保证多个副本之间的数据一致性, 其余的follower**会先将各自的log文件高于HW的部分截掉**, 然后从新的leader同步数据

> 这只能保证副本之间的数据一致性, 并不能保证数据不丢失或者不重复
>
> 上述过程中提到的leader和follower都是在ISR中的



##### Exactly Once

将服务器的ACK级别设置为-1可以保证producer和server之间不会丢失数据, 即**At Least Once**. 相对的, ACK的级别设置为0, 可以保证生产者每条消息只会被发送一次, 即**At Most Once**



**At Least Once**可以保证数据不丢失, 但是不能保证数据不重复. 

**At Most Once**可以保证数据不重复, 但是不能保证数据不丢失

对于一些非常重要的信息, 比如说交易数据. 下游数据消费者要求数据既不重复也不丢失, 这就是**Exactly Once**语义

0.11版本的kafka引入了一个重大特性: **幂等性**, 解决了重复数据的问题. 所谓幂等性就是指Producer不论向Server发送多少次重复数据. Server端只会持久化一条

```text
幂等性 + At Least Once = Exactly Once
```

如何实现幂等性的呢??

> 开启幂等性的Producer在初始化的时候会被分配一个PID, 发往同一个Partition的消息会附带Sequence Number. 而Broker端会对**<PID, Partition, SequenceNumber>**做缓存, 当具有相同主键的消息提交时, Broker只会持久化一条

但是因为PID重启就会发生变化, 同时不同的Partition也具有不同主键, 所以幂等性无法保证跨分区会话的Exactly Once



### kafka消费者

##### 消费方式

consumer采取pull从broker中读取数据

pull模式的不足之处在于, 如果kafka中没有数据, 消费者可能hi陷入到循环中, 一直返回空数据. 针对这一点, Kafka的消费者在消费数据时会传入一个时长参数timeout.如果当前没有数据可供消费, consumer会过一段时间之后再来, 这段时间就是timeout



##### 分区分配策略

一个consumer group中有多个consumer, 一个topic有多个partition, 这就涉及到partition的分配问题, 确定按个partition由哪个consumer来消费

* RundRobin -- 轮询
* ![image-20200823162033731](E:\learningNotes\Kafka\kafka\image-20200823162033731.png)

轮流分配

![image-20200823162049483](E:\learningNotes\Kafka\kafka\image-20200823162049483.png)

如果要分配多个topic中的多个分区, 也是一样的.将消费者组订阅的所有的topic中的partition当做一个整体, 轮流分配

> 问题: 可能会将某个订阅的消息发错
>
> ![image-20200823162458284](E:\learningNotes\Kafka\kafka\image-20200823162458284.png)



因此用轮询的条件是: 消费者组中的消费者订阅的主题是一样的才可以

* range

  ![image-20200823162634308](E:\learningNotes\Kafka\kafka\image-20200823162634308.png)

可能会造成消费数据不对等的问题

> 使用时机: 当消费者的数量发生改变时都会触发重新分配



##### offset的维护

由于consumer在消费过程中可能会出现断电宕机等故障, consumer恢复后, 需要从故障前的位置继续消费, 所以consumer需要实时记录自己消费到了哪个offset, 以便故障后继续消费

**consumer group + topic + partition 唯一确定一个offset**

> kafka0.9版本之前, consumer默认将offset保存在zookeeper中, 从0.9版本开始, consumer默认将offsets保存在kafka的一个内置topic中, 该topic名为__consumer_offsets . 因此消费者可以从这个topic中消费offset信息. 这个topic是由消费者产生的, 该消费者产生后由改组的组里其他的消费者消费

![image-20200905134611323](E:\learningNotes\Kafka\kafka\image-20200905134611323.png)





#### kafka高效读取数据

##### 1.顺序写磁盘

kafka producer生产数据, 要写入到log文件中, 写的过程是一直追加到文件末端, 为顺序写.官网有数据表明, 同样的磁盘, 顺序写能节省大量时间

##### 2.零复制技术

![image-20200905150601191](E:\learningNotes\Kafka\kafka\image-20200905150601191.png)



#### ZooKeeper在kafka中的作用

kafka集群中有一个broker会被选举为Controller, 负责管理集群broker的上下线, 所有topic的分区副本分配和leader选举等工作

Controller的管理工作都是依赖于Zookeeper的



#### kafka事务

kafka中的事务(**0.11版本以后**)可以保证kafka在exactly Once语义的基础上, 生产和消费可以跨区和回话, 要么全部成功, 要么全部失败

##### 1.produce事务

​	为了实现跨分区会话, 需要引入一个全局唯一的TransactionId, 并将Producer获得的PID和transactionID绑定. 这样当Producer重启后就可以通过正在进行的TransactionID获得原来的PID

为了管理transaction, kafka引入了一个新的组件Transaction Coordinator.Producer就是通过和Transaction Coordinator交互获得TransactionID对应的任务的状态.Transaction Coordinator还负责将事务写入到kafka的一个内部Topic, 这样即使整个服务重启, 由于事务状态得到保存, 进行中的事务状态可以得到恢复, 从而继续进行.

> 如果不引入transactionID, nameproducer在重启后期PID会发生变化, 重启后producer会将消息重新发送一遍, 而partition通过PID来区分消息, 明明是相同的消息但是由于PID相同, 因此partition认为两次发送的是不同的消息, 因此会导致消息的重复.而引入了TransactionID后由于TransactionID是和PID绑定的, 因此PID不会发生变化, 也就不会出现消息重复了

##### 2.consumer事务

上述的事务机制主要是从producer方面考虑, 对于consumer而言, 事务的保证会相对较弱, 尤其是无法保证Commit的信息被精确消费. 这是由于COnsumer可以通过offset访问任意信息, 而且不同的segment File生命周期不同, 同一事务的消息可能会出现重启后被删除的情况

> 假设现在消费者消费数据要消费100条, 但是这100条数据跨了segment. 不同的segment的生命周期是不一样的, 假设你消费过的分区到期被删除了, 但是你的代码逻辑出错了, 按照道理应该重新再从头消费这100条数据, 但是此时, 由于之前的segment已经过期删除了, 所以此时消费者的事务性得不到保障





### kafka API

##### 消息发送流程

kafka的producer发送消息采用的是**异步发送**的方式.在消息发送的过程中, 涉及到了两个线程, **main**线程和**sender**线程, 以及一个线程共享变量----**RecordAccumulator**. 

​	main线程将消息发送给RecordAccumulator, Sender线程不断从RecordAccumulator中拉取消息发送到Kafka broker

![image-20200905160437454](E:\learningNotes\Kafka\kafka\image-20200905160437454.png)

拦截器-->序列化器-->分区器

#### 1.生产者 API



##### 2.异步发送API

