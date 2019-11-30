package de.marcel.adblockbrowserbyopensearch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    WebView view;
    StringBuilder wwwdf2;
    StringBuilder wwwdf;
    StringBuilder hosts;
    StringBuilder AdguardDNS;
    StringBuilder adservers;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Load filter loading
        adservers();

        //Webview Control
        view = (WebView) findViewById(R.id.webview);
        registerForContextMenu(view);
        view.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setSupportZoom(true);
        view.getSettings().setDisplayZoomControls(false);
        view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        view.setScrollbarFadingEnabled(true);
        view.setLongClickable(true);
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setAppCacheEnabled(true);
        view.getSettings().setSavePassword(true);
        view.getSettings().setSaveFormData(true);
        view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        view.getSettings().setAllowFileAccess(true);
        view.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        view.getSettings().setAllowFileAccess(true);
        view.getSettings().setEnableSmoothTransition(true);
        view.setWebViewClient(new MyWebViewClient());
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setMediaPlaybackRequiresUserGesture(true);
        view.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        view.loadUrl("https://www.google.de");
    }


    //Advertise filter with the lists
    public class MyWebViewClient extends WebViewClient {

        private Map<String, Boolean> loadedUrls = new HashMap<>();

      @Override
      public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
	ByteArrayInputStream EMPTY = new ByteArrayInputStream("".getBytes());
                    String kk5 = String.valueOf(adservers);
                    if (kk5.contains(":::::"+request.getUrl().getHost())) {
                        return new WebResourceResponse("text/plain", "utf-8", EMPTY);
                    }
          return super.shouldInterceptRequest(view, request);
      }
    }






//Advertise filter list Loading
    private void adservers(){
        String strLine2="";
        adservers = new StringBuilder();

        InputStream fis2 = this.getResources().openRawResource(R.raw.adblockserverlist);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
        if(fis2 != null) {
            try {
                while ((strLine2 = br2.readLine()) != null) {
                    adservers.append(strLine2);
                    adservers.append("\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
