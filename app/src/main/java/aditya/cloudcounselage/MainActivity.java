package aditya.cloudcounselage;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;


import im.delight.android.webview.AdvancedWebView;


public class MainActivity extends AppCompatActivity {

    ConneckBar conneckBar;



    private LeftDrawerLayout mLeftDrawerLayout;

    private AdvancedWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();


        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        mWebView = (AdvancedWebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);




        mWebView.setWebViewClient(new mWebClient());
        mWebView.setWebViewClient(new mWebClient());
        FragmentManager fm = getSupportFragmentManager();

        conneckBar = new ConneckBar(getApplicationContext(), mWebView, "No Internet Connection!", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(conneckBar.isConnected())
                {
                    mWebView.reload();
                }


            }
        }, Snackbar.LENGTH_INDEFINITE, Color.RED, Color.WHITE, Color.LTGRAY);


        MyMenuFragment mMenuFragment = (MyMenuFragment) fm.findFragmentById(R.id.id_container_menu);
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);
        if (mMenuFragment == null) {
            mMenuFragment = new MyMenuFragment();
            Bundle bundle= new Bundle();
            bundle.putInt("webview",mWebView.getId());
            bundle.putInt("flowingdrawer",mLeftDrawerLayout.getId());
            mMenuFragment.setArguments(bundle);
            fm.beginTransaction().add(R.id.id_container_menu,mMenuFragment).commit();
        }
        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(mMenuFragment);




        //Setting up the webview

        mWebView.loadUrl("http://cloudcounselage.com/");




    }

    public class mWebClient extends WebViewClient
    {



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


            view.loadUrl(url);

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if(conneckBar.isConnected())
            {

                mWebView.setVisibility(View.VISIBLE);
                findViewById(R.id.webViewProgress).setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);

            }
            else
            {
                mWebView.setVisibility(View.GONE);
            }
        }


        @Override
        public void onPageFinished(WebView view, String url) {

            findViewById(R.id.webViewProgress).setVisibility(View.GONE);

            super.onPageFinished(view, url);
        }
    }


    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_nav_drawer);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftDrawerLayout.toggle();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mLeftDrawerLayout.isShownMenu()) {
            mLeftDrawerLayout.closeDrawer();
        }
        else if(mWebView.canGoBack())
        {
            mWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }



}
