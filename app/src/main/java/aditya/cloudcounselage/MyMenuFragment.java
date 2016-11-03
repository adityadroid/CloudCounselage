package aditya.cloudcounselage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;


import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.mxn.soul.flowingdrawer_core.MenuFragment;

import im.delight.android.webview.AdvancedWebView;


public class MyMenuFragment extends MenuFragment {

    private WebView mWebView;
    LeftDrawerLayout mLeftDrawerLayout;




    public MyMenuFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);

        mWebView=(WebView) getActivity().findViewById(getArguments().getInt("webview"));
        mLeftDrawerLayout=(LeftDrawerLayout)getActivity().findViewById(getArguments().getInt("flowingdrawer"));
        NavigationView mNavigator = (NavigationView)view.findViewById(R.id.vNavigation);
        mNavigator.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId())
                {

                    case R.id.onecloud:
                    mWebView.loadUrl("http://cloudcounselage.com/onecloud");
                    break;
                    case  R.id.edpedia:
                    mWebView.loadUrl("http://cloudcounselage.com/edpedia");
                    break;


                }

                mLeftDrawerLayout.closeDrawer();
                return false;
            }
        });
        return  setupReveal(view) ;
    }


    public void onOpenMenu(){
    }
    public void onCloseMenu(){
    }
}
