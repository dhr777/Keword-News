package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GetNews extends AppCompatActivity {
TextView tv;
    String j=null;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_news);
        tv=(TextView)findViewById(R.id.tv);
        Intent intent=getIntent();
              a= intent.getStringExtra("title");


     asd();



    }
    public void asd(){

        new Thread(new Runnable() {

            @Override
            public void run() {


                String clientId = "8YbaPUJrw4AlCb7WMLW2";//애플리케이션 클라이언트 아이디값";
                String clientSecret = "a7sRfWF1Ac";//애플리케이션 클라이언트 시크릿값";
                try {

                    String text = URLEncoder.encode(a, "UTF-8") + "&display=10";
                    String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text; // json 결과
                    // String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                    BufferedReader br;

                    br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine + "\n");
                    }
                    br.close();
                    //          System.out.println(response.toString());
                    j = response.toString();
                    j=j.replace("<b>","");
                    j=j.replace("&amp;","");
                    j=j.replace("</b>","");
                } catch (Exception e) {

                }


                tv.post(new Runnable() {

                    @Override
                    public void run() {

                        JSONObject jRootObj=null;
                        JSONArray jarr=null;
                        JSONObject jObj=null;
                        try {
                            jRootObj=new JSONObject(j);
                            tv.setText(""+jRootObj.getString("total"));
jarr=jRootObj.getJSONArray("items");

                            for(int i=0;i<jarr.length();i++) {
                                jObj = jarr.getJSONObject(i);
                                tv.append(jObj.getString("title")+"\n\n");
                                tv.append(jObj.getString("description")+"\n\n");
                                tv.append(jObj.getString("pubDate")+"\n\n\n\n");


                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


               //         tv.setText(j);
                    }


                });
            }

        }).start();
/*

        tv.post(new Runnable() {

            @Override
            public void run() {
                tv.setText(j);
            }


        });
*/



    }
}
