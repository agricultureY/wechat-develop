package com.ycn.wechat.baidufanyi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycn.wechat.util.MD5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度翻译接口
 */
public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private static final String APP_ID = "Your baidu fanyi appid";
    private static final String SECURITY_KEY = "Your baidu fanyi app security";
    //翻译源自动识别
    private static final String FROM = "auto";
    //翻译结果语言 自动识别
    private static final String TO  ="auto";
    /**
     * 获取翻译结果
     * @param query
     * @return
     */
    public static String getTransResult(String query) {
        Map<String, String> params = buildParams(query);
        String resultMap = HttpGet.get(TRANS_API_HOST, params);
        JSONObject json = JSON.parseObject(resultMap);
        StringBuffer buffer = new StringBuffer();
        //保存翻译后的结果
        List<Map> list = (List<Map>) json.get("trans_result");
        for (int i = 0; i < list.size(); i++) {
            buffer.append(list.get(i).get("dst"));
        }
        return buffer.toString();
    }

    private static  Map<String, String> buildParams(String query) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", FROM);
        params.put("to", TO);

        params.put("appid", APP_ID);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = APP_ID + query + salt + SECURITY_KEY; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

    public static void main(String[] args) {
        String query = "苹果手机";
        String query1 = "Hello world";
        System.out.println(TransApi.getTransResult(query));
        System.out.println(TransApi.getTransResult(query1));
    }
}
