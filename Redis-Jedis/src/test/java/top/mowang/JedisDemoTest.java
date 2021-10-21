package top.mowang;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.io.OutputStream;
import java.util.List;
import java.util.Set;

/**
 * Redis6-Study
 *
 * @author : Xuan Li
 * @website : https://mowangblog.top
 * @date : 2021/10/21 17:23
 **/
public class JedisDemoTest {
    public static void main(String[] args) {
        //创建jedis对象
        Jedis jedis = new Jedis("192.168.64.128",6379);
        System.out.println("jedis.ping() = " + jedis.ping());
    }

    @Test
    public void demo1(){
        Jedis jedis = new Jedis("192.168.64.128",6379);
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);

    }

    @Test
    public void demo2(){
        Jedis jedis = new Jedis("192.168.64.128",6379);
        List<String> k5 = jedis.lrange("k6", 0, -1);
        System.out.println(k5);
    }

    @Test
    public void demo3(){
        Jedis jedis = new Jedis("192.168.64.128",6379);
        jedis.sadd("name","lixuan");
        jedis.sadd("name","mowang");
        Set<String> name = jedis.smembers("name");
        name.forEach(System.out::println);
    }

    @Test
    public void demo4(){
        Jedis jedis = new Jedis("192.168.64.128",6379);
        jedis.hset("names","age","20");
        String hget = jedis.hget("names", "age");
        System.out.println(hget);
    }

    @Test
    public void demo5(){
        Jedis jedis = new Jedis("192.168.64.128",6379);
        jedis.zadd("china",100d,"上海");
        jedis.zadd("china",200d,"深圳");
        jedis.zadd("china",300d,"北京");
        Set<String> china = jedis.zrange("china", 0, -1);
        china.forEach(System.out::println);
    }
}
