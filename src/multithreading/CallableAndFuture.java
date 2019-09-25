/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmadmudzakir
 */
public class CallableAndFuture {
    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);
                if(duration >= 2000){
                    throw new IOException("Duration is too long..");
                }
                
                System.out.println("Starting");
                
                Thread.sleep(duration);
                System.out.println("Finished");
                return duration;
            }
        });
        
        executor.shutdown();
        
        try {
            System.out.println("Result : " + future.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(CallableAndFuture.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            System.out.println("ex : " + ex.getMessage());
        }
    }
}
