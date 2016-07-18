package com.chen.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenNing on 16/6/1.
 */
public class WordsFactory {

    static List<String> list = new ArrayList<String>();

    public static List<String> getWords()  {

        try {
            if (list != null && list.size() > 0) {
                return list;
            } else {
                    File file = new File("/Users/ChenNing/Downloads/en.txt");
                    if (file.isFile() && file.exists()) { //判断文件是否存在
                        InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                        BufferedReader bufferedReader = new BufferedReader(read);
                        String lineTxt = null;
                        while ((lineTxt = bufferedReader.readLine()) != null) {
                            list.add("http://www.dacidian.net/en/" + lineTxt.replace(" ", "+") + ".html");
                        }
                        read.close();
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
