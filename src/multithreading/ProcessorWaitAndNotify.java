/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.Scanner;

/**
 *
 * @author ahmadmudzakir
 */
public class ProcessorWaitAndNotify {

    public void producer() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread started...");
            wait();
            System.out.println("Resumed.");
        }
    }

    public void consumer() throws InterruptedException {
        System.out.println("Consumer thread started");
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        
        synchronized (this) {
            System.out.println("Waiting for input key");
            scanner.nextLine();
            System.out.println("Return key pressed...");
            notify();
            Thread.sleep(5000);
        }
    }
}
