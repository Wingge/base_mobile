package com.code.wing.baseapp.util;

import android.app.Activity;
import android.view.ViewGroup;

import com.devspark.appmsg.AppMsg;

/**
 * base on com.devspark.appmsg.AppMsg
 * Created by Administrator on 2016/8/18 0018.
 */

public class MsgUtil {

    public static void showMsg(Activity content, ViewGroup parentView, AppMsg.Style style, String msg){
        AppMsg appMsg = AppMsg.makeText(content, msg,style);
        appMsg.setParent(parentView);
        appMsg.show();
    }
    public static void showMsg(Activity content,int parentViewId,AppMsg.Style style, String msg){
        AppMsg appMsg = AppMsg.makeText(content, msg,style);
        appMsg.setParent(parentViewId);
        appMsg.show();
    }
}
