package test.t1;

import okhttp3.*;
import okhttp3.Request.Builder;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 **/
@Service
public class OkHttpUtil {
    private final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
    		.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
    		.hostnameVerifier(new TrustAllHostnameVerifier())
    		.retryOnConnectionFailure(false)
    		.connectionPool(new ConnectionPool(200, 10, TimeUnit.MINUTES))
    		.connectTimeout(30, TimeUnit.SECONDS)
    		.readTimeout(300, TimeUnit.SECONDS)
    		.writeTimeout(300, TimeUnit.SECONDS)
    		.build();
    
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

    /**
     * 根据map获取get请求参数
     *
     * @param queries
     * @return
     */
    public StringBuffer getQueryString(String url, Map<String, String> queries) {
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        return sb;
    }

    /**
     * 调用okhttp的newCall方法
     *
     * @param request
     * @return
     */
    private Response execNewCall(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return response;
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public Response get(String url, Map<String, String> queries) {
        StringBuffer sb = getQueryString(url, queries);
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();
        return execNewCall(request);
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public Response postFormParams(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder(Charset.forName("UTF-8"));
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        return execNewCall(request);
    }


    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    public Response postJsonParams(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
//        RequestBody requestBody = RequestBody.create( MediaType.parse("application/json; charset=utf-8"),jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return execNewCall(request);
    }

    public Response postJsonParamsWithHeaders(String url, String jsonParams, Map<String, String> headerMap) {
    	System.out.println("request url: " + url);
    	System.out.print("request header: {");
    	headerMap.forEach((x,y) -> {System.out.print(x + ":" + y + ",");});
    	System.out.println("}");
    	System.out.println("request body: " + jsonParams);
        RequestBody requestBody = RequestBody.create(jsonParams.getBytes(StandardCharsets.UTF_8),MediaType.get("application/json"));
        
        Builder builder = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((name, value) -> builder.addHeader(name, value));
        }
        Request request = builder.post(requestBody).build();
        return execNewCall(request);
    }

    public String postJsonParamsReturnBody(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = execNewCall(request);
        try {
            return response.body().string();
        } catch (IOException e) {
            logger.error("response to string error：{}", response);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Post请求发送xml数据....
     * 参数一：请求Url
     * 参数二：请求的xmlString
     * 参数三：请求回调
     */
    public Response postXmlParams(String url, String xml, Map<String, String> headerMap) {
        RequestBody requestBody = RequestBody.create(xml, MediaType.parse("application/xml; charset=utf-8"));
        Builder builder = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((name, value) -> builder.addHeader(name, value));
        }
        Request request = builder.post(requestBody).build();
        return execNewCall(request);
    }

    public Response postMultipartBody(String url, RequestBody multipartBody, Map<String, String> headerMap) {
    	System.out.println("request url: " + url);
    	System.out.print("request header: {");
    	headerMap.forEach((x,y) -> {System.out.print(x + ":" + y + ",");});
    	System.out.println("}");
    	
        Builder builder = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((name, value) -> builder.addHeader(name, value));
        }
        Request request = builder.post(multipartBody).build();
        return execNewCall(request);
    }

    public MultipartBody multipartBody(String thumbnailPartName, File thumbnail, String partName, File file) {
        MediaType mediaType = MediaType.Companion.parse("multipart/form-data");
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (thumbnail != null) {
            builder.addFormDataPart(thumbnailPartName, thumbnail.getName(), RequestBody.Companion.create(thumbnail, mediaType));
        }
        builder.addFormDataPart(partName, file.getName(), RequestBody.Companion.create(file, mediaType));
        return builder.build();
    }

    public Response delete(String url, Map<String, String> headerMap) {
        Builder builder = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((name, value) -> builder.addHeader(name, value));
        }
        Request request = builder.delete().build();
        return execNewCall(request);
    }
}