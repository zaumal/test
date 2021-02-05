package test.t1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TelRcsApiManager {
    private static final Logger log = LoggerFactory.getLogger(TelRcsApiManager.class);
    private static final String slash = "/";
    private String fileServerRoot;
    private String serverRoot;
    private String appId;
    private String appSecret;
    private String apiVersion;
    private String chatbotId;
    private String chatbotURI;
    private String authorization;

    public TelRcsApiManager(JSONObject sendChannelInfo) {
        String fileServerRoot = sendChannelInfo.getString("fileServerRoot");
        String serverRoot = sendChannelInfo.getString("serverRoot");
        String apiVersion = sendChannelInfo.getString("apiVersion");
        String appId = sendChannelInfo.getString("appId");
        String appSecret = sendChannelInfo.getString("appSecret");
        String chatbotId = sendChannelInfo.getString("chatbotId");
        this.fileServerRoot = fileServerRoot;
        this.serverRoot = serverRoot;
        this.appId = appId;
        this.appSecret = appSecret;
        this.apiVersion = apiVersion;
        this.chatbotId = chatbotId;
        this.chatbotURI = TokenUtil.getTelChatbotURI(chatbotId);
    }

    public String tokenUrl() {
        return serverRoot + slash + "bot" + slash + apiVersion
                + slash + chatbotURI + slash + "accessToken";
    }

    //https://{serverRoot}/bot/{apiVersion}/{chatbotId}/medias/upload
    public String uploadUrl() {
        return serverRoot + slash + "bot" + slash + apiVersion
                + slash + chatbotId + slash + "medias" + slash + "upload";
    }

    public String deleteMediaUrl() {
        return serverRoot + slash + "bot" + slash + apiVersion
                + slash + chatbotId + slash + "medias" + slash + "delete";
    }

//    public String groupSendUrl() {
//        return serverRoot + slash + "messaging" + slash + "group" + slash + apiVersion
//                + slash + "outbound" + slash + chatbotURI + slash + "requests";
//    }

    //https://{serverRoot}/bot/{apiVersion}/{chatbotId}/messages
    public String interSendUrl() {
        return serverRoot + slash + "bot" + slash + apiVersion
                + slash + chatbotURI + slash + "messages";
    }

    //https://{serverRoot}/bot/{apiVersion}/{chatbotId}/revoke
    public String revokeUrl() {
        return serverRoot + slash + "bot" + slash + apiVersion
                + slash + chatbotId + slash + "revoke";
    }

    public Map<String, String> uploadHeads(String uploadMode) {
        Map<String, String> headers = this.authHeaders();
        headers.put("content-type", "multipart/form-data");
        headers.put("uploadMode", uploadMode);
        headers.put("Authorization", authorization);
        return headers;
    }

    public Map<String, String> deleteMediaHeads(String fileUrl, String host, String date) {
        Map<String, String> headers = this.authHeaders();
        headers.put("url", fileUrl);
        headers.put("host", host);
        headers.put("date", date);
        return headers;
    }

    public Map<String, String> sendHeads() {
        return authHeaders();
    }

    public Map<String, String> revokeHeaders(String phone) {
        Map<String, String> headers = this.authHeaders();
        headers.put("content-type", "application/json");
        return headers;
    }

    @NotNull
    private Map<String, String> authHeaders() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("authorization", authorization);
        headerMap.put("connection", "close");
        return headerMap;
    }

    public String tokenBody() {
        JSONObject body = new JSONObject();
        body.put("appId", appId);
        body.put("appKey", appSecret);
        return body.toJSONString();
    }

    public String revokeBody(String messageId, String phone) {
        JSONObject body = new JSONObject();
        body.put("messageId", messageId);
        body.put("destinationAddress", "tel:+86" + phone);
        body.put("status", "RevokeRequested");
        return body.toJSONString();
    }

    public String assemblyBody(String content, JSONObject sendChannelInfo, List<String> list, String contributionId, String conversationId) {
        JSONObject body = new JSONObject();
        List<String> destinationAddressList = list.stream().map(s -> "tel:+86" + s).collect(Collectors.toList());
        String[] destinationAddress = destinationAddressList.toArray(new String[destinationAddressList.size()]);

        JSONObject json = new JSONObject();
        json.put("capabilityId", "ChatbotSA");
        json.put("version", "+g.gsma.rcs.botversion=\"#=1\"");
        JSONArray serviceCapability = new JSONArray();
        serviceCapability.add(json);

        body.put("messageId", UUID.randomUUID());
        body.put("messageList", JSONObject.parseArray(content));
        body.put("destinationAddress", destinationAddress);
        body.put("senderAddress", chatbotURI);
        body.put("serviceCapability", serviceCapability);
        body.put("contributionId", contributionId);
        body.put("conversationId", conversationId);
        body.put("smsSupported", false);
        body.put("storeSupported", true);
        body.put("reportRequest", new String[] {"sent","delivered","displayed","failed"});
        return body.toJSONString();
    }

    public String assemblyInteractionBody(String content, JSONObject sendChannelInfo, String phone, String inReplyTo, String contributionId, String conversationId) {
        JSONObject body = new JSONObject();
        String[] destinationAddress = {"tel:+86" + phone};
        JSONObject json = new JSONObject();
        json.put("capabilityId", "ChatbotSA");
        json.put("version", "+g.gsma.rcs.botversion=\"#=1\"");
        JSONArray serviceCapability = new JSONArray();
        serviceCapability.add(json);

        body.put("messageId", UUID.randomUUID());
        body.put("messageList", JSONObject.parseArray(content));
        body.put("destinationAddress", destinationAddress);
        body.put("senderAddress", chatbotURI);
        body.put("serviceCapability", serviceCapability);
        body.put("contributionId", contributionId);
        body.put("conversationId", conversationId);
        body.put("inReplyTo", inReplyTo);
        return body.toJSONString();
    }


    public String getFileServerRoot() {
        return fileServerRoot;
    }

    public void setFileServerRoot(String fileServerRoot) {
        this.fileServerRoot = fileServerRoot;
    }

    public String getServerRoot() {
        return serverRoot;
    }

    public void setServerRoot(String serverRoot) {
        this.serverRoot = serverRoot;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getChatbotId() {
        return chatbotId;
    }

    public void setChatbotId(String chatbotId) {
        this.chatbotId = chatbotId;
    }

    public String getChatbotURI() {
        return chatbotURI;
    }

    public void setChatbotURI(String chatbotURI) {
        this.chatbotURI = chatbotURI;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
