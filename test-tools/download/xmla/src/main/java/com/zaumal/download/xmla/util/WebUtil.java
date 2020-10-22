package com.zaumal.download.xmla.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUtil {
	private final static Logger logger = LoggerFactory.getLogger(WebUtil.class);
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("xm-sign", genXmSign());
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			logger.debug("get:{};return:{}",url,result);
		} catch (Exception e) {
			logger.error("发送get请求时发生异常[{}]",url,e,e.getMessage());
		} finally {
			close(in);
		}
		return result;
	}

	public static String sendPost(String url) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("xm-sign", genXmSign());
			connection.setDoOutput(true);
			connection.setDoInput(true);
			out = new PrintWriter(connection.getOutputStream());
			out.flush();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			logger.debug("post:{};return:{}",url,result);
		} catch (Exception e) {
			logger.error("发送post请求时发生异常[{}]",url,e,e.getMessage());
		} finally {
			close(out);
			close(in);
		}
		return result;
	}

	public static String genXmSign(){
		String result = "";

		//获取服务器时间戳
		String url = "https://www.ximalaya.com/revision/time";
		
		String time = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				time += line;
			}
		} catch (Exception e) {
			logger.error("获取xm-sgin服务器时间戳时发生异常",e,e.getMessage());
		} finally {
			close(in);
		}
		
		//生成xm-sign
		String xm_sign = "js/xmSign.js";
        try{
        	FileReader js = new FileReader(xm_sign);
    		ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            engine.eval(js);
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                result = (String)invocable.invokeFunction("python",time);
            }
        }catch(Exception e){
        	logger.error("执行xmSign.js获取xm-sgin时发生异常",e,e.getMessage());
        }
        logger.debug("sm-sign:{}",result);
        return result;
	}
	
	public static void close(BufferedReader bufferedReader){
		if(null != bufferedReader){
			try {
				bufferedReader.close();
			} catch (IOException e) {
				logger.error("关闭BufferedReader时发生异常",e,e.getMessage());
			}
		}
	}
	
	public static void close(PrintWriter printWriter){
		if(null != printWriter){
			printWriter.close();
		}
	}
}
