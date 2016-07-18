package com.chen.spider;

import com.chen.bean.EDUNews;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;

/**
 * Created by ChenNing on 16/5/20.
 */
@Service
public class xlProcessor implements PageProcessor {

    public static long creatTime = System.currentTimeMillis();

    public static final String URL_LIST = "http://edu.163.com/\\d{2}/\\d{4}/\\d{2}/\\w+.html";
//    public static final String URL_LIST = "http://edu.163.com/15/0317/18/AKU9HSMB00294MP6.html";
    //对于一些有限制ip访问次数的网站，可以增加http代理防止被禁
    //.setHttpProxyPool(Lists.newArrayList(new String[]{"192.168.0.2", "8080"}, new String[]{"192.168.0.3", "8080"}))
    private Site site = Site
            .me()
            .setDomain("http://edu.163.com")
            .setSleepTime(3000)
            //用户代理，伪装多个浏览器访问，防止有些网站判断是否是在恶意刷
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {

        System.out.println(creatTime);

        long nowTime = System.currentTimeMillis();

        System.out.println(nowTime);

//        if(nowTime-creatTime>1000*20){
//            try {
//                Thread.sleep(1000*30);
//                creatTime=System.currentTimeMillis();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        //把当前页面符合的所有链接放入容器中进行遍历
        page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        //标题
        String title = page.getHtml().xpath("//div[@id='epContentLeft']/h1/text()").toString();
        //发布时间
        String time = page.getHtml().xpath("//div[@class='post_time_source']").regex("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)").toString();
        //作者
        String author =  page.getHtml().xpath("//span[@class='ep-editor']/text()").toString();
        //来源
        String from =  page.getHtml().xpath("//a[@id='ne_article_source']/text()").toString();
        //描述
        String desc = page.getHtml().xpath("//meta[@name='keywords']").replace("(\\w+|\"|=|<|>)", "").toString();
        //页面地址
        String url = page.getResultItems().getRequest().getUrl();
        //<(?!img|style|/style|script|/script).*?>|&(lt|gt|nbsp);
        //内容
        String content = page.getHtml().xpath("//div[@id='endText']").replace("<(?!img|style|/style|script|/script).*?>|&(lt|gt|nbsp);", "")
                .replace("<[^>]*?style[^>]*?>[\\s\\S]*?<\\/style>|<[^>]*?script[^>]*?>[\\s\\S]*?<\\/script>","").toString();
        //内容(图片占位符)
        String content_Placeholder = page.getHtml().xpath("//div[@id='endText']").replace("<(?!img|style|/style|script|/script).*?>|&(lt|gt|nbsp);", "")
                .replace("<[^>]*?style[^>]*?>[\\s\\S]*?<\\/style>|<[^>]*?script[^>]*?>[\\s\\S]*?<\\/script>","").replace("<([^>]*?img).*?>","{img}").toString();
        if (content==null||"".equals(content.trim())) {
            //如果内容为空，则跳过这个页面
            page.setSkip(true);
        } else {
            try {
                EDUNews eduNews = new EDUNews();
                eduNews.setDesc(desc.trim());
                eduNews.setAuthor(author);
                eduNews.setNewsfrom(from);
                eduNews.setContent(replaceImg(content_Placeholder).trim());
                eduNews.setUrl(url);
                eduNews.setTitle(title);
//                eduNews.setImglist(ImgMatcherUtil.match(content));
                eduNews.setTime(time == null ? null : new SimpleDateFormat("yyyy-MM-dd").parse(time));
                page.putField("edu",eduNews);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public Site getSite() {
        return site;
    }

    private String replaceImg(String string){
        int i = 0;
        while(true){
            String ss = string.replaceFirst("\\{img\\}", "\\{"+i+"\\}");
            if(string.equals(ss)){
                break;
            }
            string = ss;
            i++;
        }
        return string;
    }

}
