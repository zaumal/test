package com.zaumal.ting89.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

@Component
public class HttpUtil{
    @Autowired
    private FileUtil fileUtil;

    public static void main2(String[] args) throws Exception {
        String url = "http://play.ting89.com/down/down.php?url=http://mp33a.ting89.com:9090/经典纪实/丰乳肥臀/001.mp3";
        InputStream is = new URL(url).openStream();
        Document document = Jsoup.parse(is, "GBK",url);
        if(null != is) {
            is.close();
        }
        System.out.println(document.html());

        Element iframeBody = document.getElementsByTag("body").first();
        Element a = iframeBody.select("div.fl > a").first();
        String result = a.attr("href");

        result = new String(result.getBytes("GBK"),"UTF-8");

        System.out.println(result);
    }

    public Document request(String url,String fileName) throws IOException {
        File file = new File(fileUtil.getFilePathName(fileName));
        if(file.exists()){
            return Jsoup.parse(file, "UTF-8");
//            return Jsoup.parse(file, "GBK");
        }else{
            Document document = null;
            for(int i = 0; i < 10; i++){
                InputStream is = null;
                try {
                    is = new URL(url).openStream();
                    document = Jsoup.parse(is, "GBK", url);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(null != is) {
                        try {
                            is.close();
                        } catch (IOException e) {
                        }
                    }
                }
                if(null != document){
                    break;
                }

                Random random = new Random();
                try {
                    Thread.sleep(500*random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            fileUtil.crtFile(fileName,document.html());
            return document;
        }
    }
}