package test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.CloseUtil;
import util.DateUtil;
import util.SHA256Util;

public abstract class AbstractDemo {
	protected final static MediaType mediaTypeStr = MediaType.parse("application/xml");
	protected final static MediaType MULTIPART_FORM_DATA = MediaType.parse("multipart/form-data");
	
	protected String sendUrl;
	protected String uploadUrl;
	protected String downloadUrl;
	protected String deleteUrl;
	protected String revokeUrl;
	protected String notifyUrl;
	
	protected String chatbotId;
	protected String appId;
	protected String password;
	
	protected String phone;
	
	protected String headerDate;
	protected String authorization;
	protected OkHttpClient client;

	public AbstractDemo() {}
	@SuppressWarnings("deprecation")
	public AbstractDemo(String chatbotId,String appid,String password) {
		this.chatbotId = chatbotId;
		this.appId = appid;
		this.password = password;
		
		this.headerDate = DateUtil.getHttpHeaderDate();
		System.out.println("headerDate : " + headerDate);
		this.authorization = tokenHeader(appid, password);
		System.out.println("authorization : " + authorization);
		System.out.println("======================================================================");
		
		this.client = new OkHttpClient().newBuilder().sslSocketFactory(createSSLSocketFactory()).hostnameVerifier(new TrustAllHostnameVerifier()).build();
	}
	
	String deleteFile(String data) {
		System.out.println("删除文件：");
		
		String url = deleteUrl;
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(url);
		System.out.println("tid : " + data);
		return request(data,x -> {
			return new Request.Builder()
					.url(url)
					.addHeader("Terminal-type", "Chatbot")
					.addHeader("User-Agent", "SP/"+getchatbotSip())
					.addHeader("Authorization", authorization)
					.addHeader("Date", headerDate)
					.addHeader("tid", x)
					.delete()
					.build();
		}); 
	}
	
	void downloadFile(String fileUrl) {
		System.out.println("下载文件：");
		
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(fileUrl);
		
		Request request = new Request.Builder()
					.url(fileUrl)
					.addHeader("Authorization", authorization)
					.addHeader("Date", headerDate)
					.addHeader("X-3GPP-Intended-Identity", getchatbotSip())
					.addHeader("Terminal-type","Chatbot")
					.get()
					.build();
		try {
			Response response = client.newCall(request).execute();
			
			System.out.println("response ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			System.out.println(response);
			System.out.println();
			
			InputStream is = null;
			try {
				String filename = UUID.randomUUID().toString();
				String contentType = response.header("Content-Type");
				if(!StringUtils.isEmpty(contentType)) {
					String[] cta = contentType.split("/");
					if(cta.length == 2) {
						filename += "." + cta[1];
					}
				}
				
				is = response.body().byteStream();
				
				download(is, filename);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseUtil.close(is);
			}
			System.out.println("=====================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void downloadFile2(String fileUrl) {
		System.out.println("下载文件：");
		
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(fileUrl);
		
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);// 设置超时
		requestFactory.setReadTimeout(10000);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		InputStream is = null;
        OutputStream os = null;
    	try {
    		ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl, HttpMethod.GET, getRequest(null), byte[].class);
    		
    		System.out.println("response ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
    		System.out.println(response);
			System.out.println();
    		
    		String filename = getFileName(response.getHeaders());
    		
    		if(null != filename) {
    			is = new ByteArrayInputStream(response.getBody());
	        	
	        	String filePath = "tmp/" + filename;
	        	
	        	File file = new File(filePath);
	        	
	        	os = new FileOutputStream(file);
	        	
	        	int len = 0;
	        	byte[] buf = new byte[1024];
	        	while((len = is.read(buf,0,1024)) != -1) {
	        		os.write(buf,0,len);
	        	}
	        	os.flush();
	        	
	        	System.out.println("下载成功：" + filePath);
    		}else {
    			System.out.println("下载失败：" + new String(response.getBody()));
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
		}finally {
			CloseUtil.close(is);
			CloseUtil.close(os);
		}
	}
	
	protected HttpEntity<HttpHeaders> getRequest(String param) {
		String chatbotURI = getchatbotSip();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Terminal-type", "Chatbot");
        headers.add("X-3GPP-Intended-Identity", chatbotURI);
        headers.add("User-Agent", "SP/" + chatbotURI);
        headers.add("Date", headerDate);
        headers.add("Authorization", authorization);
        headers.add("Connection","close");
        return new HttpEntity<>(headers);
	}
	
	String getFileName(HttpHeaders responseHeaders) {
		List<String> contentDisposition = responseHeaders.get("Content-disposition");
		if(null != contentDisposition && contentDisposition.size() > 0) {
			String content = contentDisposition.get(0);
			if(null != content) {
				String[] attachment = content.split(";");
				if(attachment.length == 2) {
					String[] filename = attachment[1].split("=");
					if(filename.length == 2) {
						return filename[1];
					}
				}
			}
		}
		return null;
	}
	
	void download(InputStream is,String filename) {
		OutputStream os = null;
		try {
			String filePath = "tmp" + File.separator + filename;
			
			File file = new File(filePath);
			
			os = new FileOutputStream(file);
			
			int len = 0;
			byte[] buff = new byte[1024];
			while((len = is.read(buff,0,1024)) != -1) {
				os.write(buff,0,len);
			}
			os.flush();
			System.out.println("filePath : " + filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(os);
		}
	}
	
	String getFileName(String header) {
		String[] attachment = header.split(";");
		if(attachment.length == 2) {
			String[] filename = attachment[1].split("=");
			if(filename.length == 2) {
				return filename[1];
			}
		}
		return null;
	}
	
	void revokeMessage(String messageId) {
		System.out.println("撤回消息：");
		
		String url = revokeUrl.replace("messageId", messageId);
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(url);
		String xml = getRevokeXml();
		System.out.println("xml : ");
		System.out.println(xml);
		request(xml,x -> {
			RequestBody body = RequestBody.create(x, mediaTypeStr);
			return new Request.Builder()
					.url(url)
					.addHeader("Address", "+86" + phone)
					.addHeader("Authorization", authorization)
					.addHeader("Date", headerDate)
					.addHeader("Content-Type", "application/xml")
					.put(body)
					.build();
		});
	}
	
	
	String getRevokeXml() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<msg:messageStatusReport xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				"<status>RevokeRequested</status>\r\n" + 
				"<address>tel:+86" + phone + "</address>\r\n" + 
				"</msg:messageStatusReport>";
		return xml;
	}
	
	String sendMessage(String data) {
		String url = sendUrl;
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(url);
		System.out.println("xml : ");
		data = data.replace("repace-rcsBody-replace", "");
		System.out.println(data);
		System.out.println();
		String message = request(data,x -> {
				RequestBody body = RequestBody.create(x, mediaTypeStr);
				return new Request.Builder()
						.url(url)
						.addHeader("Address", "+86" + phone)
						.addHeader("Authorization", authorization)
						.addHeader("Date", headerDate)
						.addHeader("Content-Type", "application/xml")
						.post(body)
						.build();
		});
		
		writeFile("messageIds",readMessageId(message));
		
		return message;
	}
	
	void uploadFile(String thumbnailPath,String thumbnailType,String filePath,String fileType) {
		String url = uploadUrl;
		String tid = UUID.randomUUID().toString();
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(url);
		System.out.println("tid : " + tid);
		System.out.println("thumbnail : " + thumbnailPath);
		System.out.println("file : " + filePath);
		System.out.println("notifyUrl : " + notifyUrl);
		System.out.println();
		
		MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		
		if(null != thumbnailPath && thumbnailPath.length() > 0) {
			File thumbnail = new File(thumbnailPath);
	        builder.addFormDataPart("Thumbnail", thumbnail.getName(), RequestBody.create(thumbnail,MediaType.parse("*/*")));
		}
        File file = new File(filePath);
        builder.addFormDataPart("File", file.getName(), RequestBody.create(file,MediaType.parse("*/*")));
        
        MultipartBody body = builder.build();
        
		String response = request(null,x -> {
				return new Request.Builder()
						.url(url)
						.addHeader("Content-Type", "multipart/form-data")
//						.addHeader("NotifyUrl", notifyUrl)
						.addHeader("Terminal-type","Chatbot")
						.addHeader("Authorization", authorization)
						.addHeader("Date", headerDate)
						.addHeader("X-3GPP-Intended-Identity", getchatbotSip())
						.addHeader("tid", tid)
						.post(body)
						.build();
		});
		
		writeFile("uploadFiles","\r\ntid : " + tid + "\r\n" + response);
	}
	
	protected String request(String data,Function<String,Request> function) {
		Request request = function.apply(data);
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(request);
		System.out.println();
//		System.out.println("request body ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
//		System.out.println(request.body());
//		System.out.println();
		System.out.println("======================================================================");
		return request(request);
	}
	
	String request(Request request) {
		try {
			Response response = client.newCall(request).execute();
			System.out.println("response ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			System.out.println(response);
			System.out.println();
			
			System.out.println("response header  ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			Headers headers = response.headers();
			for(int i = 0; i < headers.size(); i++) {
				String name = headers.name(i);
				String value = headers.get(name);
				
				System.out.println(name + " : " + value);
				
			}
			System.out.println();
			
			System.out.println("response body ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			String message = response.body().string();
			System.out.println(message);
			System.out.println();
			System.out.println("=====================================");
			return message;
		} catch (IOException e) {
			e.printStackTrace();
			try {
				System.out.println(new String(e.getMessage().getBytes("GBK"),"UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	String getDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		return df.format(new Date());
	}

	private SSLSocketFactory createSSLSocketFactory() {
		SSLSocketFactory ssfFactory = null;

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

			ssfFactory = sc.getSocketFactory();
		} catch (Exception e) {
		}

		return ssfFactory;
	}

	private class TrustAllCerts implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}

	private class TrustAllHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	protected String tokenHeader(String appId, String password) {
		String tokenHeader = null;
		String token = SHA256Util.encode(password);
		String text = appId.concat(":").concat(SHA256Util.encode(token + headerDate));
		try {
			String base64Str = Base64.getEncoder().encodeToString(text.getBytes());
			tokenHeader = "Basic ".concat(base64Str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenHeader;
	}
	
	protected String getchatbotSip() {
		return "sip:" + getchatbotUri();
	}
	
	protected String getchatbotUri() {
		return chatbotId + "@botplatform.rcs.chinamobile.com";
	}
	protected String getXml(String contentType,String bodyText) {return null;};
	
	protected String getMessageId(String xml) {
        try {
        	Document doc = DocumentHelper.parseText(xml);
        	Element root = doc.getRootElement();
            Element outboundIMMessage = root.element("outboundIMMessage");
            if(null != outboundIMMessage) {
	            Element messageIdEle = outboundIMMessage.element("messageId");
	            if(null != messageIdEle) {
	            	return messageIdEle.getText();
	            }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	void writeFile(String filename,String text) {
		File file = new File("file/" + filename);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
        	fos = new FileOutputStream(file,true);
        	osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(DateUtil.getDateTime() + " : " + text); //写入内容
            osw.write("\r\n");  //换行
        }catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(osw);
			CloseUtil.close(fos);
		}
	}
	
	 String readMessageId(String xml) {
		try {
        	Document doc = DocumentHelper.parseText(xml);
        	Element root = doc.getRootElement();
        	Element outboundIMMessage = root.element("outboundIMMessage");
        	if(null != outboundIMMessage) {
        		Element messageId = outboundIMMessage.element("messageId");
            	if(null != messageId) {
            		return messageId.getText();
            	}
        	}
        } catch (DocumentException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	FileInfo readXml(String xml) {
		FileInfo fileInfo = new FileInfo();
        try {
        	Document doc = DocumentHelper.parseText(xml);
        	Element root = doc.getRootElement();
        	
        	List<Element> files = root.elements("file-info");
        	
        	for(int i = 0; i < files.size(); i ++) {
        		Element element = files.get(i);
        		
        		if("thumbnail".equals(element.attributeValue("type"))){
        			Element size = element.element("file-size");
        			if(null != size) {
        				fileInfo.thumbnailFileSize = size.getText();
        			}
        			Element type = element.element("content-type");
        			if(null != type) {
        				fileInfo.thumbnailFileType = type.getText();
        			}
        			Element name = element.element("file-name");
        			if(null != name) {
        				fileInfo.thumbnailFileName = name.getText();
        			}
        			Element data = element.element("data");
        			if(null != data) {
        				Attribute url = data.attribute("url");
        				if(null != url) {
        					fileInfo.thumbnailFileUrl = url.getValue();
        				}
        			}
        		}else if("file".equals(element.attributeValue("type"))){
        			Element size = element.element("file-size");
        			if(null != size) {
        				fileInfo.fileSize = size.getText();
        			}
        			Element type = element.element("content-type");
        			if(null != type) {
        				fileInfo.fileType = type.getText();
        			}
        			Element name = element.element("file-name");
        			if(null != name) {
        				fileInfo.fileName = name.getText();
        			}
        			Element data = element.element("data");
        			if(null != data) {
        				Attribute url = data.attribute("url");
        				if(null != url) {
        					fileInfo.fileUrl = url.getValue();
        				}
        			}
        		}
        	}
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return fileInfo;
	}
}

class FileInfo{
	String fileName;
	String fileSize;
	String fileType;
	String fileUrl;
	
	String thumbnailFileName;
	String thumbnailFileSize;
	String thumbnailFileType;
	String thumbnailFileUrl;
}
