package com.zaumal.ting89.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Aria2RPCHttp {
    Logger logger = LoggerFactory.getLogger(Aria2RPCHttp.class);
    private OkHttpClient okHttpClient = new OkHttpClient();
    private String dir;
    private String rpc;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    private String jsonRpc(String url){
//        String dir = "/media/AiCard_01/bddownload";
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"jsonrpc\"").append(":").append("\"2.0\"");
        sb.append(",");
        sb.append("\"id\"").append(":").append("\""+UUID.randomUUID().toString()+"\"");
        sb.append(",");
        sb.append("\"method\"").append(":").append("\"aria2.addUri\"");
        sb.append(",");
        sb.append("\"params\"").append(":");
        sb.append("[");
        sb.append("[");
        sb.append("\""+url+"\"");
        sb.append("]");
        sb.append(",");
        sb.append("{");
        sb.append("\"dir\"").append(":").append("\""+dir+"\"");
        sb.append("}");
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    public boolean sendRpc(String url){
        logger.info("url:{}",url);
        String jsonrpc = jsonRpc(url);
        final Request request = new Request.Builder()
                .url(this.rpc)
                .header("Authorization","Basic YWRtaW46MTIzNDU2N0FT")
                .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonrpc))
                .build();
        final Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.isSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String dir = "/media/AiCard_01/bddownload";
        String rpc = "http://192.168.2.115:6800/jsonrpc";
        String url = "http://www.ting89.com/down/?8645-0.html";
        Aria2RPCHttp a = new Aria2RPCHttp();
        a.setDir(dir);
        a.setRpc(rpc);
        boolean s = a.sendRpc(url);
        System.out.println(s);
    }
}