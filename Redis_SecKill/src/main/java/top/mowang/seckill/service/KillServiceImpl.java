package top.mowang.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Redis6-Study
 *
 * @author : Xuan Li
 * @website : https://mowangblog.top
 * @date : 2021/10/25 20:40
 **/
@Service
public class KillServiceImpl {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 商品秒杀 uid 用户id | pid 商品id
     * @author: Xuan Li
     * @date: 2021/10/25 20:53
    */
    public boolean SecKill(Integer uid,Integer pid){
        System.out.println(uid+"|"+pid);
        return false;
    }


}
