package test.t1;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.List;

public interface IRcsMsgStrategy {
    JSONObject sendRcsMsg(JSONObject sendChannelInfo, List<String> list, String content);

    JSONObject sendInterMsg(JSONObject sendChannelInfo, String phone, String content, String inReplyTo);

    JSONObject revokeMsg(JSONObject sendChannelInfo, String messageId, String phone);

    JSONObject uploadMedia(JSONObject sendChannelInfo, File thumbnail, File file, JSONObject params);

    JSONObject deleteMedia(JSONObject sendChannelInfo, JSONObject params);
}
