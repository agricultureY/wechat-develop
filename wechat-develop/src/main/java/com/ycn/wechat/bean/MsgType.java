package com.ycn.wechat.bean;

/**
 * 消息类型的枚举类
 *
 * @author ycn
 * @package com.ycn.wechat.bean
 * @ClassName MsgType
 * @Date 2018/7/9 10:07
 */
public enum MsgType {

    Text("text"),//文本消息
    Image("image"),//图片消息
    Music("music"),//音频
    Video("video"),//视频
    Voice("voice"),//小视频
    Location("location"),//地理位置
    Link("link");//链接

    private String msgType = "";

    MsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the msgType
     */
    @Override
    public String toString() {
        return msgType;
    }
}
