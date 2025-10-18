package edu.eci.arsw.psredis.connection;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class PSRedisTemplate extends StringRedisTemplate {

    public PSRedisTemplate(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }
}
