package util;

import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
public class DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";
    public static String YYYYMM = "yyyyMM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String dateTimeSecond(final Date date) {
        return parseDateToStr(YYYY_MM_DD_HH_MM_SS, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取N天的日期,n>0得到nowDate的n天后的日期,n<0得到nowDate的n天前的日期
     */
    public static Date getNewDate(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
        return calendar.getTime();
    }


    public static String getHttpHeaderDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat greenwichDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        greenwichDate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return greenwichDate.format(cal.getTime());
    }

    public static Date getGMT8Time() {
        Date gmt8 = null;
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"), Locale.CHINESE);
            Calendar day = Calendar.getInstance();
            day.set(Calendar.YEAR, cal.get(Calendar.YEAR));
            day.set(Calendar.MONTH, cal.get(Calendar.MONTH));
            day.set(Calendar.DATE, cal.get(Calendar.DATE));
            day.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
            day.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
            day.set(Calendar.SECOND, cal.get(Calendar.SECOND));
            gmt8 = day.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gmt8;
    }

    static Instant start = Instant.now();

    public static long timeConsume() {
        Instant now = Instant.now();
        long result = ChronoUnit.MILLIS.between(start, now);
        start = now;
        return result;
    }


    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat greenwichDate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
//        greenwichDate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        String headerDate = DateUtils.getHttpHeaderDate();
        String headerDate = "Wed, 09 Dec 2020 12:12:12 GMT";
        System.out.println("Date:" + headerDate);

//        System.out.println("集微科技：Authorization:" + tokenHeader("125200401111382", "xH837(h2P0Q*rZ5U", headerDate));
//        System.out.println("和选电商：Authorization:" + tokenHeader("125200401111383", "~5#)&a.4x-nGM\"", headerDate));
//        System.out.println("南京银行：Authorization:" + tokenHeader("125200401111384", "STv211gO;$", headerDate));
//        System.out.println("优信二手车：Authorization:" + tokenHeader("125200401111534", "[|j1Tw01PcG>", headerDate));
//        System.out.println("途牛商旅：Authorization:" + tokenHeader("125200401111533", "6ZZ|E8l.80!RWu7", headerDate));
//        System.out.println("奥凯航空：Authorization:" + tokenHeader("125200401111532", "0W6AgZUJ73xU175)", headerDate));
//        System.out.println("富滇银行：Authorization:" + tokenHeader("125200401111531", "1Pex{_\"fkR,s-5`", headerDate));
//        System.out.println("乐收：Authorization:" + tokenHeader("125200401111530", "Z9!XqA|01&", headerDate));
//        System.out.println("郑州银行：Authorization:" + tokenHeader("125200401111529", "n2=2+!q65CL`K23", headerDate));
//        System.out.println("百世优派：Authorization:" + tokenHeader("125200401111528", "O{18;T6vfgy^7", headerDate));
//        System.out.println("百世云仓：Authorization:" + tokenHeader("125200401111527", "iPXZ4](s", headerDate));
//        System.out.println("百世快递：Authorization:" + tokenHeader("125200401111526", "^gO/m2IGV56@_0vzk", headerDate));
//        System.out.println("好慷在家：Authorization:" + tokenHeader("125200401111525", "F!cGGYF4Wt0,`oS563TR", headerDate));
//        System.out.println("网银在线：Authorization:" + tokenHeader("125200401111516", "G7\\+!*@w(:x5J1", headerDate));
//        System.out.println("京东钱包：Authorization:" + tokenHeader("125200401111517", "LjUl&-I\"2K93vL", headerDate));
//        System.out.println("懂车帝卖车通：Authorization:" + tokenHeader("125200401111518", "XKu9pd/F>2~jl@5v;", headerDate));
//        System.out.println("徽商银行：Authorization:" + tokenHeader("125200401111519", "M3dsRQ$^WW", headerDate));
//        System.out.println("华泰证券：Authorization:" + tokenHeader("125200401111520", "Tab,=db47", headerDate));
//        System.out.println("抖音火山版：Authorization:" + tokenHeader("125200401111521", "iD;(TCm5w", headerDate));
//        System.out.println("国信证券：Authorization:" + tokenHeader("125200401111522", "r3`M5{4w0", headerDate));
//        System.out.println("京东物流：Authorization:" + tokenHeader("125200401111523", "M<m9O3V9", headerDate));
//        System.out.println("京东快递柜：Authorization:" + tokenHeader("125200401111524", "EcxWq&d6:", headerDate));
//        System.out.println("京东快递柜：Authorization:" + tokenHeader("125200401111514", "9<P0)8o4", headerDate));
//        System.out.println("中通快递：Authorization:" + tokenHeader("125200401111707", "7p.~W2h1fq9qj)b(5sp]", headerDate));
//        System.out.println("长龙航空：Authorization:" + tokenHeader("125200401111708", "bX29AoXq8@:{I", headerDate));
//        System.out.println("腾讯科技：Authorization:" + tokenHeader("txkjcs", "1Ab7LP4aIQC@xMmDU5rA", headerDate));

//        System.out.println("中飞艾维5G消息:Authorization:" + tokenHeader("125200401111833", "lxB;6O(2ST|J~3b`qw", headerDate));
//        System.out.println("光鉴科技5G消息:Authorization:" + tokenHeader("125200401111834", "vnEOXyd|(5$XPr", headerDate));
//        System.out.println("清锋时代5G消息:Authorization:" + tokenHeader("125200401111835", "nF7#b<7Bk4iG/L]2", headerDate));
//        System.out.println("踏歌智行5G消息:Authorization:" + tokenHeader("125200401111836", "!H1?2m5P", headerDate));
//        System.out.println("易诚高科5G消息:Authorization:" + tokenHeader("125200401111837", "(ec_5v@S9EQ87", headerDate));
//        System.out.println("标贝科技5G消息:Authorization:" + tokenHeader("125200401111838", "N;SnhlE6t1D9A#\"j3", headerDate));
//        System.out.println("观脉科技5G消息:Authorization:" + tokenHeader("125200401111839", "4kP1<0Oq50X3,#_fB71h", headerDate));
//        System.out.println("海马云5G消息:Authorization:" + tokenHeader("125200401111840", "e#k%b38T9<2b", headerDate));
//        System.out.println("镁伽机器人5G消息:Authorization:" + tokenHeader("125200401111841", "3:=8u53Te&27", headerDate));
//        System.out.println("梯影传媒5G消息:Authorization:" + tokenHeader("125200401111842", ".]Ky3Fe4;7Z%cL1f\"\\", headerDate));
//        System.out.println("卓视智通5G消息:Authorization:" + tokenHeader("125200401111843", "<i3>Kj9>lL\\0I1", headerDate));
//        System.out.println("为快科技5G消息:Authorization:" + tokenHeader("125200401111844", "0OyHdU>@,\"$o", headerDate));
//        System.out.println("歌尔创客5G消息:Authorization:" + tokenHeader("125200401111845", "63>z4G6Y_pck5vB1", headerDate));

        System.out.println("京东健康:" + tokenHeader("1252004011111443", "63R2,z8^q=GTvp", headerDate));
//        System.out.println("京东电器:"+tokenHeader("1252004011111444","M0RI<e0M?o4AJ",headerDate));
//        System.out.println("京东手机:"+tokenHeader("1252004011111445","+X^$Q%L:i24>\"qa1p9",headerDate));
//        System.out.println("京东旅游:"+tokenHeader("1252004011111446","Su64wLH&TeY6qaJ&^[",headerDate));
//        System.out.println("燕之屋:"+tokenHeader("1252004011111447","cY\\92?LF3c1&r\\B",headerDate));
//        System.out.println("网龙教育:"+tokenHeader("1252004011111448","9nsc=4~6",headerDate));
//        System.out.println("会面:"+tokenHeader("1252004011111449","2S;jFtYzc0Nx",headerDate));
//        System.out.println("神州英才:"+tokenHeader("1252004011111450","jM0YT5>yE7%[",headerDate));
        /**移动h5*/
        System.out.println("福建移动H5:Authorization:" + tokenHeaderH5("iap_12520020000120", "4f95d15126615625250926f3747dd5861b745927ce58051295989de43deeb120", headerDate));
        /**联通*/
//        System.out.println("腾讯5G生态计划：Authorization:" + tokenHeader("iap_2012090011233", "ASdf1234!", headerDate));
        System.out.println("上海联通和选：Authorization:" + tokenHeader("iap_201201001008", "12345678", headerDate));


    }

    private static String tokenHeader(String appId, String password, String headerDate) {
        String tokenHeader = null;
        String token = SHA256Util.encode(password);
//        System.out.println("token:" + token);
        String text = appId.concat(":").concat(SHA256Util.encode(token + headerDate));
        try {
            String base64Str = Base64.getEncoder().encodeToString(text.getBytes("UTF-8"));
            tokenHeader = "Basic ".concat(base64Str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tokenHeader;
    }

    private static String tokenHeaderH5(String appId, String token, String headerDate) {
        String tokenHeader = null;
        String text = appId.concat(":").concat(SHA256Util.encode(token + headerDate));
        try {
            String base64Str = Base64.getEncoder().encodeToString(text.getBytes("UTF-8"));
            tokenHeader = "Basic ".concat(base64Str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tokenHeader;
    }
}
