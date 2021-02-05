package test.t1;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public class Constants {


    /**
     * 0-移动；1-联通；2-电信
     */
    public static final String operator_china_mobile = "1";
    public static final String operator_china_unicom = "2";
    public static final String operator_china_telecom = "3";
    public static final String request_operator_success = "00000";

    /**
     * 账户信息
     */
    //计费方式(0预付费，1后付费）
    public static final String account_compute_way_before = "0";
    public static final String account_compute_way_after = "1";

    //账户状态(0=关闭;1=正常)
    public static final String account_status_no = "0";
    public static final String account_status_yes = "1";
    public static final String business_status_no = "0";
    public static final String business_status_yes = "1";
    public static final String channel_status_no = "0";
    public static final String channel_status_yes = "1";
    //状态（0=删除;1=正常）
    public static final String status_no = "0";
    public static final String status_yes = "1";

    public static final String account_detail_amount_type_deduction = "0";
    public static final String account_detail_amount_type_compensate = "1";

    /**
     * 业务通道
     */
    //业务通道状态(0=关闭;1=正常)
    public static final String send_type_group = "0";
    public static final String send_type_single = "1";

    /**
     * 消息类型
     * 00-短信通道 01-视频短信通道 10-5G消息通道  11-5G消息H5通道
     */
    public static final String msg_type_sms = "00";
    public static final String msg_type_video_sms = "01";
    public static final String msg_type_5g = "10";
    public static final String msg_type_5g_h5 = "11";
    /**
     * 详单状态
     */
    public static final String msg_status_success = "0";
    public static final String msg_status_fail = "1";
    public static final String msg_status_sending = "2";
    public static final String msg_status_submit_to_operator = "3";


    /**
     * 是否转普通消息
     */
    public static final String transfer_sms_yes = "0";
    public static final String transfer_sms_no = "1";


    /**
     * 是否触发关键词
     */
    public static final String is_trigger_keywords_yes = "0";
    public static final String is_trigger_keywords_no = "1";
    /**
     * 0正常模板 1兜底模板
     */
    public static final String is_final_model_yes = "0";
    public static final String is_final_model_no = "1";

    /**
     * 是否加速
     */
    public static final String speed_yes = "1";
    public static final String speed_no = "0";

    /**
     * es
     */
    public static final String rcs_msg_detail_index = "rcs_msg_detail";
    public static final String keyword = ".keyword";

    /**
     * token的头字段
     */
    public static final String TOKEN_HEADER_STRING = "Authorization";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 参数管理 cache name
     */
    public static final String SYS_CONFIG_CACHE = "sys-config";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache name
     */
    public static final String SYS_DICT_CACHE = "sys-dict";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
}
