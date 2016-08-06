package com.chen.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * Created by ChenNing on 16/5/23.
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/applicationContext.xml"})
public class RedisTest {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void main() throws InterruptedException {
        //序列化工具
        BoundHashOperations operations = redisTemplate.boundHashOps("rede:show");
        operations.put("1000110002","1");
//        String s = (String) operations.get("321312312");
//        redisTemplate.delete("rede:show");
//        BoundHashOperations redisShowHash = redisTemplate.boundHashOps("rede:show");
//        redisShowHash.put("","");
//        System.out.println(redisTemplate.hasKey("rede:show"));
//        final RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//        ValueOperations<String, String> boundValueOperations = redisTemplate.opsForValue();
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        hashOperations.put("rude:show","10001","1");
//        redisTemplate.expire("rude:show",10, TimeUnit.SECONDS);
//        String s  = (String) hashOperations.get("rude:show", "10001");
//        System.out.println(s);
//        hashOperations.put("rude:show","10001","1");
//        hashOperations.put("rude:show","10002","2");
//        redisTemplate.delete("rude:show");
//        hashOperations.delete("rude:show","10001","10002");
//        boundValueOperations.set("rude:show","123");
//        System.out.println(boundValueOperations.get("rude:show"));

    }


}
