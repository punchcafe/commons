package dev.punchcafe.commons.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.function.Function;

/**
 * A running cache a cache in which the entries are regularly updated in teh background by a specified function.
 * @param <ID>
 * @param <T>
 */
public class RunningCache<ID, T> {

    private Map<ID, CacheEntry<T>> cacheEntryMap;
    private ScheduledExecutorService updateThread;

    private Function<Set<ID>, List<T>> batchUpdateFunction;
    int updateInterval;
    int timeToLive;
    //TODO: rething use of set since ordering must be the same

    public static <T,ID> RunningCache<ID, T> buildRunningCache(Function<Set<ID>, List<T>> batchUpdateFunction ){
        return new RunningCache<ID, T>(Executors.newSingleThreadScheduledExecutor(), batchUpdateFunction);

    }

    private RunningCache(ScheduledExecutorService executor, Function<Set<ID>, List<T>> batchUpdateFunction) {
        this.batchUpdateFunction = batchUpdateFunction;
        this.updateThread = executor;
        this.cacheEntryMap = new HashMap<>();
        this.updateInterval = 30_000;
        this.timeToLive = 30_000;
    }

    public T get(ID id) {
        final var initialResult = this.cacheEntryMap.get(id);
        if (initialResult == null) {
            //TODO: check index
            final var entryElement = new CacheEntry<>(batchUpdateFunction.apply(Set.of(id)).stream().findFirst().get());
            this.cacheEntryMap.put(id, entryElement);
            return entryElement.getElement();
        }
        return initialResult.getElement();
    }
/*

    public void start() {
        updateThread.execute(this::runUpdate, this.updateInterval);
    }

 */

    private void runUpdate() {
        final var updatedEntries = batchUpdateFunction.apply(this.cacheEntryMap.keySet());
        if (updatedEntries.size() != this.cacheEntryMap.keySet().size()) {
            throw new RuntimeException();
            // alternatively, log error and toss result
        }

        //TODO: reassign shit
    }

}
