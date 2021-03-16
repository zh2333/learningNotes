### 一.ConcurrentHashMap容器初始化

1. 源码分析

2. sizeCtl含义

   在构造方法中偶读涉及到一个变量sizeCtl

   > 为0， 代表数据未初始化， 且数组的初始容量为16
   >
   > 为正数， 如果数组未初始化， name记录其的是数组的初始容量， 如果数据已经初始化， 那么记录其记录的是数据的扩容阈值 
   >
   > 为1， 表示数据正在进行初始化。其他线程不能扩容
   >
   > Thread.yeild()方法
   >
   > 小于0， 并不是-1， 表示数组正在扩容， -(n+1) 表示此时有n个线程正在进行共同完成数据的扩容操作

### 二.jdk8保证添加元素安全

1. 源码分析

   * 添加元素put/putVal 方法

     ```java 
     final V putVal(K key, V value, boolean onlyIfAbsent) {
             if (key == null || value == null) throw new NullPointerException();
             int hash = spread(key.hashCode());
             int binCount = 0;
             for (Node<K,V>[] tab = table;;) {
                 Node<K,V> f; int n, i, fh;
                 if (tab == null || (n = tab.length) == 0)
                     tab = initTable();//初始化
                 else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {//添加元素， 当前桶没有元素， CAS添加
                     if (casTabAt(tab, i, null,
                                  new Node<K,V>(hash, key, value, null)))
                         break;                   // no lock when adding to empty bin
                 }
                 else if ((fh = f.hash) == MOVED)
                     tab = helpTransfer(tab, f);//协助扩容。如果当前位置被迁移了， 这个位置就塞一个forward节点。将原来的hash桶划分成很多歌小块， 每个小块由一个线程负责数据的迁移工作。每个小块中的每个桶迁移完了之后向其中塞一个forward节点。 从后向前迁移。迁移的时候加syncchronized锁， 防止有其他线程向这个位置上添加元素
                 else {
                     V oldVal = null;
                     synchronized (f) {//对链表头或红黑树根节点结点加锁
                         if (tabAt(tab, i) == f) {//如果链表变成树或者红黑树变成链表， 根节点就不是原来的节点了
                             if (fh >= 0) {//链表插入，尾插法
                                 binCount = 1;
                                 for (Node<K,V> e = f;; ++binCount) {
                                     K ek;
                                     if (e.hash == hash &&
                                         ((ek = e.key) == key ||
                                          (ek != null && key.equals(ek)))) {
                                         oldVal = e.val;
                                         if (!onlyIfAbsent)
                                             e.val = value;
                                         break;
                                     }
                                     Node<K,V> pred = e;
                                     if ((e = e.next) == null) {
                                         pred.next = new Node<K,V>(hash, key,
                                                                   value, null);
                                         break;
                                     }
                                 }
                             }
                             else if (f instanceof TreeBin) {//红黑树插入
                                 Node<K,V> p;
                                 binCount = 2;
                                 if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                                value)) != null) {
                                     oldVal = p.val;
                                     if (!onlyIfAbsent)
                                         p.val = value;
                                 }
                             }
                         }
                     }
                     if (binCount != 0) {//链表元素数量是否大于8， 桶的数量大于64，变成红黑树， 否则只是容量大于8， 只扩容
                         if (binCount >= TREEIFY_THRESHOLD)
                             treeifyBin(tab, i);
                         if (oldVal != null)
                             return oldVal;
                         break;
                     }
                 }
             }
             addCount(1L, binCount);//维护集合长度（桶的数量）
             return null;
         }
     ```

     > addCount 维护桶数量
     >
     > 1. 首先对baseCount CAS加， 如果加成功OK。 没加成功， countCells机制，最后集合的长度等于baseCount， 加countCells数组中每个value总和
     >
     >    * 如果对应countCell数组位置没有元素， 自旋将cellBusy改为1，new 一个新的countCell对象放入该数组位置
     >    * 如果该数组位置有countCell对象， 自旋将该对象中的value值++。如果自旋加失败了， 将这个数组扩容， 因为出现碰撞了， 在线程不超过当前CPU核心数的话继续扩容
     >    * 最终的桶的数量为baseCount+countCell数组中每个元素value的和
     >
     > 2. countCell数组初始化是自旋的， 将cellBusy改为1， 其他线程就不能初始化。这段代码兼顾初始化和扩容数据从老数组拷贝到新数组操作
     >
     >    ![image-20210307112543391](E:\learningNotes\HashMap\image-20210307112543391.png)

   * 数组初始化， initTable方法

     CAS 

     ![image-20210307103854040](E:\learningNotes\HashMap\image-20210307103854040.png)

2. 图解

   * put加锁理解

### 三.jdk8扩容安全

1. 源码分析
2. 图解

### 四. jdk8多线程罗荣效率改进

1. 源码分析
   * 元素未添加， 先协助扩容， 扩容后再添加元素
   * 先添加元素， 再协助扩容
2. 图解

### 五.集合长度的累加方式

1. addCount方法
2. fullAddCount方法

### 六.jdk8集合长度获取

1. size方法
2. sumCount方法





