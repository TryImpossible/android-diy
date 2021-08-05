package com.barry.java.Thread;

/**
 * synchronized 实现线程的同步
 */
public class Tester5 {

    double balance = 1000.0;

    public void withdraw(double money) {
        // 把需要同步的代码放到同步语句块中
        synchronized (this) {
            double after = balance - money;
            // 延迟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 更新
            this.setBalance(after);
        }
    }

    private void setBalance(double money) {
    }
}
