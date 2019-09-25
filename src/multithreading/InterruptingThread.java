/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.Random;

/**
 *
 * @author ahmadmudzakir
 */
public class InterruptingThread {
    public static void main(String[] args) throws InterruptedException{
        
        System.out.println("Starting.");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for(int i = 0; i<1E8; i++){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted");
                        break;
                       
                    }
                    
                    Math.sin(random.nextDouble());
                }
            }
        });
        t1.start();
        
        Thread.sleep(500);
        
        t1.interrupt();
        t1.join();
        
        System.out.println("Finished");
    }
}
