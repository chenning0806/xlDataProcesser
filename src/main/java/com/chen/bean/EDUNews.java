package com.chen.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ChenNing on 16/5/20.
 */
public class EDUNews implements Serializable{

    //教育新闻id（主键）
    private Integer id;

    //新闻url地址
    private String url;

    //新闻标题
    private String title;

    //新闻描述
    private String desc;

    //新闻发布时间
    private Date time;

    //新闻作者
    private String author;

    //新闻来源
    private String newsfrom;

    //新闻内容
    private String content;

    //图片地址
    private String imglist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsfrom() {
        return newsfrom;
    }

    public void setNewsfrom(String newsfrom) {
        this.newsfrom = newsfrom;
    }

    public String getImglist() {
        return imglist;
    }

    public void setImglist(String imglist) {
        this.imglist = imglist;
    }

    @Override
    public String toString() {
        return "EDUNews{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", time=" + time +
                ", author='" + author + '\'' +
                ", newsfrom='" + newsfrom + '\'' +
                ", content='" + content + '\'' +
                ", imglist='" + imglist + '\'' +
                '}';
    }
}
