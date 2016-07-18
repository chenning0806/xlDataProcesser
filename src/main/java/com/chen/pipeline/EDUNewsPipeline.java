package com.chen.pipeline;

import com.chen.bean.EDUNews;
import com.chen.dao.EDUNewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by ChenNing on 16/5/20.
 */
@Service
public class EDUNewsPipeline implements Pipeline {

    @Autowired
    EDUNewsDao eduNewsDao;


    public void process(ResultItems resultItems, Task task) {
        try {

        System.out.println("get page: " + resultItems.getRequest().getUrl());

        EDUNews edu = (EDUNews)resultItems.get("edu");

            if(edu!=null){

                eduNewsDao.savaEDUNews(edu);

            }

        }catch (Exception e){

            e.printStackTrace();

        }

        System.out.println("successful");

    }
}
