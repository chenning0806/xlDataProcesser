package com.chen.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
        BigDecimal schoolMoney = new BigDecimal("-1111");
        System.out.println(schoolMoney.compareTo(new BigDecimal("0")));
//        redisTemplate.delete("rede:all:rule");
//        BoundListOperations<String,String> boundValueOperations = redisTemplate.boundListOps("rede:all:rule");
//        List<String> l =  boundValueOperations.range(0, boundValueOperations.size());
//        System.out.println(l);

        //序列化工具
//        List<String> s = Arrays.asList("aaa");
//        System.out.println(s);
//        redisTemplate.delete("rede:init:time");
//        BoundHashOperations operations = redisTemplate.boundHashOps("rede:show");
//        List<String> list = new ArrayList<String>();
//        list.add("a");
//        list.add("b");
//        System.out.println(list.toString());
//        String s = JSON.toJSONString(list);
//        System.out.println(s);
//        operations.put("1000110002",s);
//        String ss = (String) operations.get("1000110002");
//        List<String> list1 = JSON.parseArray(ss, String.class);
//        for (String s1 : list1) {
//            System.out.println(s1);
//        }
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
