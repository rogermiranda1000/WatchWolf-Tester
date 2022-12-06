package com.rogermiranda1000.watchwolf.tester;

/**
 * We can guarantee synchronization in all the WatchWolf components, but we can't guarantee synchronization across
 * two different components. The function of this manager is to
 */
public interface SynchronizationManager {
    public void requestSynchronization(Petition current);
}
