package edu.eci.arsw.psredis.connection;

import org.springframework.stereotype.Component;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Component
public class PSRedisListenerContainer extends RedisMessageListenerContainer {

    public PSRedisListenerContainer(RedisConnectionFactory connectionFactory) {
        this.setConnectionFactory(connectionFactory);
    }
}
