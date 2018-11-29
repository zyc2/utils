package pers.zyc.common.test;

import org.junit.Test;

/**
 * @author YanchaoZhang
 * @date 2018/11/22 17:57
 */
public class ThreadTest {
    int count = 0;

    private synchronized void add() {
        System.out.println(Thread.currentThread().getName() + "\t" + count++);
        try {
            this.notify();
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (; count < 100; ) {
                add();
            }
        }, "T1");
        Thread t2 = new Thread(() -> {
            for (; count < 100; ) {
                add();
            }
        }, "T2");
//        Thread t3 = new Thread(() -> {
//            for (; count < 100; ) {
//                add();
//            }
//        }, "T3");
        t1.start();
        t2.start();
//        t3.start();
        t1.join();
        t2.join();
//        t3.join();
    }
}
