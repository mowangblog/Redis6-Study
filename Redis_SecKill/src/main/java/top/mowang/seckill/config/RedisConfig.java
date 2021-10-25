package top.mowang.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis6-Study
 *
 * @author : Xuan Li
 * @website : https://mowangblog.top
 * @date : 2021/10/25 23:09
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory factory){
        RedisTemplate redisTemplate = new RedisTemplate();
        //使用LettuceConnectionFactory 代替 RedisConnectionFactory
        redisTemplate.setConnectionFactory(factory);
        //配置序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        //设置redis支持数据库的事务
        redisTemplate.setEnableTransactionSupport(true);

        return redisTemplate;
    }

}
