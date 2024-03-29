package dev.punchcafe.commons.cache;

import jdk.jfr.Experimental;

import java.time.LocalDateTime;

@Experimental
class CacheEntry<T> {
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessedTime;

    private T element;

    public CacheEntry(T element){
        this.creationTime = LocalDateTime.now();
        this.element = element;
    }

    public synchronized T getElement(){
        this.lastAccessedTime = LocalDateTime.now();
        return this.element;
    }

    public synchronized void setElement(T element){
        this.element = element;
    }
}
