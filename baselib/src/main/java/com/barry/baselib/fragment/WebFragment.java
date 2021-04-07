package com.barry.baselib.fragment;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.barry.baselib.R;
import com.barry.baselib.contract.NullContract;
import com.barry.baselib.mvp.BaseMVPFragment;
import com.barry.baselib.presenter.NullPresenter;
import com.barry.baselib.utils.AppUtils;
import com.barry.baselib.utils.PixelUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;

public class WebFragment extends BaseMVPFragment<NullContract.View, NullContract.Presenter> implements NullContract.View {

    public AgentWeb mAgentWeb;

    private final String mWebViewUrl;
    private final boolean hasMarginTop;
    private WebFragmentListener mListener;

    private final WebViewClient mWebViewClient = new WebViewClient() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (mListener != null) {
                mListener.setWebViewUrl(url);
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }
        
    };

    private final WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mListener != null) {
                mListener.setHeaderTitle(title);
            }
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress >= 100) {
                String script = "var chatsuccesscallback={};var chaterrorcallback={};function chatwebkitcall(key,code,data){let success=chatsuccesscallback[key];let error=chaterrorcallback[key];if(code==\"0\"){success(JSON.stringify(data))}else{error(JSON.stringify(data))}try{delete chatsuccesscallback[key];delete chaterrorcallback[key]}catch(e){}}function chatrandomkey(){return new Date().getTime()+\"_\"+parseInt(Math.random()*(10000000+1),10)};window.ChatWebApi={call:function(params){let key=chatrandomkey();chatsuccesscallback[key]=params.callback.success;chaterrorcallback[key]=params.callback.fail;window.ChatWebKit.call(key,params.action,JSON.stringify(params.data),\"chatwebkitcall\")}}";
                view.evaluateJavascript(script, null);
            }
        }
    };


    public WebFragment(String url, Boolean hasMarginTop) {
        super();
        this.mWebViewUrl = url;
        this.hasMarginTop = hasMarginTop;
    }

    @Override
    public NullContract.Presenter createPresenter() {
        return new NullPresenter(mContext);
    }

    @Override
    public NullContract.View createView() {
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_web;
    }

    @Override
    public void init() {
        LinearLayout webRoot = rootView.findViewById(R.id.container);
        if (hasMarginTop) {
            webRoot.setPadding(0, PixelUtils.getStatusBarHeight(mContext), 0, 0);
        }

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(webRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(this.getResources().getColor(R.color.colorPrimary), 2)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(mWebViewUrl);
//        mAgentWeb.getWebCreator().getWebView().setBackgroundColor(getResources().getColor(R.color.colorAccent));

        mAgentWeb.getAgentWebSettings().getWebSettings().setJavaScriptEnabled(true);
        mAgentWeb.getAgentWebSettings().getWebSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
        mAgentWeb.getAgentWebSettings().getWebSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小、
        mAgentWeb.getAgentWebSettings().getWebSettings().setUserAgentString("baselib" + "_android_" + AppUtils.getVersionName(mContext));
        mAgentWeb.getJsInterfaceHolder().addJavaObject("ChatWebKit", new AndroidJSInterface(mAgentWeb));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAgentWeb != null && mAgentWeb.getWebLifeCycle() != null) {
            mAgentWeb.getWebCreator().getWebView().onResume();
        }
    }

    @Override
    public void onPause() {
        if (mAgentWeb != null && mAgentWeb.getWebLifeCycle() != null) {
            mAgentWeb.getWebCreator().getWebView().onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (mAgentWeb != null && mAgentWeb.getWebLifeCycle() != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroyView();
    }

    public void setListener(WebFragmentListener mListener) {
        this.mListener = mListener;
    }

    public void refresh() {
        if (mAgentWeb != null && mAgentWeb.getWebLifeCycle() != null) {
            mAgentWeb.getUrlLoader().reload();
        }
    }

    private class AndroidJSInterface {
        private AgentWeb agentWeb;

        public AndroidJSInterface(AgentWeb agentWeb) {
            this.agentWeb = agentWeb;
        }

        @JavascriptInterface
        public void call(String key, String action, String data, String call) {
            Log.d("callLYQ", "action : " + action + " |data : " + data + "| call : " + call);
        }
    }

    public interface WebFragmentListener {
        /**
         * 设置导航栏的标题
         *
         * @param title 标题
         */
        void setHeaderTitle(String title);

        /**
         * 获取WebView的Url地址
         *
         * @param url 网址
         */
        void setWebViewUrl(String url);

        /**
         * 设置导航栏的更多 显示状态
         *
         * @param hideMore
         */
        void setHeaderMore(boolean hideMore);

        /**
         * 关闭WebView
         */
        void closeWebView();
    }
}