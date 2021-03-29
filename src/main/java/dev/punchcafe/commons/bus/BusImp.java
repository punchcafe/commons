package dev.punchcafe.commons.bus;

import java.util.HashSet;
import java.util.Set;

public class BusImp<T> implements Bus<T> {

    private final Set<Subscriber<T>> subscribers = new HashSet<>();

    @Override
    public void publish(T message) {
        for(final var subscriber : subscribers){
            subscriber.accept(message);
        }
    }

    public void acceptSubscriber(final Subscriber<T> subscriber){
        this.subscribers.add(subscriber);
    }
}
