package com.example.mohamedabdelaziz.simplewebbrowser;

import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    WebView webView ;
    String url ;
    Button go ;
    ProgressBar progressBar ;
    EditText turk ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView=(WebView)findViewById(R.id.webview) ;
        go=(Button) findViewById(R.id.go) ;
        progressBar=(ProgressBar) findViewById(R.id.progresbar) ;
        turk=(EditText) findViewById(R.id.url) ;
        progressBar.setMax(100);
        progressBar.setVisibility(View.GONE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.loadUrl("https://www.google.com.eg/?gfe_rd=cr&ei=G_jrWKOnBq2s8we2sIf4Aw");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
               if(newProgress<100 && progressBar.getVisibility()== ProgressBar.GONE)
                   progressBar.setVisibility(ProgressBar.VISIBLE);
                if(newProgress>=100)
                    progressBar.setProgress(View.GONE);
            }
        });

        webView.setWebViewClient(new mywebclient());
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("https://www.google.com.eg/#q="+turk.getText().toString());
            }
        });

    }
    class mywebclient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true ;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu) ;
        menu.getItem(0).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(1).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(2).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId() ;
        switch(id) {
            case R.id.back:
            webView.goBack(); break;

            case R.id.forward:
                webView.goForward(); break;

            case R.id.gohome:
                webView.loadUrl("https://www.google.com.eg/"); break;
        }

        return true;
    }
}
