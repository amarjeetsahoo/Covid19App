package com.amarjeetsahoo.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class bot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        WebView centos = (WebView) findViewById(R.id.webview);
        centos.loadUrl("google.com");
        // I used chat-form for this: a plugin for google form
        // 'PASTE THE LINK OF YOUR CHAT-FORM'
        //HAVE TO REMOVE FOR PRIVACY CONCERN
        centos.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = centos.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    private class MyWebViewClient extends WebViewClient {
        @Override

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

    }
}