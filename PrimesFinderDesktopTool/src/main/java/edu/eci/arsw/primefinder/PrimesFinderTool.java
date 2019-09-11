package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

	public static void main(String[] args) throws InterruptedException {
           
            // Setting up the threads
            
            
            int maxPrim=100;
            
            PrimesResultSet prs=new PrimesResultSet("john");
            PrimeFinder finderTreads[] = new PrimeFinder[4];
            
            for(int i = 0; i < finderTreads.length;  i++){
                Integer start = 1 + i * (maxPrim / finderTreads.length);
                Integer end;
                if(i != finderTreads.length - 1){
                     end = (1 + (i+1) * (maxPrim / finderTreads.length)) - 1;
                }else{
                    end = maxPrim;
                }
                finderTreads[i] = new PrimeFinder(new BigInteger(start.toString()), new BigInteger(end.toString()), prs, Integer.toString(i + 1));
                finderTreads[i].start();
            }
            
            

            /**
             * Running the program checking if User is working
             * if the user is working, the threads are paused
             * if the user is not working, threads are unpaused and calculating prime numbers
             */
            boolean toPause = true;
            while(true){
                try {
                     
//                    check every 10ms if the idle status (10 seconds without mouse
//                    activity) was reached. 
                    Thread.sleep(10);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
                        
                        if(!toPause){
                            System.out.println("Idle CPU ");
                            synchronized(PrimeFinder.pauseLock){
                                PrimeFinder.unPauseCalculation();
                                PrimeFinder.pauseLock.notifyAll();
                                System.out.println("Calculating again");
                            }
                            toPause = true;
                        }
                        
                    }
                    else{
                        if(toPause){
                            synchronized(PrimeFinder.pauseLock){
                                PrimeFinder.pauseCalculation();
                            }
                            toPause = false;
                        }
                            
                        System.out.println("User working again!");
                        
                    }
                    
                    // Check if all threads finished their execution
                    boolean isFinished = true;
                    for(PrimeFinder pf : finderTreads){
                        if(pf.getState() != Thread.State.TERMINATED){
                            isFinished = false;
                        }
                    }
                    
                    // If so, exit the loop
                    if(isFinished)
                        break;
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println("Prime numbers found:");
            
            System.out.println(prs.getPrimes());
                        

                        
            
            
            
            
	}
	
}


