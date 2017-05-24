package com.chen.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenNing on 16/5/23.
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/applicationContext.xml"})
public class RedisTest {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    private static final String _userAchieve = "ach_userach:{0}:{1}";
    @Test
    public void main() throws InterruptedException {
        redisTemplate.delete("rede:all:rule");
        BoundListOperations<String,String> boundValueOperations = redisTemplate.boundListOps("rede:all:rule");
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        String[] strings = new String[list.size()];
        list.toArray(strings);
        boundValueOperations.rightPushAll(strings);
        List<String> l =  boundValueOperations.range(2, boundValueOperations.size());

        System.out.println(l);

        //序列化工具
//        List<String> s = Arrays.asList("aaa");
//        System.out.println(s);
//        redisTemplate.delete("rede:init:time");
//        BoundHashOperations operations = redisTemplate.boundHashOps("rede:show");
//        System.out.println(operations.increment("10001",-10));

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
//        BoundHashOperations redisShowHash = redisTemplate.boundHashOps("rede:showw");
//        String hashKey = MessageFormat.format(_userAchieve, "1000110002", null);
//        System.out.println(hashKey);
//        String key = 1+"_"+"0";
//        System.out.println(getHashValueExists(hashKey, key));
//        setHashValue(hashKey, key,"123");
//        redisShowHash.put("","");


    }


    /**
     * hash结构中获取hash中field是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean getHashValueExists(String key, String field) {
        final byte[] keyByte = key.getBytes();
        final byte[] fieldByte = field.getBytes();
        Boolean b = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hExists(keyByte, fieldByte);
            }
        }, true, false);
        return b == null ? false : b;
    }

    /**
     * hash结构中添加和修改value值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Boolean setHashValue(String key, String field, String value) {
        final byte[] keyByte = key.getBytes();
        final byte[] fieldByte = field.getBytes();
        final byte[] valueByte = value.getBytes();
        Boolean b = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hSet(keyByte, fieldByte, valueByte);
            }
        }, true, false);
        return b == null ? false : b;
    }
}
