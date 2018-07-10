package com.ycn.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 输出信息实体
 *
 * @author ycn
 * @package com.ycn.wechat.bean
 * @ClassName wechatOutputMessage
 * @Date 2018/7/9 10:17
 */
@XStreamAlias("xml")
public class WechatOutputMessage {

    @XStreamAlias("ToUserName")
    @XStreamCDATA
    private String ToUserName;

    @XStreamAlias("FromUserName")
    @XStreamCDATA
    private String FromUserName;

    @XStreamAlias("CreateTime")
    private Long CreateTime;

    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String MsgType = "text";

    private ImageMessage Image;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public ImageMessage getImage() {
        return Image;
    }

    public void setImage(ImageMessage image) {
        Image = image;
    }
}
