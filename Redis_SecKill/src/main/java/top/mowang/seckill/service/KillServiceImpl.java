package top.mowang.seckill.service;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
     *
     * @author: Xuan Li
     * @date: 2021/10/25 20:53
     */
    public boolean SecKill(String uid, String pid) {
        System.out.println(pid + " | " + uid);
        //对传来的值做判空操作是一个好习惯
        if (uid == null || pid == null) {
            System.out.println("商品或用户不能为空");
            return false;
        }
        //获得操作redis对象
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        //设置key
        String numKey = "ms:" + pid + ":num";
        String userKey = "ms:" + pid + ":user";

        //获取库存
        String num = operations.get(numKey);
        if (num == null) {
            System.out.println("抢购活动还未开始");
            return false;
        }
        if (Integer.parseInt(num) < 1) {
            System.out.println("商品被抢光了，活动结束");
            return false;
        }

        //判断用户是否重复抢购
        SetOperations<String,Object> setOperations = redisTemplate.opsForSet();
        if (setOperations.isMember(userKey, uid)) {
            System.out.println("活动每人限购一件");
            return false;
        }
        //抢购成功，减库存
        operations.decrement(numKey);
        //添加用户到已购买用户名单
        setOperations.add(userKey,uid);
        System.out.println("用户"+uid+"秒杀成功");
        return true;
    }


}
