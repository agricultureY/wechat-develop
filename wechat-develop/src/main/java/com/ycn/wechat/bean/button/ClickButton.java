package com.ycn.wechat.bean.button;

/**
 * 点击按钮
 *
 * @author ycn
 * @package com.ycn.wechat.bean
 * @ClassName ClickButton
 * @Date 2018/7/10 11:19
 */
public class ClickButton extends Button {

    /**
     * 菜单key值
     */
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
