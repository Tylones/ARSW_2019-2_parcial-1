package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */

@Service
public class PrimeServiceStub implements PrimeService
{
    
    List<FoundPrime> primes = new ArrayList<FoundPrime>();
    
    /**
     *
     * @param foundPrime
     * @throws PrimeAlreadyExistsException
     */
    @Override
    public void addFoundPrime( FoundPrime foundPrime ) throws PrimeAlreadyExistsException
    {
        
        
        for(int i = 0; i < primes.size(); i++){
            if(primes.get(i).equalsPrimeButNotUser(foundPrime))
                throw new PrimeAlreadyExistsException("Prime " + foundPrime.getPrime() + " already exists");
        }
        
        if(!primes.contains(foundPrime))
           primes.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        //TODO
        return primes;
    }

    @Override
    public FoundPrime getPrime( String prime )
    {
        //TODO
        for(int i = 0; i < primes.size(); i++){
            if(primes.get(i).getPrime().equals(prime)){
                return primes.get(i);
            }
        }
        return null;
    }
}
