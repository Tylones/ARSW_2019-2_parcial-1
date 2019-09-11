/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.api.primesrepo.service;

/**
 *
 * @author 2169368
 * This exception is thrown when an already existing prime is added with a different user name
 */
public class PrimeAlreadyExistsException extends Exception{

    public PrimeAlreadyExistsException(String message) {
        super(message);
    }

    public PrimeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
