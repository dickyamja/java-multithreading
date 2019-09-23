/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ahmadmudzakir
 */
public class DeadlockRunner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    private void acquiredLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {
            boolean gotFirstLocked = false;
            boolean gotSecondLocked = false;
            try {
                gotFirstLocked = firstLock.tryLock();
                gotSecondLocked = secondLock.tryLock();
            } finally {
                if (gotFirstLocked && gotSecondLocked) {
                    return;
                }

                if (gotFirstLocked) {
                    firstLock.unlock();
                }

                if (gotSecondLocked) {
                    secondLock.unlock();
                }
            }
            
            Thread.sleep(1);
        }

    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
//            lock1.lock();
//            lock2.lock();
            acquiredLocks(lock1, lock2);
            try {
                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
//            lock1.lock();
//            lock2.lock();
            acquiredLocks(lock1, lock2);
            try {
                Account.transfer(acc2, acc1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Account 1 , balance : " + acc1.getBalance());
        System.out.println("Account 2 , balance : " + acc2.getBalance());
        System.out.println("Total balance : " + (acc1.getBalance() + acc2.getBalance()));
    }
}
