package com.ycn.wechat.bean;

/**
 * access token实体
 *
 * @author ycn
 * @package com.ycn.wechat.bean
 * @ClassName AccessToken
 * @Date 2018/7/9 15:30
 */
public class AccessToken {

    /**
     * 获取到的token
     */
    private String access_token;

    /**
     * 凭证有效时间
     */
    private int expries_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpries_in() {
        return expries_in;
    }

    public void setExpries_in(int expries_in) {
        this.expries_in = expries_in;
    }
}
