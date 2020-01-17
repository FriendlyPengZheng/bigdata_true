package juc;

import java.util.concurrent.locks.ReentrantLock;

public class Util implements Runnable{

    ReentrantLock lock = new ReentrantLock();
    private  int Ticket = 100;

    public  void sale() {
        for (int i = 0; i < 100; i++){
            System.out.println("线程："+Thread.currentThread().getName()+"   在出售第"+(Ticket-i)+"张票");
        }
    }

    @Override
    public void run() {
        //lock.lock();
        sale();
        //lock.unlock();
    }
}
