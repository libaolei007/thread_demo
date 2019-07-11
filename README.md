# thread_demo
线程的学习
1.并发优缺点

并发优点：
发挥多处理的强大的功能
建模的简单性
异步事件的简化处理
响应更加灵敏的用户界面

并发的缺点：
安全性问题
活跃性问题
性能问题
java监视和管理控制台：cmd命名输入 jconsole

2.理解多线程与并发之间的联系与区别
区别：多线程其实并不是多个线程一起执行，而是线程之间因为切换的速度非常的快，所以，我们看起来像不间断的执行。
并行表示的是多个任务同时执行
联系：多线程并不一定是并发，如果是并发执行，那么肯定是多个线程在一块执行。

3.多线程与多进程的联系
进程是资源分配的基本单位
进程中包含多个线程，线程共享进程的资源
线程是处理器调度的基本单位

4.线程垃圾收集器（多线程执行的垃圾收集器，并行执行的垃圾收集器，serial单线程执行的垃圾收集器。）
（单线程一味的收集干活，一直等它干完活就结束，多线程一边想着干活，一边进行线程之间的切换。对于占用内存比较少，回收时间本来就比较短，可以使用单线程的垃圾收集器来进行收集，而它的性能是远比多线程要快的，因此在某些情境下单线程是远比多线程要快的）。
多线程下载并不是多线程提高了速度，也不是多线程的性能提高了，而是，由于外部服务器对资源的限制，为每一个链接分配一定的带宽，而不是将全部带宽分给一个链接，也就是说多线程下载并不是多线程提高了速度，不是多线程的性能提高了，而是多个链接突破了这个远程服务器的限制，也就导致了性能的提高。

5.线程的状态以及各状态之间的转换详解
创建状态：new一个线程后，该进程就处于新建状态，此时由JVM为其分配内存，并初始化成员变量的值。
就绪状态：调用start()方法之后，该线程处于就绪状态。java虚拟机会为其创建方法调用栈和程序计数器等待调度运行。
运行状态：处于就绪状态的线程抢占到CPU,开始执行run()方法的线程执行体，则该线程处于运行状态。
阻塞状态：当处于运行状态的线程失去所占用资源之后，便进入阻塞状态。
死亡状态：线程结束或者出现异常或错误，该线程结束生命周期。

6.线程的初始化，中断和源码分析
Java中线程两种类型：用户线程和守护线程 Thread.setDaemon(false)为用户线程，默认也为用户线程，Thread.setDaemon(true)设置为守护线程。
区别：1.主线程结束后用户线程还会继续运行，JVM存活。
2.如果没有用户线程，都是守护线程，那么JVM结束（随之而来的是所有一切烟消云散，包括所有的守护线程）。
stop()只是让这个线程无限期的等待下去，这个线程所获取得锁、获取得其他资源都没有被释放掉，因此这种方法已经不建议使用了。
interrupt()：中断线程，interrupted()：查看当前线程是否中断，isInterrupted()：判断当前这个线程是否中断。
interrupted()是静态方法：测试当前线程是否已经是中断状态，执行后具有清除状态功能。
isInterrupted()是实例方法：测试线程Thread对象，是否已经是中断状态，但不会清除中断状态。

7-10.线程创建方式（8种）
继承Thread类
实现Runnable接口
带有返回值的线程继承Callable接口，使用FutureTask创建线程对象
内部类3种方式
Timer定时器 timer.schedule(new TimerTask(){},0,1000)
spring注解@Async
Lambda表达式parallelStream并发流

11.了解多线程带来的安全风险
线程安全性问题
活跃性问题 死锁(哲学家吃饭) 饥饿问题(食堂打饭) 活锁（两人过独木桥）
性能问题 线程切换

12.从线程的优先级看饥饿问题

饥饿问题出现的三种情况：
1.优先级高的线程吞噬掉了优先级低的线程的CPU时间片
2.线程被永久堵塞在一个等待进入同步块的状态
3.处于等待状态的线程永远不被唤醒

如何尽量避免饥饿问题：
1.设置合理的优先级
2.使用锁来代替synchronized

13.从java字节码看线程安全问题
java自带分析字节码文件工具javap -verbose name.class文件
线程安全性问题出现的条件：
多线程环境下
多个线程共享一个资源
对资源进行非原子性操作

14.synchronized保证线程安全的原理（理论层面）
内置锁/互斥锁
修饰普通方法：内置锁就是当前实例
修饰静态方法：内置锁就是当前Class字节码文件
修饰代码块：内置锁是放入的对象

15.synchronized保证线程安全的原理（JVM层面）
锁存在的位置：存在对象头中
对象头中的信息：Mark Word，Class Metadata Address，Array Length

Mark Word：
线程Id
Epoch
对象的分代年龄信息
是否是偏向锁
锁标志位

偏向锁：
每次获取锁和释放锁会浪费资源
很多情况下，竞争锁不是有多个线程，而是只有一个线程在使用
只有一个线程在访问同步代码块的场景

轻量级锁：
自旋
多线程使用

重量级锁：
只能有一个锁进入代码块

16.单例问题与线程安全性深入解析
饿汉式单例模式不会出现线程安全问题
懒汉式单例模式使用双重检查加锁和volatile关键字提高CPU性能和避免线程安全性问题

17.理解自旋锁，死锁，重入锁
自旋锁就是代码被多个线程访问时，如果上一个锁不被释放，则下一个线程进入自旋状态。
死锁就是被访问的资源被互相锁住，就进入死锁状态
重人锁就是一个对象被线程访问时，可以进入另一个同步方法，线程不会出现死锁。因为这两个方法锁的是同一个对象。

18.深入理解volatile原理与使用
被volatile修饰的变量，在汇编中多了一个lock指令，lock指令将当前处理器缓存行的数据写回到系统内存中，其他cpu缓存该内存地址的数据失效了,
这就保证了当我们一个线程修改volatile修饰的变量的时候，另一个线程是可见的。
大量使用volatile会使处理器的缓存失效了，也就是说大量使用volatile会降低性能。
对比：volatile只能保证变量的可见性，但是，并不能保证对这个变量所操作的原子性。因此，synchronized可以完全替代volatile,但是volatile并不能取代
synchronized。
volatile保证变量在多个线程之间可见，保证变量的一致性
volatile称为轻量级锁，被volatile修饰的变量，在线程之间是可见的。（可见：一个线程修改了这个变量的值，在另外一个线程中能够读到这个修改后的值）
volatile除了线程之间互斥以外，另一个非常大的作用，就是保证可见性。

19.JDK5提供的原子类操作以及实现原理
AtomicInteger 对整形数据类型原子性操作
AtomicIntegerArray 对整形数组的原子性操作
AtomicReference<> 对实体对象的原子性操作
AtomicIntegerFieldUpdater<> a = AtomicIntegerFieldUpdater.newUpdater(A.class,“old”);对实体对象的字段进行原子性操作，字段需要用volatile操作。

20.Lock接口认识与使用
Lock可以非阻塞的获取锁，能被中断的获取锁，超时获取锁。
提供的方法：lock()加锁 unlock()解锁 lockInterruptibly()中断锁boolean tryLock()获取锁。
Lock需要显示地获取和释放锁，繁琐，但可以使代码更灵活。
Synchronized不需要显示地获取和释放锁，使用简单。
Lock lock = new ReentrantLock();可以方便的实现公平性。

21.手动实现一个可重入锁
思路：继承Lock接口，实现lock()和unlock()方法，声名布尔型isLockd,线程LockBy,整形lockcount三个变量。
操作这三个变量，通过wait()和notify()函数控制线程运行。

22,23.AbstractQueuedSynchronizer(AQS)详解
实现加锁和重入锁

24.公平锁
公平是针对锁的获取而言的，如果一个锁是公平的，那么锁的获取顺序就应该符合请求的绝对时间顺序。

25.读写锁
写 ：排他锁
读：共享锁
读-读能共存，
读-写不能共存，
写-写不能共存

26.分析ReentrantReadWriteLock
tryAcquire(int acquires) 互斥锁加锁
tryRelease(int releases) 互斥锁释放
tryAcquireShared(int unused)共享锁加锁
tryReleaseShard(int unused) 共享锁释放

27.锁降级和锁升级
锁降级是指写锁降级为读锁，在写锁没有释放的时候，获取到读锁。
把读锁升级为写锁，在读锁没有释放的时候，获取到写锁。

28.线程安全性问题总结

重复线程安全性问题出现的条件
多线程环境下
具有共享资源
对共享资源进行非原子性操作

解决线程性安全问题的途径
使用synchronized
volatile
JDK原子类
使用Lock

29.线程间的通信
wait()和notify()必须放在同步代码块中。
调用wait()方法时会释放锁，调用notify()时会加锁。释放锁和加锁都是这当前锁释放时拿到的。

30.生产者和消费者问题
生产类实现Runnable，传入对象
消费类实现Runnbale，传入对象
对象类里面做生产和消费，控制产品个数，设置产品数量，然后利用通信控制生产和消费。

31-33.Condition的使用及原理解析有界队列，和源码阅读
Condition的作用是对锁进行更精确的控制，它的await()相当于wait()，它的signal()相当于notify()，它的signalAll()相当于notifyAll()
不同的是它不需要与synchronized捆绑使用，但需要与互斥锁和共享锁捆绑使用。

34.实现数据库连接池
连接池有助于数据库性能的提升
初始化时建立一定个数的连接放到linklist里，获取连接时去池中拿连接，如果池中没有连接则等待，释放一个连接时，可以叫醒获取连接的线程。

35.线程加塞join
语法：j.start();j.join()

36.ThreadLocal用于保存某个线程共享变量
get: 获取ThreadLocal中当前线程共享变量的值。
set: 设置ThreadLocal中当前线程共享变量的值。
remove: 移除ThreadLocal中当前线程共享变量的值。
initialValue: ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值。

37-41.并发工具类：CountDownLatch，CyclicBarrier，Semaphore，Exchanger
Semaphore：一个计数信号量
CountDownLatch：一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
CyclicBarrier：一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点
Exchanger：方便了两个共同操作线程之间的双向交换

42-44.FuterTask
哎，没理解透，找个好心情再出发： https://blog.csdn.net/codershamo/article/details/51901057

45.Fork/Join框架

拆分处理最后合并，能够发挥多核服务器的优势
ForkJoinTask 实现类 RecursiveTask
class A extends RecursiveTask
@Override
protected Integer compute(){
A a1 = new A();	
A a2 = new A();
a1.fork();
a2.fork();
return a1.join() + a2.join();
}
ForkJoinPool pool = new ForkJoinPool();
Future f = pool.submit(new A());
结果：f.get();

46-48.同步容器和并发容器

Vector（线程安全） -----同步容器----> ArrayList（线程不安全）-----并发容器----->CopyOnWriteArrayList
Hashtable（线程安全）-------同步容器----> HashMap（线程不安全）----并发容器----->CopyOnWriteHashMap
并发容器：ConcurrentLinkedQueue

49.java中的阻塞队列BlockingQueue

private BlockingQueue queue = new ArrayBlockingQueue<>(10);
queue.push(1)发送 queue.take()抓取

50.Executors框架

public static void main(String[] args) {
// ExecutorService threadPool = Executors.newFixedThreadPool(10);//分配固定的线程数
ExecutorService threadPool = Executors.newCachedThreadPool();// 根据需要自动建立相应的线程数
for (int i = 0; i < 50; i++) {
threadPool.execute(new Runnable() {
@Override
public void run() {
System.out.println(Thread.currentThread().getName());
}
});
}
threadPool.shutdown(); // 关闭线程池
}
