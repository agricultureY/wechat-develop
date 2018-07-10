package com.ycn.wechat.bean.button;

/**
 * 按钮
 *
 * @author ycn
 * @package com.ycn.wechat.bean
 * @ClassName Button
 * @Date 2018/7/10 11:17
 */
public class Button {

    /**
     * 菜单标题
     */
    private String name;

    /**
     * 菜单响应的动作类型
     */
    private String type;

    /**
     * 子菜单
     */
    private Button[] sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
