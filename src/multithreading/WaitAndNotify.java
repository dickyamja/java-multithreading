/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmadmudzakir
 */
public class WaitAndNotify {
    public static void main(String[] args){
        ProcessorWaitAndNotify processorWaitAndNotify = new ProcessorWaitAndNotify();
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processorWaitAndNotify.producer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(WaitAndNotify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processorWaitAndNotify.consumer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(WaitAndNotify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(WaitAndNotify.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
