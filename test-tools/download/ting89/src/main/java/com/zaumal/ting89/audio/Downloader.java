package com.zaumal.ting89.audio;

import com.zaumal.ting89.utils.Aria2RPCHttp;
import com.zaumal.ting89.utils.HtmlUtil;
import com.zaumal.ting89.utils.HttpUtil;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Component
public class Downloader {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private HtmlUtil htmlUtil;
    @Autowired
    private AudioInfo audioInfo;
    @Autowired
    private Aria2RPCHttp aria2RPCHttp;

    private int begin;
    private int end;

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void download(String url) throws IOException {
        Document html = httpUtil.request(url,"index.html");
        List<AddrInfo> addrInfos = htmlUtil.parseAddr(html);

        int i = 0;
        for(AddrInfo a : addrInfos){
            i++;
            if(begin != end){
                if(begin > i){
                    continue;
                }
                if(end !=0 && end < i){
                    continue;
                }
            }

            Random random = new Random();
            if(i % (random.nextInt(3) + 1) == 0){
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String src = audioInfo.getHref(a.getTitle(),a.getHerf());

            //提交aria2
            boolean success = true;
            int j = 0;
            do{
                if(j >= 6){
                    throw new RuntimeException("提交到 aria2 时出现异常，请检查");
                }
                if(!(success = aria2RPCHttp.sendRpc(src))){
                    System.out.println("第" + j + "次重新提交 -》" + a.getTitle() + " : " + src);
                }
                j++;
            }while (!success);
            System.out.println("已提交 -》" + a.getTitle() + " : " + src);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
    }
}
