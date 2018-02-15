package mythread;

/**
 * Created by PearsonLee on 2017/12/24.
 */
public class SynchronizedReadWriteDemo {

  public static void main(String[] args) {
    final SynchronizedReadWriteDemo demo = new SynchronizedReadWriteDemo();

    new Thread() {
      @Override
      public void run() {
        demo.get(Thread.currentThread());
      }
    }.start();

    new Thread(new Runnable() {
      public void run() {
        demo.get(Thread.currentThread());
      }
    }).start();
  }

  public synchronized void get(Thread thread) {
    long start = System.currentTimeMillis();
    int i = 0;
    while (System.currentTimeMillis() - start <= 1) {
      i++;
      if (i % 4 == 0) {
        System.out.println(thread.getName() + "正在进行写操作");
      } else {
        System.out.println(thread.getName() + "正在进行读操作");
      }
    }
    System.out.println(thread.getName() + "读写操作完毕");
  }
}
