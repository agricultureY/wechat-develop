package com.ycn.wechat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycn.wechat.bean.button.*;

/**
 * 菜单工具类
 *
 * @author ycn
 * @package com.ycn.wechat.util
 * @ClassName MenuUtil
 * @Date 2018/7/10 11:24
 */
public class MenuUtil {


    private static final String CTRATE_MENU_URL  = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 创建菜单
     * @param accessToken
     * @param Menu 菜单json格式字符串
     * @return
     */
    public static int createMenu(String accessToken,String Menu){
        int result = Integer.MIN_VALUE;
        String url = CTRATE_MENU_URL.replaceAll("ACCESS_TOKEN", accessToken);
        JSONObject json = AccessTokenUtil.doPoststr(url, Menu);
        if(json!=null){
            //从返回的数据包中取数据{"errcode":0,"errmsg":"ok"}
            result = json.getIntValue("errcode");
        }
        return result;
    }

    public static String initMenu(){
        String result = "";
        ClickButton button11 = new ClickButton();
        button11.setKey("btn11");
        button11.setName("扫一扫");
        button11.setType(MenuType.SCANCODE_WAITMSG.toString());

        ClickButton button12 = new ClickButton();
        button12.setType(MenuType.CLICK.toString());
        button12.setName("今日歌曲");
        button12.setKey("button12");


        //创建点击一级菜单
        Button button1 = new Button();
        button1.setName("往期活动");
        button1.setType(MenuType.CLICK.toString());
        button1.setSub_button(new Button[]{button11, button12});
        //创建点击一级菜单
//        ClickButton button11 = new ClickButton();
//        button11.setName("往期活动");
//        button11.setKey("11");
//        button11.setType("click");


        //创建跳转型一级菜单
        ViewButton button21 = new ViewButton();
        button21.setName("百度一下");
        button21.setType(MenuType.VIEW.toString());
        button21.setUrl("https://www.baidu.com");

        //创建其他类型的菜单与click型用法一致
        ClickButton button31 = new ClickButton();
        button31.setName("拍照发图");
        button31.setType(MenuType.PIC_PHOTO_OR_ALBUM.toString());
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setName("发送位置");
        button32.setKey("32");
        button32.setType(MenuType.LOCATION_SELECT.toString());

        //封装到一级菜单
        Button button = new Button();
        button.setName("图片");
        button.setType(MenuType.CLICK.toString());
        button.setSub_button(new Button[]{button31,button32});

        //封装菜单
        Menu menu = new Menu();
        menu.setButton(new Button[]{button1,button21,button});
        return JSON.toJSON(menu).toString();
    }

    public static void main(String[] args) {
        String accessToken = AccessTokenUtil.getAccess_Token();
        String menu = MenuUtil.initMenu();
        System.out.println(menu);
        int res = MenuUtil.createMenu(accessToken, menu);
        if (0 == res)
            System.out.println("菜单创建成功");
        else
            System.out.println("错误码：" + res);
    }
}
