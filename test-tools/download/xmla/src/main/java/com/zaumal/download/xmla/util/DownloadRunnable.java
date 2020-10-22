package com.zaumal.download.xmla.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多线程下载执行类
 */
public class DownloadRunnable implements Runnable {
	private Logger logger = LoggerFactory.getLogger(DownloadRunnable.class);

	private volatile static int totleThreadCount = 0; 
	public volatile static int currThreadCount = 0;
	
	private String url;
	private String folderPath;
	private String filename;
	private int totalCount;
	
	private Object notifyObj;

	public DownloadRunnable(String url, String folderPath, String filename) {
		super();
		this.url = url;
		this.folderPath = folderPath;
		this.filename = filename;
	}
	public DownloadRunnable(String url, String folderPath, String filename,int totalCount,Object notifyObj) {
		super();
		this.url = url;
		this.folderPath = folderPath;
		this.filename = filename;
		this.totalCount = totalCount;
		this.notifyObj = notifyObj;
	}

	@Override
	public void run() {
		totleThreadCount++;
		synchronized (DownloadRunnable.class) {
			File folder = new File(folderPath);
			if (folder.exists() == false) {
				folder.mkdirs();
			}
			try {
				long fileLength = 0;
				int i = 0;
				do {
					if(i >= 6){
						break;
					}

					File file = new File(folderPath, filename);
					FileUtils.copyURLToFile(new URL(url), file);

					if((fileLength = file.length()) == 0){
						logger.info("第{}次重新下载，名称：{}，文件大小：{}",i,filename,TranUtil.tranH(file.length()));

						Random random = new Random();
						try {
							Thread.sleep(3000*random.nextInt(5));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					i++;
				}while (fileLength == 0);
				logger.info("已下载：{}，文件大小：{}，完成{}({})",
						filename,TranUtil.tranH(fileLength),(currThreadCount+1) + "/" + totalCount,String.format("%.2f%% ", (currThreadCount+1)*1.0/totalCount*100));
			} catch (IOException e) {
				logger.error("下载文件[" + filename + "]时发生异常",e,e.getMessage());
			}finally{
				currThreadCount++;
				if(null != notifyObj){
					if(totleThreadCount == currThreadCount){
						synchronized(notifyObj){
							notifyObj.notify();
						}
					}
				}
			}
		}
	}
}
