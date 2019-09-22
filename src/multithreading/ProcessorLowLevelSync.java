/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author ahmadmudzakir
 */
public class ProcessorLowLevelSync {
    private LinkedList<Integer> list = new LinkedList<>();
    private static Integer LIMIT = 10;
    private Object lock = new Object();
    public void producer() throws InterruptedException{
        int value = 0;
        while(true){
            synchronized (lock){
                while (list.size() == LIMIT){
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }
    
    public void consumer() throws InterruptedException{
        while(true){
            synchronized(lock){
                
                if(list.size() == 0){
                    lock.wait();
                }
                System.out.print("list size is : " + list.size());
                int value = list.removeFirst();
                System.out.println("; value taken : " + value);
                lock.notify();
                
            }
            Random random = new Random();
            Thread.sleep(random.nextInt(1000));
        }
    }
}
