package test.t1;

import okhttp3.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
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

public class OkHttpUtilOffline {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtilOffline.class);

    public static RestTemplate restTemplate() {
        OkHttpClient httpClient = okHttpClient();
        ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        // 可以增加拦截器
        //restTemplate.setInterceptors(...);
        // 可以添加消息转换
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    public static OkHttpClient okHttpClient() {
        TrustAllCerts trustManager = new TrustAllCerts();
        return new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory(), trustManager)
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .retryOnConnectionFailure(false)
                .connectionPool(new ConnectionPool(200, 10, TimeUnit.MINUTES))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();
    }


    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    private static class TrustAllCerts implements X509TrustManager {
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

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
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
    public static StringBuffer getQueryString(String url, Map<String, String> queries) {
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
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static Response get(String url, Map<String, String> queries) {
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
    public static Response postFormParams(String url, Map<String, String> params) {
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
    public static Response postJsonParams(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return execNewCall(request);
    }

    public static String postJsonParamsReturnBody(String url, String jsonParams) {
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
    public static Response postXmlParams(String url, String xml, Map<String, String> headerMap) {
        RequestBody requestBody = RequestBody.create(xml, MediaType.parse("application/xml; charset=utf-8"));
        Request.Builder builder = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((name, value) -> builder.addHeader(name, value));
        }
        Request request = builder.post(requestBody).build();
        return execNewCall(request);
    }

    public static Response postMultipartBody(String url, RequestBody multipartBody, Map<String, String> headerMap) {
        Request.Builder builder = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((name, value) -> builder.addHeader(name, value));
        }
        Request request = builder.post(multipartBody).build();
        return execNewCall(request);
    }

    public static MultipartBody multipartBody(String thumbnailPartName, File thumbnail, String partName, File file) {
        MediaType mediaType = MediaType.Companion.parse("text/x-markdown; charset=utf-8");
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (thumbnail != null) {
            builder.addFormDataPart(thumbnailPartName, thumbnail.getName(), RequestBody.Companion.create(thumbnail, mediaType));
        }
        builder.addFormDataPart(partName, file.getName(), RequestBody.Companion.create(file, mediaType));
        return builder.build();
    }

    public static Response delete(String url, Map<String, String> headerMap) {
        Request.Builder builder = new Request.Builder().url(url);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((name, value) -> builder.addHeader(name, value));
        }
        Request request = builder.delete().build();
        return execNewCall(request);
    }

    public static Response execNewCall(Request request) {
        Response response = null;
        try {
            response = OkHttpUtilOffline.okHttpClient().newCall(request).execute();
            return response;
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }
}
