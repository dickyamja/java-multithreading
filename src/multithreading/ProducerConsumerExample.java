/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmadmudzakir
 */
public class ProducerConsumerExample {
    private static BlockingQueue<Integer> blocking = new ArrayBlockingQueue<>(10);
    
    public static void main(String[] args) {
        try {
            Thread producerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        producer();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProducerConsumerExample.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
            });
            
            Thread consumerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        consumer();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProducerConsumerExample.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            producerThread.start();
            consumerThread.start();
            
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProducerConsumerExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void producer() throws InterruptedException {
        Random random = new Random();
        while(true){
            blocking.put(random.nextInt(100));
        }
       
    }
    
    private static void consumer() throws InterruptedException{
        Random random = new Random();
        while(true){
            Thread.sleep(100);
            if(random.nextInt(10) == 0){
                Integer value = blocking.take();
                System.out.println("Taken value : " + value 
                        + " blocking size : " + blocking.size());
            }
            
        }
    }
    
    
    
}
