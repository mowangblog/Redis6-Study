package top.mowang.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootTest
class RedisSpringBootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;


    @Test
    void RedisTest() {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        operations.set("hello7","world");
        String k1 = operations.get("hello7");
        System.out.println(k1);
    }

}
