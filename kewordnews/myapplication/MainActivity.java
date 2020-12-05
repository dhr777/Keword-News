package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.myapplication.R.id.listView;


public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> data;
    private ArrayAdapter<String> adapter;
    private EditText et;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText)findViewById(R.id.et);

        data = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        listview = (ListView)findViewById(listView);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(onItemClickListener);
        listview.setOnItemClickListener(onItemListener);



    }

    public void onClick(View v){
        String a=et.getText().toString();
        data.add(a);
        listview.setAdapter(adapter);
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("title",a);


    }

    AdapterView.OnItemClickListener onItemListener=new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);

String a=adapter.getItem(i);
            intent.putExtra("title",""+a);
            startActivity(intent);


        }
    };






    AdapterView.OnItemLongClickListener onItemClickListener = new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            data.remove(i);
            adapter.notifyDataSetChanged();
            return false;
        }
    };
}







