package com.example.myapplication

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val name = intent.getStringExtra("name")
        val url = intent.getStringExtra("url")
        title=name
        findViewById<WebView>(R.id.webview).apply {
            settings.javaScriptEnabled = true
            loadUrl(url);
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    loadUrl(url)
                    return false
                }
            }
        }
    }
}


