package com.thinkive.umeng.analytics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

/**
 * @author: sq
 * @date: 2017/9/21
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: SDKV4.6.2 之前 页面访问只能统计到 'Activity' 级别，不能统计到每个 'Fragment' .
 * 现在 新增的页面统计API，可以用来统计Fragment 这样颗粒度更细的页面。
 */
public class FragmentTabs extends FragmentActivity {
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.umeng_example_analytics_fragment_tabs);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"), FragmentSimple.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"), FragmentContacts.class, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 统计应用时长(也就是Session时长)
         */
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /**
         * 统计应用时长(也就是Session时长)
         */
        MobclickAgent.onPause(this);
    }


    public static class FragmentSimple extends Fragment {
        private final String mPageName = "FragmentSimple";

        static FragmentSimple newInstance(int num) {
            FragmentSimple f = new FragmentSimple();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * The Fragment's UI is just a simple text view showing its instance
         * number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TextView tv = new TextView(getActivity());
            tv.setText("Fragment Simple");
            return tv;
        }

        @Override
        public void onResume() {
            // TODO Auto-generated method stub
            super.onResume();
            /**
             * 统计页面(此处页面为FragmentActivity)
             */
            MobclickAgent.onPageStart(mPageName);
        }

        @Override
        public void onPause() {
            // TODO Auto-generated method stub
            super.onPause();
            /**
             * 统计页面(此处页面为FragmentActivity)
             */
            MobclickAgent.onPageEnd(mPageName);
        }
    }

    public static class FragmentContacts extends Fragment {
        private final String mPageName = "FragmentContacts";

        static FragmentSimple newInstance(int num) {
            FragmentSimple f = new FragmentSimple();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * The Fragment's UI is just a simple text view showing its instance
         * number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TextView tv = new TextView(getActivity());
            tv.setText("Fragment Contacts");
            return tv;
        }

        @Override
        public void onResume() {
            // TODO Auto-generated method stub
            super.onResume();
            /**
             * 统计页面(此处页面为FragmentActivity)
             */
            MobclickAgent.onPageStart(mPageName);
        }

        @Override
        public void onPause() {
            // TODO Auto-generated method stub
            super.onPause();
            /**
             * 统计页面(此处页面为FragmentActivity)
             */
            MobclickAgent.onPageEnd(mPageName);
        }
    }
}