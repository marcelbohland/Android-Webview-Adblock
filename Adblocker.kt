package de.marcel.adblockbrowserbyopensearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.ConnectivityManager
import android.view.View
import android.webkit.*
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity()
{
    private lateinit var webView: WebView
    private lateinit var adservers: StringBuilder

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readAdServers()

        // WebView Control
        webView = findViewById(R.id.webview)
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = true
        webView.isLongClickable = true
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        webView.webViewClient = MyWebViewClient()
        registerForContextMenu(webView)

        // Set WebSettings for a WebView
        val webSettings = webView.settings
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.loadWithOverviewMode = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.setAppCachePath(this.cacheDir.absolutePath)

        // Load google.de
        webView.loadUrl("https://www.google.de")

        val cm = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val ani = cm.activeNetworkInfo
        if (ani != null && ani.isConnected)
            webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        else
            webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        webSettings.allowFileAccess = true
        webSettings.javaScriptEnabled = true                            // Enable this only if you need JavaScript support!
        webSettings.javaScriptCanOpenWindowsAutomatically = false       // Enable this only if you want pop-ups!
        webSettings.mediaPlaybackRequiresUserGesture = true
    }


    private fun readAdServers()
    {
        adservers = StringBuilder()

        var line: String? = ""
        val inputStream = this.resources.openRawResource(R.raw.adblockserverlist)
        val br = BufferedReader(InputStreamReader(inputStream))

        try
        {
            while (br.readLine().also { line = it } != null)
            {
                adservers.append(line)
                adservers.append("\n")
            }
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
    }

    //Advertise filter with the lists
    inner class MyWebViewClient : WebViewClient()
    {
        override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse?
        {
            val empty = ByteArrayInputStream("".toByteArray())
            val kk5 = adservers.toString()

            if (kk5.contains(":::::" + request.url.host))
                return WebResourceResponse("text/plain", "utf-8", empty)

            return super.shouldInterceptRequest(view, request)
        }
    }
}