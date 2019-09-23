/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ahmadmudzakir
 */
public class ReentrantLockRunner {
    private int count = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    
    public void increment(){
        for(int i=0; i<10000;i++){
            count++;
        }
    }
    
    public void firstThread() throws InterruptedException{
        lock.lock();
        System.out.println("Waiting...");
        condition.await();
        System.out.println("woken up !!");
        try{
            increment();
        }finally{
            lock.unlock();
        }
    }
    
    public void secondThread() throws InterruptedException{
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Please press return key !");
        new Scanner(System.in).nextLine();
        System.out.println("Got Return key !");
        condition.signal();
        try{
            increment();
        }finally{
            lock.unlock();
        }
    }
    
    public void finished(){
        System.out.println("Finished, count : " + count);
    }
}
