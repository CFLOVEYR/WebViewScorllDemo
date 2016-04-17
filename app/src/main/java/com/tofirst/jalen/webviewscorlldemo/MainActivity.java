package com.tofirst.jalen.webviewscorlldemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * 此工程是Android Studio 2.0建立的 需要gradle2.10才可以
 */
public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout mainContent;
    private AppBarLayout appbar;
    private Toolbar toolBar;
    private WebView webview;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initWebView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mainContent = (CoordinatorLayout) findViewById(R.id.main_content);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolBar = (Toolbar) findViewById(R.id.toolBar);//
        webview = (WebView) findViewById(R.id.webview);

    }

    /**
     * 初始化WebView的配置
     */
    private void initWebView() {
        webview.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置WebView属性,运行执行js脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //调用loadView方法为WebView加入链接
        webview.loadUrl("http://blog.csdn.net/y1258429182");
    }


    // 我们需要重写回退按钮的时间,当用户点击回退按钮：
    //1.webView.canGoBack()判断网页是否能后退,可以则goback()
    //2.如果不可以连续点击两次退出App,否则弹出提示Toast
    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }

        }
    }
}
