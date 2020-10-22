package com.zaumal.download.xmla;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zaumal.download.xmla.start.Run;
import com.zaumal.download.xmla.util.TranUtil;

@SpringBootApplication
public class XmlyApplication implements CommandLineRunner{
	private Logger logger = LoggerFactory.getLogger(XmlyApplication.class);
	
	public static void main(String[] args) {
		args = new String[] {"15957077","tmp"};//专辑id,保存位置
		SpringApplication app = new SpringApplication(XmlyApplication.class);
        app.run(args);
	}
	
	@Override
	public void run(String... args) throws InterruptedException{
		long start = System.currentTimeMillis();
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		Run run = new Run();
		Date startDate = new Date();
		String startStr = JSON.toJSONStringWithDateFormat(startDate,dateFormat,
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteNonStringValueAsString);
		logger.info("开始下载[{}]...",startStr.substring(1, startStr.length()-1));
		if(null != args && args.length >=2){
			// 专辑id
			String albumId = args[0];
			// 保存位置
			String savePath = args[1];
			
			Object notifyObj = new Object();
			List<File> result = new ArrayList<>();
			run.start(albumId,savePath,notifyObj,result);
			synchronized(notifyObj){
				notifyObj.wait();
			}
			long totalBytes = 0L;
			for (File file : result) {
				totalBytes += file.length();
			}
			logger.info("共下载{}",TranUtil.tranH(totalBytes));
		}else{
			logger.error("参数异常！必填参数说明：[参数1：专辑ID,参数2：存储位置]");
		}
		Date endDate = new Date();
		String endStr = JSON.toJSONStringWithDateFormat(endDate,dateFormat,SerializerFeature.WriteDateUseDateFormat);
		logger.info("下载完成[{}]，用时：{}",endStr.substring(1,endStr.length()-1),TranUtil.tranHTime(endDate.getTime() - start));
	}
}
