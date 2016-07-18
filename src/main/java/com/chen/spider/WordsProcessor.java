package com.chen.spider;

import com.chen.util.WordsFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ChenNing on 16/5/20.
 */
@Service("WordsProcessor")
public class WordsProcessor implements PageProcessor {


    private Site site = Site
            .me()
            .setDomain("http://www.dacidian.net")
            .setSleepTime(3000)
            //用户代理，伪装多个浏览器访问，防止有些网站判断是否是在恶意刷
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {

        //把当前页面符合的所有链接放入容器中进行遍历<br.*?>WordsFactory.getWords()
//        List<String> list = new ArrayList<String>();
//        list.add("http://11");
        page.addTargetRequests(WordsFactory.getWords());
        String title = page.getHtml().xpath("//div[@id='dic1']/p[@class='word']/text()").toString();
        String adj = page.getHtml().xpath("//div[@id='dic1']").regex("<p>.*?</p>").replace("<.?p>","").replace("<br.*?>","|").toString();
        String url = page.getRequest().getUrl();
        System.out.println(adj);
        System.out.println(title);
        if(title==null&&!"http://www.dacidian.net".equals(page.getRequest().getUrl())){
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("/Users/ChenNing/Downloads/noten.txt"), true));
                bufferedWriter.write(url.substring(url.lastIndexOf("/")+1,url.lastIndexOf(".")).replace("+"," ")+"\r\n");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public Site getSite() {
        return site;
    }



}
