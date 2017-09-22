package com.thinkive.umeng.analytics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.social.UMPlatformData;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private final String mPageName = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        // 在代码中配置Appkey和Channel
//        MobclickAgent.UMAnalyticsConfig analyticsConfig = new MobclickAgent.UMAnalyticsConfig(context,
//                "59c32c1cf5ade4696600001c", "Wandoujia", MobclickAgent.EScenarioType.E_UM_NORMAL);
//        MobclickAgent. startWithConfigure(analyticsConfig);


        //设置是否对日志信息进行加密, 默认false(不加密)
        MobclickAgent.enableEncrypt(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * 统计页面(页面可能是Activity 也可能是Fragment或View)
         * 仅有Activity的应用中SDK自动调用，不需要单独写
         */
        MobclickAgent.onPageStart(mPageName);

        /**
         * 统计应用时长(也就是Session时长)
         * 不要同时在父和子Activity中重复添加onPause方法，否则会造成重复统计
         * 确保在所有的Activity中都调用
         */
        MobclickAgent.onResume(context);

    }

    @Override
    protected void onPause() {
        super.onPause();

        /**
         * 统计页面(页面可能是Activity 也可能是Fragment或View)
         * 仅有Activity的应用中SDK自动调用，不需要单独写
         * 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。
         */
        MobclickAgent.onPageEnd(mPageName);

        /**
         * 统计应用时长(也就是Session时长)
         * 不要同时在父和子Activity中重复添加onPause方法，否则会造成重复统计
         * 确保在所有的Activity中都调用
         */
        MobclickAgent.onPause(context);


    }

    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.umeng_example_analytics_signin:
                //账号的统计
                MobclickAgent.onProfileSignIn("user_login_id");//统计应用自身的账号
//                MobclickAgent.onProfileSignIn("WB","user_login_id");//统计用户第三方账号（如新浪微博）
                break;
            case R.id.umeng_example_analytics_signoff:
                //账号的统计
                MobclickAgent.onProfileSignOff();
                break;
            case R.id.umeng_example_analytics_event_count1:
                //计数事件的统计（不带参数）
                MobclickAgent.onEvent(context, "click");
                break;
            case R.id.umeng_example_analytics_event_count2:
                //计数事件的统计（带参数）
                Map<String, String> map_ekv = new HashMap<String, String>();
                map_ekv.put("type", "流行");
                map_ekv.put("artist", "谢霆锋");
                MobclickAgent.onEvent(context, "music", map_ekv);
                break;
            case R.id.umeng_example_analytics_event_value:
                //计算事件的统计
                int duration = 12000; //开发者需要自己计算音乐播放时长
                Map<String, String> map_value = new HashMap<String, String>();
                map_value.put("type", "流行");
                map_value.put("artist", "谢霆锋");
                MobclickAgent.onEventValue(context, "play", map_value, duration);
                break;
            case R.id.umeng_example_analytics_event_social:
                //社交统计
                UMPlatformData platform = new UMPlatformData(UMPlatformData.UMedia.SINA_WEIBO, "user_id");
                platform.setGender(UMPlatformData.GENDER.MALE); // optional
                platform.setWeiboId("weiboId"); // optional
                MobclickAgent.onSocialEvent(this, platform);
                break;
            case R.id.umeng_example_analytics_make_crash:
                //程序崩溃的统计
                "123".substring(10);
                break;
            case R.id.umeng_example_analytics_js_analytic:
                //Webview的统计
                startActivity(new Intent(context, WebviewAnalytic.class));
                break;
            case R.id.umeng_example_analytics_fragment_stack:
                //Fragment页面的统计
                startActivity(new Intent(context, FragmentStack.class));
                break;
            case R.id.umeng_example_analytics_fragment_tabs:
                //Fragment页面的统计
                startActivity(new Intent(context, FragmentTabs.class));
                break;
        }
    }
}
