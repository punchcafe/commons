package dev.punchcafe.commons.bus;

import java.util.Optional;

public interface BusBroker {

    static BusBroker newBusBroker() {
        return new BusBrokerImp();
    }

    <T> Optional<Bus<T>> getBus(final Class<T> messageType, final String topic);
}
