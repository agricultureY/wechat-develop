package com.ycn.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 多媒体ID实体类
 *
 * @author ycn
 * @package com.ycn.wechat.bean
 * @ClassName MediaIdMessage
 * @Date 2018/7/9 10:15
 */
public class MediaIdMessage {
    @XStreamAlias("MediaId")
    @XStreamCDATA
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
