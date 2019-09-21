/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmadmudzakir
 */
class ProcessorLatch implements Runnable{
    private CountDownLatch latch;
    
    public ProcessorLatch(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("Started");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcessorLatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        latch.countDown();
    }
    
}
public class CountDownLatchExample {
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executors = Executors.newFixedThreadPool(3);
        for(int i=0; i < 3; i++){
            executors.submit(new ProcessorLatch(latch));
        };
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(CountDownLatchExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Completed");
        executors.shutdown();
    }
}
