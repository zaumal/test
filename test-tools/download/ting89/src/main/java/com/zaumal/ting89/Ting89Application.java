package com.zaumal.ting89;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zaumal.ting89.audio.Downloader;
import com.zaumal.ting89.utils.Aria2RPCHttp;
import com.zaumal.ting89.utils.FileUtil;
import com.zaumal.ting89.utils.TranUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Date;

@SpringBootApplication
public class Ting89Application implements CommandLineRunner {
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private Aria2RPCHttp aria2RPCHttp;
    @Autowired
    private Downloader downloader;

    Logger logger = LoggerFactory.getLogger(Ting89Application.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Ting89Application.class);
        app.run(args);
    }

    /**
     *
     * @param args 1、bookId 2、aria2目录 3、从第几集（包括）开始，可以为空  4、到第几集（包括）结束，可以为空
     * @throws InterruptedException
     * @throws IOException
     */
    @Override
    public void run(String... args) throws InterruptedException, IOException {
        long start = System.currentTimeMillis();
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        Date startDate = new Date();
        String startStr = JSON.toJSONStringWithDateFormat(startDate,dateFormat,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNonStringValueAsString);
        logger.info("aria2 rpc");
        logger.info("开始提交 - [{}]...",startStr.substring(1, startStr.length()-1));

        String bookId = args[0];
        String dir = args[1];
        int begin = 0;
        if(args.length >= 3 && null != args[2] && args[2].length() > 0) {
            begin = Integer.parseInt(args[2]);
        }
        int end = 0;
        if(args.length >= 4 && null != args[3] && args[3].length() > 0) {
            end = Integer.parseInt(args[3]);
        }

        String path = "/media/AiCard_01/bddownload/" + dir;
        String rpc = "http://www.asecondself.com:6800/jsonrpc";
        String url = "http://www.ting89.com/books/" + bookId + ".html";

        fileUtil.setDir(dir);

        aria2RPCHttp.setDir(path);
        aria2RPCHttp.setRpc(rpc);

        downloader.setBegin(begin);
        downloader.setEnd(end);

        downloader.download(url);

        Date endDate = new Date();
        String endStr = JSON.toJSONStringWithDateFormat(endDate,dateFormat,SerializerFeature.WriteDateUseDateFormat);
        logger.info("提交完成 - [{}]，用时：{}",endStr.substring(1,endStr.length()-1), TranUtil.tranHTime(endDate.getTime() - start));
    }
}
