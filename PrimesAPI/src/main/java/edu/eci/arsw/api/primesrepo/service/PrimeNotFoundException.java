/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.api.primesrepo.service;

/**
 *
 * @author 2169368
 * This exception is thrown when a specific prime is not found in the list
 */
public class PrimeNotFoundException extends Exception{

    public PrimeNotFoundException(String message) {
        super(message);
    }

    public PrimeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
