package com.chen.main;

import com.chen.util.JUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover;

import javax.annotation.Resource;

/**
 * Created by ChenNing on 16/5/20.
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/applicationContext.xml"})
public class WordsProcessor {

    @Autowired
    Pipeline pipeline;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    @Qualifier(value = "WordsProcessor")
    PageProcessor pageProcessor;

    @Test
    public void main() throws Exception {


            Spider spider = Spider.create(pageProcessor)
                    .addUrl("http://www.dacidian.net")
                            .thread(1)
//                    .addPipeline(pipeline)
                    .setScheduler(new QueueScheduler()
                            .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)));

            spider.run();




//        DelayQueueScheduler delayQueueScheduler = new DelayQueueScheduler(2, TimeUnit.SECONDS);
//        delayQueueScheduler.push(new Request("http://edu.163.com"), spider);
//        while (true){
//            Request poll = delayQueueScheduler.poll(spider);
//            System.out.println(System.currentTimeMillis()+"\t"+poll);
//        }
    }
}
