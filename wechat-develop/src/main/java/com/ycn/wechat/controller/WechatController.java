package com.ycn.wechat.controller;

import com.thoughtworks.xstream.XStream;
import com.ycn.wechat.baidufanyi.TransApi;
import com.ycn.wechat.bean.ImageMessage;
import com.ycn.wechat.bean.MsgType;
import com.ycn.wechat.bean.WechatInputMessage;
import com.ycn.wechat.bean.WechatOutputMessage;
import com.ycn.wechat.properties.WeChatProperties;
import com.ycn.wechat.util.AccessTokenUtil;
import com.ycn.wechat.util.SHA1;
import com.ycn.wechat.util.SerializeXmlUtil;
import com.ycn.wechat.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;

/**
 * 微信接口
 *
 * @author ycn
 * @package com.ycn.wechat.controller
 * @ClassName WechatController
 * @Date 2018/7/9 9:38
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    private final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WeChatProperties properties;

    @RequestMapping(value = "/getSpecMovie", method = {RequestMethod.GET, RequestMethod.POST})
    public void getSpecMovie(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        Boolean isGet = "get".equalsIgnoreCase(request.getMethod());
        if (isGet) {
            String echostr = checkURL(request, response);
            if(StringUtils.isEmpty(echostr))
                logger.error("wechat connect error!");
            else
                logger.info("wechat connect success!");
        }else {
            /**消息处理*/
            logger.info("enter post");
            try {
                acceptMessage(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String checkURL(HttpServletRequest request, HttpServletResponse response) {
        // 验证URL真实性
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        List<String> params = new ArrayList<String>();
        params.add(properties.getToken());
        params.add(timestamp);
        params.add(nonce);
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
            try {
                response.getWriter().write(echostr);
                logger.info("URL check success!  echostr：" + echostr);
                return echostr;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.error("URL check error!");
        return null;
    }

    private void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 处理接收消息
        ServletInputStream in = request.getInputStream();
        // 将POST流转换为XStream对象
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(WechatInputMessage.class);
        xs.processAnnotations(WechatOutputMessage.class);
        // 将指定节点下的xml节点数据映射为对象
        xs.alias("xml", WechatInputMessage.class);
        xs.alias("Image", ImageMessage.class);
        // 将流转换为字符串
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }
        // 将xml内容转换为InputMessage对象
        WechatInputMessage inputMsg = (WechatInputMessage) xs.fromXML(xmlMsg.toString());

        String servername = inputMsg.getToUserName();// 服务端
        String custermname = inputMsg.getFromUserName();// 客户端
        long createTime = inputMsg.getCreateTime();// 接收时间
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间

        // 取得消息类型
        String msgType = inputMsg.getMsgType();
        // 根据消息类型获取对应的消息内容
        if (msgType.equals(MsgType.Text.toString())) {
            // 文本消息
            logger.info("开发者微信号：" + inputMsg.getToUserName());
            logger.info("发送方帐号：" + inputMsg.getFromUserName());
            logger.info("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));
            logger.info("消息内容：" + inputMsg.getContent());
            logger.info("消息Id：" + inputMsg.getMsgId());

            StringBuffer str = new StringBuffer();
            /**定义要回复的消息*/
            String msg = "Hi welcome to Java码农基地";
            if(inputMsg.getContent().startsWith("翻译"))
                msg = TransApi.getTransResult(inputMsg.getContent().replaceAll("^翻译", ""));
            str.append("<xml>");
            str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");
            str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");
            str.append("<CreateTime>" + returnTime + "</CreateTime>");
            str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
            str.append("<Content><![CDATA[" + msg + "]]></Content>");
            str.append("</xml>");
            logger.info("response msg:" + str.toString());
            response.getWriter().write(str.toString());
        }
        // 获取并返回多图片消息
        if (msgType.equals(MsgType.Image.toString())) {
            logger.info("获取多媒体信息");
            logger.info("多媒体文件id：" + inputMsg.getMediaId());
            logger.info("图片链接：" + inputMsg.getPicUrl());
            logger.info("消息id，64位整型：" + inputMsg.getMsgId());

            WechatOutputMessage outputMsg = new WechatOutputMessage();
            outputMsg.setFromUserName(servername);
            outputMsg.setToUserName(custermname);
            outputMsg.setCreateTime(returnTime);
            outputMsg.setMsgType(msgType);
            ImageMessage images = new ImageMessage();
//            images.setMediaId(inputMsg.getMediaId());
            String mediaId = "";
            try {
                String accessToken = AccessTokenUtil.getAccess_Token();
                String filePaht = "F:\\self\\img\\hide.jpg";
                mediaId = UploadUtil.upload(filePaht, accessToken, MsgType.Image.toString());
                System.out.println(mediaId);
            } catch (NoSuchAlgorithmException | NoSuchProviderException| KeyManagementException | IOException e) {
                e.printStackTrace();
            }
            images.setMediaId(mediaId);
            outputMsg.setImage(images);
            logger.info("response img:\n" + xs.toXML(outputMsg));
            response.getWriter().write(xs.toXML(outputMsg));
        }
    }
}
