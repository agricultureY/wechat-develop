package com.ycn.wechat.bean.button;

/**
 * 菜单类枚举
 *
 * @author ycn
 * @package com.ycn.wechat.bean.button
 * @ClassName MenuType
 * @Date 2018/7/10 11:42
 */
public enum MenuType {

    VIEW("view"),//网页类型
    CLICK("click"),//点击类型

    SCANCODE_WAITMSG("scancode_waitmsg"),//扫码带提示
    SCANCODE_PUSH("scancode_push"),//扫码事件

    PIC_SYSPHOTO("pic_sysphoto"),//系统拍照发图
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),//拍照或相册发图
    PIC_WEIXIN("pic_weixin"),//微信相册发图

    LOCATION_SELECT("location_select"),//发送位置

    MEDIA_ID("media_id"),//图片

    VIEW_LIMITED("view_limited"),//图文消息

    MINIPROGRAM("miniprogram");//小程序类型

    private String menuType = "";

    MenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * @return the msgType
     */
    @Override
    public String toString() {
        return menuType;
    }
}
