package com.winn.aliyun.util.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description: 线程池
 *
 * @Date: 2020/6/10
 */
public class ThreadPoolExecutorUtil extends ThreadPoolExecutor {

    private static Logger log = LogManager.getLogger(ThreadPoolExecutorUtil.class);

    private static ThreadPoolExecutorUtil pool;

    //核心线程个数
    private static final int CORE_POOL_SIZE = 4;

    //池中允许的最大线程数
    //如果核心线程数满了，那么超过核心线程数到最大线程数的这些线程，会在执行完之后，时间到达KEEP_ALIVE_TIME最大时间时，退出线程
    private static final int MAXIMUM_POOL_SIZE = 4;

    //当线程数大于核心时，这是多余的空闲线程在终止之前等待新任务的最长时间
    //如果设置为0，则是不等待，直接退出
    private static final int KEEP_ALIVE_TIME = 30;

    static {
        //固定线程池
        pool = new ThreadPoolExecutorUtil(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * Description:     execute方法：所有实现了Runnable接口的任务都可以使用execute方法进行提交。而实现了Runnable接口的任务，
     * 并没有提供任何“标准”的方式为我们返回任务的执行结果。线程在线程池中运行结束了，就结束了。所以，使用execute方法提交的任务，
     * 程序员并不能在任务执行完成后，获得一个“标准”的执行结果。
     * <p>
     * submit方法：submit方法提交的任务是实现了Callable接口的任务。Callable接口的特性是，在其运行完成后，会返回一个“标准”的执行结果。
     *
     * @Date: 2020/6/10
     */
    public static void executeThread(Runnable runnable) {
        if (null == pool || pool.isTerminated()) {
            pool = new ThreadPoolExecutorUtil(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
        }
        pool.execute(runnable);
    }

    /**
     * 线程个数
     */
    private AtomicInteger tasksNum = new AtomicInteger();

    /**
     * 线程总时间
     */
    private AtomicLong totalTime = new AtomicLong();

    /**
     * 当前线程开始时间
     */
    private ThreadLocal<Long> threadStartTime = new ThreadLocal<>();

    /**
     * Description: 执行给定Runnable之前调用的方法
     *
     * @Date: 2020/6/10
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        log.info(Thread.currentThread().getName() + " 任务准备执行...");
        threadStartTime.set(System.currentTimeMillis());
        super.beforeExecute(t, r);
    }

    /**
     * Description: 给定Runnable执行完成时调用的方法
     *
     * @Date: 2020/6/10
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        tasksNum.incrementAndGet();
        long threadEndTime = System.currentTimeMillis();
        long executeTime = threadEndTime - threadStartTime.get();
        log.info(Thread.currentThread().getName() + "任务执行毫秒数:" + executeTime);
        totalTime.addAndGet(executeTime);
    }

    /**
     * Description: 执行程序终止时调用的方法---当线程池本身停止执行的时候，该方法就会被调用。
     *
     * @Date: 2020/6/10
     */
    @Override
    protected void terminated() {
        super.terminated();
        log.info("All tasks completed....  tasksNum=" + tasksNum.get() + ",totalTime=" + totalTime.get());
    }

    public ThreadPoolExecutorUtil(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolExecutorUtil(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolExecutorUtil(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
            RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadPoolExecutorUtil(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
}
