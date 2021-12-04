package de.marcel.adblockbrowserbyopensearch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    WebView webView;
    StringBuilder adservers;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        readAdServers();
        
        // WebView Control
        webView = findViewById(R.id.webview);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(true);
        webView.setLongClickable(true);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.setWebViewClient(new MyWebViewClient());
        
        registerForContextMenu(webView);
        
        // Set WebSettings for a WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(this.getCacheDir().getAbsolutePath());

        // Load google.de
        webView.loadUrl("https://www.google.de");
    
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ani = cm.getActiveNetworkInfo();
        if(ani != null && ani.isConnected())
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        else
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);                        // Enable this only if you need JavaScript support!
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);   // Enable this only if you want pop-ups!
        webSettings.setMediaPlaybackRequiresUserGesture(true);
    }
    
    private void readAdServers() {
        String line = "";
        adservers = new StringBuilder();
        
        InputStream is = this.getResources().openRawResource(R.raw.adblockserverlist);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        if(is != null) {
            try {
                while ((line = br.readLine()) != null) {
                    adservers.append(line);
                    adservers.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Advertise filter with the lists
    public class MyWebViewClient extends WebViewClient {
        
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            ByteArrayInputStream EMPTY = new ByteArrayInputStream("".getBytes());
            String kk5 = String.valueOf(adservers);
            
            if (kk5.contains(":::::" + request.getUrl().getHost())) {
                return new WebResourceResponse("text/plain", "utf-8", EMPTY);
            }
            return super.shouldInterceptRequest(view, request);
        }
    }
}