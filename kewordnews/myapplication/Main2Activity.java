package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.myapplication.R.id.listView2;

public class Main2Activity extends AppCompatActivity {
    private ListView listview2;
    private ArrayList<String> data;
    private ArrayAdapter<String> adapter;
    String[] s1=new String[100];
    String[] s2=new String[100];
    String[] s3=new String[100];
    String[] s4=new String[100];
private TextView tv;

String j=null;
    String a=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();
        a= intent.getStringExtra("title");
tv=(TextView)findViewById(R.id.tv3);


        data = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        listview2 = (ListView)findViewById(listView2);
        listview2.setAdapter(adapter);
asd();





       listview2.setOnItemClickListener(onItemListener);



    }

    AdapterView.OnItemClickListener onItemListener=new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(Main2Activity.this, Summary.class);

//            String a=adapter.getItem(i);
  //          intent.putExtra("title",""+a);


            intent.putExtra("title",s1[i]);
            intent.putExtra("description",s2[i]);
            intent.putExtra("pubDate",s3[i]);
            intent.putExtra("link",s4[i]);

            startActivity(intent);


        }
    };





void asd() {
    new Thread(new Runnable() {

        @Override
        public void run() {


            String clientId = "8YbaPUJrw4AlCb7WMLW2";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "a7sRfWF1Ac";//애플리케이션 클라이언트 시크릿값";
            try {

                String text = URLEncoder.encode(a, "UTF-8") + "&display=15";
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

                j = j.replace("<b>", "");  // 잡문자없애기
                j = j.replace("&amp;", "");// 잡문자없애기
                j = j.replace("</b>", "");// 잡문자없애기
                j = j.replace("&quot;", "");// 잡문자없애기
                j = j.replace("&gt;", "");// 잡문자없애기
                j = j.replace("&lt;", "");// 잡문자없애기
            } catch (Exception e) {}


listview2.post(new Runnable() {
    @Override
    public void run() {


        JSONObject jRootObj=null;
        JSONArray jarr=null;
        JSONObject jObj=null;
        try {
            jRootObj=new JSONObject(j);
tv.setText("업데이트 : "+jRootObj.getString("lastBuildDate"));
            jarr=jRootObj.getJSONArray("items");

            for(int i=0;i<jarr.length();i++) {
                jObj = jarr.getJSONObject(i);
                s1[i]=jObj.getString("title");
                s2[i]=jObj.getString("description");
                s3[i]=jObj.getString("pubDate");
                s4[i]=jObj.getString("link");
                data.add(jObj.getString("title"));
                listview2.setAdapter(adapter);

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
});

        }

    }).start();

}
}
