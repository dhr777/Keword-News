package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class Summary extends AppCompatActivity {
TextView tv1;
    TextView tv2;
    TextView tv3;
    WebView webView;
    String d=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
tv1=(TextView)findViewById(R.id.title);
        tv2=(TextView)findViewById(R.id.description);
        tv3=(TextView)findViewById(R.id.putDate);


        Intent intent=getIntent();
        String a = intent.getStringExtra("title");
        String b = intent.getStringExtra("description");
        String c = intent.getStringExtra("pubDate");
       d = intent.getStringExtra("link");

        tv1.setText(a);
        tv2.setText(b);
        tv3.setText(c);

        webView = (WebView) findViewById(R.id.web);

    }
public void onClick(View v){
    webView.loadUrl(d);

}

}
