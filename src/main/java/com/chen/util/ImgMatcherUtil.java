package com.chen.util;

import com.alibaba.fastjson.JSONArray;
import com.chen.bean.ImgParam;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ChenNing on 16/5/20.
 */
public class ImgMatcherUtil {

    public static String match(String context){

        if(context!=null&&!"".equals(context.trim())){

            Pattern pattern = Pattern.compile("src=\"(.*?)\"", Pattern.DOTALL);

            Matcher matcher = pattern.matcher(context);

            StringBuffer stringBuffer = new StringBuffer();

            File imageFile = null;

            HttpClient httpClient = null;

            //创建post请求
            HttpPost httppost = new HttpPost("http://ul.xueleyun.com/upload");

            while (matcher.find()){
                try {
                    //原图片路径
                    String img = matcher.group(1);
                    //图片后缀类型
                    String Suffix = img.substring(img.lastIndexOf("."));
                    //uuid图片名称
                    String imgName = UUID.randomUUID() + Suffix;
                    //new一个URL对象
                    URL url = new URL(img);
                    //打开链接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置请求方式为"GET"
                    conn.setRequestMethod("GET");
                    //超时响应时间为5秒
                    conn.setConnectTimeout(5 * 1000);
                    //通过输入流获取图片数据
                    InputStream inStream = conn.getInputStream();
                    //得到图片的二进制数据，以二进制封装得到数据，具有通用性
                    byte[] data = readInputStream(inStream);
                    //new一个文件对象用来保存图片，默认保存当前工程根目录
                    imageFile = new File(imgName);
                    //创建输出流
                    FileOutputStream outStream = new FileOutputStream(imageFile);
                    //写入数据
                    outStream.write(data);
                    //关闭输出流
                    outStream.close();

                    //上传到学乐文件系统
                    //创建httpclient对象
                    httpClient = HttpClients.createDefault();
                    //创建文件体
                    FileBody bin = new FileBody(imageFile);
                    //创建参数体
                    HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("Filedata", bin).build();
//                    MultipartEntity reqEntity = new MultipartEntity();
//                    reqEntity.addPart("Filedata", bin);
                    httppost.setEntity(reqEntity);
                    //执行
                    HttpResponse response = httpClient.execute(httppost);
                    //返回响应参数
                    HttpEntity resEntity = response.getEntity();

                    ImgParam imgParam = JSONArray.parseObject(EntityUtils.toString(resEntity), ImgParam.class);
                    //拼接文件系统图片地址
                    if (imgParam.isStatus()&&imgParam.getFileKey()!=null){
                        stringBuffer.append("http://dl.xueleyun.com/images/"+imgParam.getFileKey()+Suffix+ ",");
                    }
                }catch (Exception e){

                    e.printStackTrace();

                }finally {

                    //关闭http请求
                    httpClient.getConnectionManager().shutdown();
                    //删除本地文件
                    imageFile.delete();

                }

            }

            String s = stringBuffer.toString();

            if(s!=null&&!"".equals(s.trim())){

                return s.substring(0,s.length()-1);

            }else{

                return null;

            }
        }else{

            return null;

        }

    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    public static void main(String[] args) throws Exception{

//        String ss = ImgMatcherUtil.match(" \n" +
//                " <img alt=\"网易新闻客户端教育频道上线 随时随地看教育\" src=\"http://img2.cache.netease.com/edu/2015/3/17/20150317183043e3f3d_550.jpg\">\n" +
//                " 您用手机可以通过网易新闻客户端查看网易教育频道最新内容，更多精彩文章期待与您分享。以下是手机端的订阅方式：\n" +
//                " 1、安装网易新闻客户端\n" +
//                " <img alt=\"网易新闻客户端教育频道上线 随时随地看教育\" src=\"http://img1.cache.netease.com/edu/2015/3/17/2015031718360258625.jpg\">\n" +
//                " 打开首页\n" +
//                " 2、点击右上角下拉箭头，打开订阅页面。（红色手指图标处）\n" +
//                " <img alt=\"网易新闻客户端教育频道上线 随时随地看教育\" src=\"http://img4.cache.netease.com/edu/2015/3/17/2015031718372858816.jpg\">\n" +
//                " 3、在“点击添加更多栏目”里，点击添加“教育”频道，即完成订阅。可进一步拖动，调整排序，点击“完成”。\n" +
//                " <img alt=\"网易新闻客户端教育频道上线 随时随地看教育\" src=\"http://img4.cache.netease.com/edu/2015/3/17/2015031718375293a63.jpg\">\n" +
//                " 4、点击进入教育频道，查看最新资讯。\n" +
//                " <img alt=\"网易新闻客户端教育频道上线 随时随地看教育\" src=\"http://img4.cache.netease.com/edu/2015/3/17/2015031718394364e55.jpg\">\n" +
//                " \n" +
//                "   \n" +
//                "  \n" +
//                "    \n" +
//                " \n" +
//                " \n" +
//                "  \n" +
//                "  \n" +
//                "  <img src=\"http://img1.cache.netease.com/cnews/css13/img/end_edu.png\" alt=\"范文艳\" width=\"13\" height=\"12\" class=\"icon\"> 本文来源：网易教育频道综合  \n" +
//                "   \n" +
//                "  责任编辑：马志秋_NQ2478 ");
//        System.out.println(ss);
//        String s ="http://img2.cache.netease.com/edu/2015/3/17/20150317183043e3f3d_550.jpg";
////        String sss = s.substring(s.lastIndexOf("/")+1, s.lastIndexOf("."));
////        System.out.println(sss);
//
//        System.out.println(UUID.randomUUID()+s.substring(s.lastIndexOf(".")));
        HttpClient httpClient = null;
        File file = null;
        HttpPost httppost = new HttpPost("http://ul.xueleyun.com/upload");
            try {
                httpClient = HttpClients.createDefault();
                file = new File("6ea4b925-f29e-4671-904c-7196f5d0b193.jpg");
                FileBody bin = new FileBody(file);
                HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("Filedata", bin).build();
                httppost.setEntity(reqEntity);
                HttpResponse response = httpClient.execute(httppost);
                HttpEntity resEntity = response.getEntity();
                ImgParam imgParam = JSONArray.parseObject(EntityUtils.toString(resEntity), ImgParam.class);
                System.out.println(imgParam.toString());

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                httpClient.getConnectionManager().shutdown();
                file.delete();
            }
    }

}
