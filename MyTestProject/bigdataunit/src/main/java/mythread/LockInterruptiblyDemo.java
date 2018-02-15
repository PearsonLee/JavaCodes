package mythread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by PearsonLee on 2017/12/24.
 */
public class LockInterruptiblyDemo {

  static Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    LockInterruptiblyDemo demoLock = new LockInterruptiblyDemo();

    Thread thread0 = new DemoThread(demoLock);
    Thread thread1 = new DemoThread(demoLock);
    thread0.start();
    thread1.start();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      //ignore
    }

    thread1.interrupt();
    System.out.println("-------------");

  }

  public void insert(String threadName) throws InterruptedException {

    System.out.println(threadName + " start working...");

    lock.lockInterruptibly();
    try {
      System.out.println(threadName + "get lock...");
      long start = System.currentTimeMillis();
      for (; ; ) {
        if (System.currentTimeMillis() - start > Long.MAX_VALUE) {
          break;
        }
        //insert data...
      }
    } finally {
      System.out.println(threadName + " is release lock...");
      lock.unlock();
    }

  }

  static class DemoThread extends Thread {

    LockInterruptiblyDemo demoLock;

    public DemoThread(LockInterruptiblyDemo demoLock) {
      this.demoLock = demoLock;
    }

    @Override
    public void run() {
      String threadName = Thread.currentThread().getName();
      try {
        demoLock.insert(threadName);
      } catch (InterruptedException e) {
        System.out.println(threadName + " is interrupted!");
      }
    }
  }
}
