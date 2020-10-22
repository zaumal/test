package com.zaumal.ting89.audio;

import com.zaumal.ting89.utils.HtmlUtil;
import com.zaumal.ting89.utils.HttpUtil;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AudioInfo {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private HtmlUtil htmlUtil;

    public String getHref(String title,String uri) throws IOException {
        String pre = "http://www.ting89.com";
        Document html = httpUtil.request(pre + uri,title);
        return htmlUtil.parseAudio(html,"re" + title);
    }
}
