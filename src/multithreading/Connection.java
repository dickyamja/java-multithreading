/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmadmudzakir
 */
public class Connection {
    private Semaphore semaphore = new Semaphore(10, true);
    private static Connection instance = new Connection();
    private int connections = 0;
    private Connection(){
        
    }
    
    public static Connection getInstance(){
        return instance;
    }
    
    public void connect(){
        try {
            semaphore.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            doConnect();
        }finally {
            semaphore.release();
        }
        
    }
    
    private void doConnect(){
        synchronized(this){
            connections++;
            System.out.println("Current Connections : " + connections);
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        synchronized(this){
            connections--;
        }
        
    }
}
