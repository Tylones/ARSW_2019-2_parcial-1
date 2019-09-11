package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import edu.eci.arsw.api.primesrepo.service.PrimeAlreadyExistsException;
import edu.eci.arsw.api.primesrepo.service.PrimeNotFoundException;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
@Service
public class PrimesController
{
    
    @Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = RequestMethod.GET )
    public ResponseEntity<?> getPrimes()
    {
        try{
            
            
            Gson gson  = new Gson();
            String jsonToReturn = "{\n" + "  \"primes\":" + gson.toJson(primeService.getFoundPrimes()) + "}";
            
            return new ResponseEntity<>(jsonToReturn,HttpStatus.OK);
            
        }catch(Exception ex){
            return new ResponseEntity<>("Primes not found", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping( value = "/primes", method = RequestMethod.POST)
    public ResponseEntity<?> postPrimes(@RequestBody String body)
    {
        try{
            
            System.out.println("Received Post");
            final JSONObject obj = new JSONObject(body);
            final JSONArray primes = obj.getJSONArray("primes");
            
            for(int i = 0; i < primes.length(); i++){
                final JSONObject primeObject = primes.getJSONObject(i);
                String user  = primeObject.getString("user");
                String prime = primeObject.getString("prime");
                
                FoundPrime newPrime = new FoundPrime(user, prime);
                
                primeService.addFoundPrime(newPrime);
                
                
            }
         
            
            return new ResponseEntity<>("Primes created", HttpStatus.CREATED);
            
        }catch(PrimeAlreadyExistsException ex){
            return new ResponseEntity<>("One or several prime numbers already exist", HttpStatus.FORBIDDEN);
        }
    }
    

    @RequestMapping( value = "/primes/{primenumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrimeNumber(@PathVariable String primenumber)
    {
        try{
            FoundPrime fp = primeService.getPrime(primenumber);
            
            String jsonToReturn = "{\"user\":\"" + fp.getUser() + "\",\"prime\":\"" + fp.getPrime() + "\"}";
            
            return new ResponseEntity<>(jsonToReturn,HttpStatus.OK);
            
        }catch(PrimeNotFoundException ex){
            return new ResponseEntity<>("404" + ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    




}
