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
     * @throws PrimeAlreadyExistsException if trying to add an existing prime but with an other username
     */
    @Override
    public void addFoundPrime( FoundPrime foundPrime ) throws PrimeAlreadyExistsException
    {
        
        // Check if the prime already exists but with a different user name
        for(int i = 0; i < primes.size(); i++){
            if(primes.get(i).equalsPrimeButNotUser(foundPrime))
                throw new PrimeAlreadyExistsException("Prime " + foundPrime.getPrime() + " already exists");
        }
        
        // Check if the list already contains the same prime number (with the same user name)
        // If so, do not add it to the list
        if(!primes.contains(foundPrime))
           primes.add(foundPrime);
    }

    /**
     * 
     * @return the list of FoundPrime
     */
    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        //TODO
        return primes;
    }

    /**
     * 
     * @param prime the prime number to get the corresponding FoundPrime
     * @return the corresponding FoundPrime
     */
    @Override
    public FoundPrime getPrime( String prime ) throws PrimeNotFoundException
    {
        //TODO
        for(int i = 0; i < primes.size(); i++){
            if(primes.get(i).getPrime().equals(prime)){
                return primes.get(i);
            }
        }
        
        throw new PrimeNotFoundException("Prime number : " + prime + " was not found");
    }
}
