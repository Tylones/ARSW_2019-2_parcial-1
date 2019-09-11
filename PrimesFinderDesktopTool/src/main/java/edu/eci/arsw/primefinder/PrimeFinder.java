package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.codegen.CompilerConstants;

public class PrimeFinder extends Thread{

    BigInteger _a; 
    BigInteger _b; 
    PrimesResultSet prs;
    
    public volatile static Lock pauseLock = new ReentrantLock();
    private volatile static boolean isPaused = true;

    public PrimeFinder(BigInteger _a, BigInteger _b, PrimesResultSet prs, String name) {
        this._a = _a;
        this._b = _b;
        this.prs = prs;
        Thread.currentThread().setName(name);
    }
    
        
    public void run(){
        try {
            findPrimes(_a, _b, prs);
        } catch (InterruptedException ex) {
            Logger.getLogger(PrimeFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    
    
        
    public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs) throws InterruptedException{

            BigInteger a=_a;
            BigInteger b=_b;

            MathUtilities mt=new MathUtilities();

            int itCount=0;

            BigInteger i=a;
            while (i.compareTo(b)<=0){
                
                // Check if paused 
                synchronized(pauseLock){
                    while(isPaused){
                        pauseLock.wait();
                        System.out.println(Thread.currentThread().getName() + " is working again");
                    }
                }
                itCount++;
                if (mt.isPrime(i)){
                    prs.addPrime(i);
                }

                i=i.add(BigInteger.ONE);
            }

    }
    
    public static void pauseCalculation(){
        PrimeFinder.isPaused = true;
    }
    
    public static void unPauseCalculation(){
        PrimeFinder.isPaused = false;
    }
	
	
}
