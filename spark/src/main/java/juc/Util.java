package juc;

import javax.xml.bind.SchemaOutputResolver;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Util {
    int Ticket = 20;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Util util = new Util();

        new Thread(() -> {
            for (int i = 0; i < 20; i++)
                util.sale();
        }, "A")
                .start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++)
                util.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++)
                util.sale();
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++)
                util.sale();
        }, "D").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++)
                util.sale();
        }, "E").start();
    }

    public synchronized void sale() {
        //lock.lock();
        if (Ticket > 0) {
            try {
                Thread.sleep(100);
                System.out.println("线程：" + Thread.currentThread().getName() + "   在出售第" + (Ticket--) + "张票");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                //lock.unlock();
            }
        }
    }

}
