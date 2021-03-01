package com.example.demo.redis;

import com.example.demo.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class Config {
    @Bean
    public RedisTemplate<Long, Post> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<Long,Post> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }


}
