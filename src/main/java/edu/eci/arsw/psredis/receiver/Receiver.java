package edu.eci.arsw.psredis.receiver;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Receiver extends MessageListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private final AtomicInteger counter = new AtomicInteger();

    public Receiver() {
        this.setDefaultListenerMethod("receiveMessage");
    }

    public void receiveMessage(String message) {
        LOGGER.info(this.hashCode() + " - Received <{}>", message);
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
