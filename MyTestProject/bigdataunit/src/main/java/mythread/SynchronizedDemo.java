package mythread;

import javax.swing.plaf.TableHeaderUI;
import mythread.LockInterruptiblyDemo.DemoThread;

/**
 * Created by PearsonLee on 2017/12/24.
 */
public class SynchronizedDemo {

  public static void main(String[] args) {
    SynchronizedDemo sdemo = new SynchronizedDemo();
    Thread thread1 = new Thread(new DemoThread(sdemo));
    Thread thread2 = new Thread(new DemoThread(sdemo));
    thread1.start();
    thread2.start();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      //ignore
    }

    // 使用synchronized修改的共享对象，线程无法interrupted，使用lock时，可以
    thread2.interrupt();
    System.out.println("==============");
  }

  public void insert(String threadName, SynchronizedDemo sdemo) {

    System.out.println(threadName + " start working...");
    synchronized (sdemo) {
      System.out.println(threadName + " get lock...");
      for (int i = 0; i< 1000000 ; i++) {
        long start = System.currentTimeMillis();
        if (i > 1000000) {
          break;
        }
        //insert data
      }
    }
  }

  static class DemoThread implements Runnable {

    SynchronizedDemo sdemo;

    public DemoThread(SynchronizedDemo sdemo) {
      this.sdemo = sdemo;
    }

    public void run() {
      String threadName = Thread.currentThread().getName();
      sdemo.insert(threadName, sdemo);
    }
  }
}
