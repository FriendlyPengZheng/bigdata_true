package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

public class Lock {
    /**
     * Java通过Executors提供四种线程池，分别为：
     * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
     * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     *
     *
     * Executors 和 ExecutorService 这两个接口主要的区别是：
     *      ExecutorService 接口继承了 Executor 接口，是 Executor 的子接口
     * Executors 和 ExecutorService 第二个区别是：
     *      Executor 接口定义了 execute()方法用来接收一个Runnable接口的对象，
     *      而 ExecutorService 接口中的 submit()方法可以接受Runnable和Callable接口的对象。
     * Executors 和 ExecutorService 接口第三个区别是:
     *      Executor 中的 execute() 方法不返回任何结果，
     *      而 ExecutorService 中的 submit()方法可以通过一个 Future 对象返回运算结果。
     * Executors 和 ExecutorService 接口第四个区别是:
     *      除了允许客户端提交一个任务，ExecutorService 还提供用来控制线程池的方法。
     *      比如：调用 shutDown() 方法终止线程池。
     * Executors 类提供工厂方法用来创建不同类型的线程池。比如: newSingleThreadExecutor() 创建一个只有一个线程的线程池，
     * newFixedThreadPool(int numOfThreads)来创建固定线程数的线程池，newCachedThreadPool()可以根据需要创建新的线程，
     * 但如果已有线程是空闲的会重用已有线程。
     * */
    public static void main(String[] args) {
        List<String> cowaList = new CopyOnWriteArrayList<>();
        List<String> strings = new ArrayList<>();
        List<String> strings1 = new Vector<>();
        ConcurrentHashMap<Object, Object> conMap = new ConcurrentHashMap<>();
        int ticket = 20;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Runnable() {
            public void run() {

            }
        }, 1);
        new Thread(integerFutureTask,"a");
        for (int i = 0; i < 20; i++) {
            int j = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程："+Thread.currentThread().getName()+"   在出售第"+(ticket- j)+"张票");
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
