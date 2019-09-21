/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmadmudzakir
 */

class Processor extends Thread{
    
    private boolean isRunning = true;

    @Override
    public void run() {
        while(isRunning){
            System.out.println("Hello Emma");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void shutdown(){
        isRunning = false;
    }
}

public class MultiThreading {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();
        
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        
        processor.shutdown();
    }
    
}
