package com.thinkive.umeng.analytics;


import android.app.Application;

import com.umeng.analytics.MobclickAgent;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.setDebugMode(true);//设置为Debug模式，输出log日志
        MobclickAgent.openActivityDurationTrack(false);//禁止默认的页面统计方式，这样将不会再自动统计Activity


        //两个独立的session(启动)时间间隔，默认为30s
//        MobclickAgent.setSessionContinueMillis(1000);//此处当应用在后台运行超过1秒再回到前端，将被认为是两个独立的session(启动)

        //设置场景模式，包含统计、游戏、统计盒子、游戏盒子
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);//统计
    }
}
