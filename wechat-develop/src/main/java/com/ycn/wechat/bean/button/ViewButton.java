package com.ycn.wechat.bean.button;

/**
 * @author ycn
 * @package com.ycn.wechat.bean.button
 * @ClassName ViewButton
 * @Date 2018/7/10 11:21
 */
public class ViewButton extends Button {

    /**
     * 网页链接
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
