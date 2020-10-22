package com.zaumal.ting89.utils;

import com.zaumal.ting89.audio.AddrInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HtmlUtil {
    public List<AddrInfo> parseAddr(String html) throws UnsupportedEncodingException {
        return parseAddr(Jsoup.parse(html));
    }
    public List<AddrInfo> parseAddr(Document document) throws UnsupportedEncodingException {
        Element body = document.getElementsByTag("body").first();
        Elements wrappers = body.getElementsByClass("wrapper");
        Elements contentleft = wrappers.first().getElementsByClass("contentleft");
        Elements numlist = contentleft.first().getElementsByClass("numlist border");
        Elements compress = numlist.get(1).getElementsByClass("compress");
        Elements ul = compress.first().getElementsByTag("ul");

        List<AddrInfo> result = new ArrayList<>();

        for(Element li : ul.first().children()){
            Element a = li.getElementsByTag("a").first();
            String title = a.text();
            String href = a.attr("href");
            AddrInfo addrInfo = new AddrInfo();
            addrInfo.setTitle(title);
            addrInfo.setHerf(href);
            result.add(addrInfo);
        }
        return result;
    }

    public String parseAudio(String html,String fileName) throws IOException {
        return parseAudio(Jsoup.parse(html),fileName);
    }

    public String parseAudio(Document document,String fileName) throws IOException {
        Element body = document.getElementsByTag("body").first();
        Element iframe = body.getElementsByTag("iframe").first();
        String src = iframe.attr("src");
        return src.split("\\?url=")[1];
//        Document iframeHtml = httpUtil.request(src,fileName);
//        Element iframeBody = iframeHtml.getElementsByTag("body").first();
//        Element a = iframeBody.select("div.fl > a").first();
//        String url = new String(a.attr("href").getBytes("GBK"),"UTF-8");
//        return url = encode(url,"UTF-8");
    }


    public static void main(String[] args) throws IOException {
        String file = "D:\\prodata\\github\\download\\tmp\\三国演义\\袁阔成_三国演义_001";
        Document iframeHtml = Jsoup.parse(new File(file), "UTF-8");
        Element iframe = iframeHtml.getElementsByTag("iframe").first();
        String src = iframe.attr("src");
        System.out.println(src);
        src = src.split("\\?url=")[1];
        System.out.println(src);
        src = new String(src.getBytes("GBK"),"UTF-8");
        System.out.println(src);
    }

    public static String encode(String str, String charset)
            throws UnsupportedEncodingException {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
        Matcher m = p.matcher(str);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
        }
        m.appendTail(b);
        return b.toString();
    }
}
