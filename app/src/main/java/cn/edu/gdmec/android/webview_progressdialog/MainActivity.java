package cn.edu.gdmec.android.webview_progressdialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.service.autofill.FillEventHistory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private AlertDialog alertDialog;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWebView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (progressBar.isShowing()){
                progressBar.dismiss();
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(MainActivity.this,"网页加载出错",Toast.LENGTH_SHORT).show();
            alertDialog.setTitle("ERROR");
            alertDialog.setMessage(description);
            alertDialog.setButton(0, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }
    }

    private void initWebView() {
        progressBar = ProgressDialog.show(MainActivity.this,null,"正在进入网页，稍等。。。");
        webView = (WebView)this.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com");
      //  webView.loadUrl("file:///android_asset/phonebook.html");
        alertDialog = new AlertDialog.Builder(this).create();
        webView.setWebViewClient(new MyWebViewClient());
    }
}
