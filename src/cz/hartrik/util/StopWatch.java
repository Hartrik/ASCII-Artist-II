package cz.hartrik.util;

/**
 * Jednoduché stopky.
 * 
 * @author Patrik Harag
 */
public class StopWatch {
    
    private long start = 0;
    private long stop = 0;
    
    /** Spustí stopky */
    public void start() {
        start = System.currentTimeMillis();
    }
    
    /** Zastaví stopky. */
    public void stop() {
        stop = System.currentTimeMillis();
    }
    
    public long getMili() { return stop - start; }
    public long getSec()  { return getMili() / 1000; }
    public long getMin()  { return getSec()  / 60; }
    public long getHour() { return getMin()  / 60; }
    public long getDay()  { return getHour() / 24; }
    
}