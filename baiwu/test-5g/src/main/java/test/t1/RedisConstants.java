package test.t1;

/**
 * 用户常量信息
 */
public class RedisConstants {
    //token请求次数 string ttl
    public static final String TOKEN_KEY_COUNT = "rcs:limit:token:";
    //mq幂等 incr ttl
    public static final String MQ_IDEMPOTENT = "rcs:mq:md:";
    //账户对手机号 发送频率 hash ttl
    public static final String LIMIT_COUNT_KEY = "rcs:limit:phone:";
    //调用运营商接口发送 incr ttl
    public static final String OPERATOR_API = "rcs:limit:operator:";
    //冻结  incr ttl
    public static final String ACCOUNT_FROZEN = "rcs:account:frozen:";
    //任务终止下发 String ttl
    public static final String TASK_STOP = "rcs:stop:task:";
    //当前token hash
    public static final String TOKEN_KEY = "rcs:token:now";
    //账户信息 hash
    public static final String ACCOUNT_KEY = "rcs:account";
    //账户模板信息 hash
    public static final String ACCOUNT_TEMPLATE_KEY = "rcs:template";
    public static final String ACCOUNT_TEMPLATE_CHECK_KEY = "rcs:template:check:";
    //业务通道信息 hash
    public static final String ACCOUNT_BUSINESS_KEY = "rcs:business";
    //新增业务通道信息 hash
    public static final String ADD_ACCOUNT_BUSINESS_KEY = "rcs:add:channel";
    //通道信息 hash
    public static final String CHANNEL = "rcs:channel";
    //携号转网 hash
    public static final String TRANSFER_NET_KEY = "xh";
    //携号转网 hash
    public static final String BLACK_PHONE_KEY = "hmd";
    //预消费  hIncr
    public static final String ACCOUNT_NOW_CONSUME = "rcs:consume";
    //chatBot token
    public static final String CHAT_BOT_TOKEN_KEY = "rcs:token:bot:";


    //list topic
    public static final String TOKEN_TOPIC = "rcs:token_topic";


//    //token请求次数
//    public static final String TOKEN_KEY_COUNT = "rcs0650";
//    public static final String TOKEN_KEY = "rcs0651";
//    //账户信息
//    public static final String ACCOUNT_KEY = "rcs0652";
//    //账户模板信息
//    public static final String ACCOUNT_TEMPLATE_KEY = "rcs0653";
//    //业务通道信息
//    public static final String ACCOUNT_BUSINESS_KEY = "rcs0654";
//    //账户对手机号 发送频率
//    public static final String LIMIT_COUNT_KEY = "rcs0655";
//    //携号转网
//    public static final String TRANSFER_NET_KEY = "xh";
//    //大客户插队
//    public static final String LARGE_CUSTOMER_SEMAPHORE = "rcs0658";
//    //预消费
//    public static final String ACCOUNT_NOW_CONSUME = "rcs0660";
//
//
//    //调用运营商接口发送
//    public static final String OPERATOR_API = "yys";
//    //mq幂等
//    public static final String MQ_IDEMPOTENT = "md";
//
//
//    public static final String TOKEN_TOPIC = "token_topic";
}
