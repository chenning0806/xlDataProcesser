package com.chen.dao;

import com.chen.bean.EDUNews;
import org.springframework.stereotype.Repository;

/**
 * Created by ChenNing on 16/5/20.
 */
@Repository
public interface EDUNewsDao {

    public void savaEDUNews(EDUNews eduNews);

}
