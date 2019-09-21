/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmadmudzakir
 */
public class ThreadPoolExample {

    static class Processor implements Runnable {

        int id;

        public Processor(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Starting : " + id);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadPoolExample.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Completed : " + id);
        }

    }
    
    public static void main(String [] args){
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i=0; i<5; i++){
            executorService.submit(new Processor(i));
        }
        executorService.shutdown();
        System.out.println("All tasks submitted");
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadPoolExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("All tasks finished");
        long end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start));
    }

}
