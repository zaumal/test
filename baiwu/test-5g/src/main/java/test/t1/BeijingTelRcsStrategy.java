package test.t1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.MultipartBody;
import okhttp3.Response;
import test.TestBjdx;
import util.DateUtil;
import util.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BeijingTelRcsStrategy extends TestBjdx implements IRcsMsgStrategy {
    public BeijingTelRcsStrategy(String chatbotId, String appid, String appkey) {
		super(chatbotId, appid, appkey);
	}
	private final Logger log = LoggerFactory.getLogger(BeijingTelRcsStrategy.class);
    @Resource
    private OkHttpUtil okHttpUtil = new OkHttpUtil();
    @Resource
//    private RedisCache redisCache;
    
    
    
    String token = null; //"accessToken eFNudUgxMk06MWEzMjk0ZGJlMzA5NDg4YzQ5YzA1YzNjMjU3NjViMTM1MjM2ZjZiYmRjNmRlMGIzZTNmNzcyODdjM2JjYzk5NQ==";;
    public static void main(String[] args) {
    	JSONObject sendChannelInfo = new JSONObject();
    	sendChannelInfo.put("fileServerRoot","https://maaptest.189.cn/maap_message");
    	sendChannelInfo.put("serverRoot","https://maaptest.189.cn/maap_message");
//    	sendChannelInfo.put("serverRoot","http://localhost:18866/maap_message");
    	sendChannelInfo.put("apiVersion","v1");
    	sendChannelInfo.put("chatbotId","106598858810000006");
    	sendChannelInfo.put("appId","xSnuH12M");
    	sendChannelInfo.put("appSecret","aa6420f8dd9a812665b0e06dc36e0ebcd3614b24");
    	
    	BeijingTelRcsStrategy t = new BeijingTelRcsStrategy("","","");
    	
    	System.out.println("下发文本消息：");
    	Map<String,Object> map = t.getText("你好chatbot,下行文本消息");
    	t.sendRcsMsg(sendChannelInfo,Arrays.asList("15330759941"),JSON.toJSONString(Arrays.asList(map)));
    	
//    	System.out.println("上传文件：");
//    	JSONObject params = new JSONObject();
//    	params.put("uploadMode", "perm");
//    	File thumbnail = new File("D:\\zal\\test\\file\\car-thumbnail.jpg");
//    	File file = new File("D:\\zal\\test\\file\\car.png");
//    	t.uploadMedia(sendChannelInfo, thumbnail, file, params);
    }
    
    @Override
    public JSONObject sendRcsMsg(JSONObject sendChannelInfo, List<String> list, String content) {
        TelRcsApiManager manager = initManager(sendChannelInfo);
        String contributionId = UUID.randomUUID().toString();
        String conversationId = UUID.randomUUID().toString();
        String url = manager.interSendUrl();
        String body = manager.assemblyBody(content, sendChannelInfo, list, contributionId, conversationId);
        Map<String, String> headerMap = manager.sendHeads();
        Response response = okHttpUtil.postJsonParamsWithHeaders(url, body, headerMap);
        try {
            return getJsonObject(response);
        } catch (IOException e) {
            log.error("转换异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject sendInterMsg(JSONObject sendChannelInfo, String phone, String content, String inReplyTo) {
        TelRcsApiManager manager = initManager(sendChannelInfo);
        String contributionId = UUID.randomUUID().toString();
        String conversationId = UUID.randomUUID().toString();
        String url = manager.interSendUrl();
        String body = manager.assemblyInteractionBody(content, sendChannelInfo, phone, inReplyTo, contributionId, conversationId);
        Map<String, String> headerMap = manager.sendHeads();
        Response response = okHttpUtil.postJsonParamsWithHeaders(url, body, headerMap);
        try {
            return getJsonObject(response);
        } catch (IOException e) {
            log.error("转换异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject revokeMsg(JSONObject sendChannelInfo, String messageId, String phone) {
        TelRcsApiManager manager = initManager(sendChannelInfo);
        String url = manager.revokeUrl();
        String body = manager.revokeBody(messageId, phone);
        Map<String, String> headerMap = manager.revokeHeaders(phone);
        Response response = okHttpUtil.postJsonParamsWithHeaders(url, body, headerMap);
        try {
            return getJsonObject(response);
        } catch (IOException e) {
            log.error("转换异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject getJsonObject(Response response) throws IOException {
    	System.out.println("reponse: " + response);
    	String result = response.body().string();
    	System.out.println("response body: " + result);
        return JSONObject.parseObject(result.replace("errorCode", "code")
                        .replace("errorMessage", "msg"));
    }

    /**
     * @param uploadMode perm:永久文件 temp：临时文件
     * @return
     */
    @Override
    public JSONObject uploadMedia(JSONObject sendChannelInfo, File thumbnail, File file, JSONObject params) {
        TelRcsApiManager manager = initManager(sendChannelInfo);
        String url = manager.uploadUrl();
        Map<String, String> headerMap = manager.uploadHeads(params.getString("uploadMode"));
        MultipartBody thumbnailMultipartBody = okHttpUtil.multipartBody(null, null, "thumbnail", thumbnail);
        MultipartBody fileMultipartBody = okHttpUtil.multipartBody(null, null, "file", file);
        url = "http:/localhost:7085/rcs/bjdx/bot/v1/106598858810000006/medias/upload";
        Response thumbnailResponse = okHttpUtil.postMultipartBody(url, thumbnailMultipartBody, headerMap);
        Response fileResponse = okHttpUtil.postMultipartBody(url, fileMultipartBody, headerMap);
        JSONObject thumbnailResult = null;
        JSONObject fileResult = null;
        JSONObject result = new JSONObject();
        try {
        	System.out.println("thumbnailResponse: " + thumbnailResponse);
         	String thumbnailResponseResult = thumbnailResponse.body().string();
         	System.out.println("response body: " + thumbnailResponseResult);
         	
         	System.out.println("fileResponse: " + fileResponse);
        	String fileResponseResult = fileResponse.body().string();
        	System.out.println("fileResponse body: " + fileResponseResult);
        	
            thumbnailResult = JSONObject.parseObject(thumbnailResponseResult);
            fileResult = JSONObject.parseObject(fileResponseResult);
            if (Constants.SUCCESS.equals(fileResult.getString("errorCode"))) {
                JSONObject thumbnailObject = thumbnailResult.getJSONArray("fileInfo").getJSONObject(0);
                JSONObject fileObject = fileResult.getJSONArray("fileInfo").getJSONObject(0);
                JSONObject data = new JSONObject();
                data.put("thumbnail", thumbnailObject);
                data.put("file", fileObject);
                result.put("code", Constants.SUCCESS);
                result.put("msg", "success");
                result.put("data", data);
            } else {
                result.put("code", fileResult.getString("errorCode"));
                result.put("msg", "对应接口文档的code");
            }
        } catch (IOException e) {
            log.error("转换异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject deleteMedia(JSONObject sendChannelInfo, JSONObject params) {
        TelRcsApiManager manager = initManager(sendChannelInfo);
        String url = manager.deleteMediaUrl();
        String fileUrl = params.getString("fileUrl");
        String host = params.getString("host");
        String date = params.getString("date");
        Map<String, String> headerMap = manager.deleteMediaHeads(fileUrl, host, date);
        Response response = okHttpUtil.delete(url, headerMap);
        try {
            return getJsonObject(response);
        } catch (IOException e) {
            log.error("转换异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    private TelRcsApiManager initManager(JSONObject sendChannelInfo) {
        TelRcsApiManager manager = new TelRcsApiManager(sendChannelInfo);
        String appId = manager.getAppId();
        String url = manager.tokenUrl();
        String appSecret = manager.getAppSecret();
        String key = RedisConstants.CHAT_BOT_TOKEN_KEY.concat(appId);
        String token = this.token;
        if (StringUtils.isEmpty(token)) {
            try {
            	String body = manager.tokenBody();
            	Map<String, String> headerMap = new HashMap<>();
//            	headerMap.put("Content-Type", "application/json");
                Response response = okHttpUtil.postJsonParamsWithHeaders(url, body, headerMap);
                
                String result = response.body().string();
                System.out.println(result);
                
                token = JSONObject.parseObject(result).getString("accessToken");
//                redisCache.setCacheObject(key, token, 7000, TimeUnit.SECONDS);
            } catch (Exception e) {
            	e.printStackTrace();
                log.error("获取token失败，url:{},appId:{},appKey:{}", url, appId, appSecret);
            }
        }
        String authorization = token;//"accessToken".concat(" ").concat(token);
        manager.setAuthorization(authorization);
        return manager;
    }
}
