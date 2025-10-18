package edu.eci.arsw.psredis.producer;

import edu.eci.arsw.psredis.connection.PSRedisListenerContainer;
import edu.eci.arsw.psredis.connection.PSRedisTemplate;
import edu.eci.arsw.psredis.receiver.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;

@Component
public class Producer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private final ApplicationContext appContext;
    private final PSRedisTemplate template;
    private final PSRedisListenerContainer container;

    public Producer(ApplicationContext appContext, PSRedisTemplate template, PSRedisListenerContainer container) {
        this.appContext = appContext;
        this.template = template;
        this.container = container;
    }

    @Override
    public void run(String... args) throws Exception {
        // Register multiple listeners (prototype-scoped beans)
        for (int i = 0; i < 7; i++) {
            Receiver receiver = appContext.getBean(Receiver.class);
            container.addMessageListener(receiver, new PatternTopic("PSChannel"));
        }

        Thread.sleep(500);

        for (int i = 1; i <= 6; i++) {
            LOGGER.info("Sending message {} ...", i);
            template.convertAndSend("PSChannel", "Hello from Redis! Message " + i);
            Thread.sleep(500);
        }

        // End the application after demo
        LOGGER.info("Demo finished. Shutting down.");
        System.exit(0);
    }
}
