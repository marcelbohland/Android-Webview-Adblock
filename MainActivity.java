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
        advertise();
        advertise2();
        hosts();
        AdguardDNS();
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
          String kk2= String.valueOf(wwwdf);
          if (kk2.contains(":::::"+request.getUrl().getHost()))
          {
              return new WebResourceResponse("text/plain", "utf-8", EMPTY);
          }
          String kk= String.valueOf(wwwdf2);
	if (kk.contains(":::::"+request.getUrl().getHost()))
	{
		return new WebResourceResponse("text/plain", "utf-8", EMPTY);
	}
	String kk3= String.valueOf(hosts);
	if (kk3.contains(":::::"+request.getUrl().getHost()))
	{
	    return new WebResourceResponse("text/plain", "utf-8", EMPTY);
	}
	String kk4= String.valueOf(AdguardDNS);
	if (kk4.contains(":::::"+request.getUrl().getHost()))
	{
	    return new WebResourceResponse("text/plain", "utf-8", EMPTY);
	}
	String kk5= String.valueOf(adservers);
	if (kk5.contains(request.getUrl().getHost()+":::::"))
	{
	    return new WebResourceResponse("text/plain", "utf-8", EMPTY);
	}
          return super.shouldInterceptRequest(view, request);
      }
    }






//Advertise filter list Loading
    private void advertise(){
        String strLine2="";
        wwwdf2 = new StringBuilder();

        InputStream fis2 = this.getResources().openRawResource(R.raw.dgads);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
        if(fis2 != null) {
            try {
                while ((strLine2 = br2.readLine()) != null) {
                    wwwdf2.append(strLine2);
                    wwwdf2.append("\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    private void advertise2(){
        String strLine2="";
        wwwdf = new StringBuilder();

        InputStream fis2 = this.getResources().openRawResource(R.raw.adlist1);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
        if(fis2 != null) {
            try {
                while ((strLine2 = br2.readLine()) != null) {
                    wwwdf.append(strLine2);
                    wwwdf.append("\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void hosts(){
        String strLine2="";
        hosts = new StringBuilder();

        InputStream fis2 = this.getResources().openRawResource(R.raw.adserver3);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
        if(fis2 != null) {
            try {
                while ((strLine2 = br2.readLine()) != null) {
                    hosts.append(strLine2);
                    hosts.append("\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void AdguardDNS(){
        String strLine2="";
        AdguardDNS = new StringBuilder();

        InputStream fis2 = this.getResources().openRawResource(R.raw.adguarddns);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
        if(fis2 != null) {
            try {
                while ((strLine2 = br2.readLine()) != null) {
                    AdguardDNS.append(strLine2);
                    AdguardDNS.append("\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void adservers(){
        String strLine2="";
        adservers = new StringBuilder();

        InputStream fis2 = this.getResources().openRawResource(R.raw.adservers);
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
