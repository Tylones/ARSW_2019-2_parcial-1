package edu.eci.arsw.api.primesrepo.model;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
public class FoundPrime
{
    String user;

    String prime;

    public FoundPrime()
    {
    }

    public FoundPrime(String user, String prime) {
        this.user = user;
        this.prime = prime;
    }
    
    

    public String getUser()
    {
        return user;
    }

    public void setUser( String user )
    {
        this.user = user;
    }

    public String getPrime()
    {
        return prime;
    }

    public void setPrime( String prime )
    {
        this.prime = prime;
    }
    
    public boolean equalsPrimeButNotUser(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof FoundPrime))return false;
        FoundPrime otherPrime = (FoundPrime)other;
        return (otherPrime.getPrime().equals(this.prime) && !otherPrime.getUser().equals(this.user));
    }
    
    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof FoundPrime))return false;
        FoundPrime otherPrime = (FoundPrime)other;
        return (otherPrime.getPrime().equals(this.prime) && otherPrime.getUser().equals(this.user));
    }
    
    
}
