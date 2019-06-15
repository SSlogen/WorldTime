package com.example.worldtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

public class MainActivity extends AppCompatActivity implements Runnable {
    List<String> retList =new ArrayList<String>();
    String TAG="正在运行";
    Handler handler;
    TextView result;
    TextView citys;
    String cty[]=new String[609];
    List<WTItem>rateList=new ArrayList<WTItem>();
    Thread thread = new Thread(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        citys = (TextView) findViewById(R.id.city);
        thread.start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {

                        }
                    }


        };
    }
        public void btn (View btn){
            if (btn.getId() == R.id.search) {

            }
    }
        //获取数据

        //添加一个菜单功能
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.seeing, menu);
            return true;
        }
        //打开下一个画面
        public boolean onOptionsItemSelected (MenuItem item){
            if (item.getItemId() == R.id.menu_set) {
                Intent hello = new Intent(this, WTList.class);
                startActivity(hello);
            }
            return super.onOptionsItemSelected(item);
        }
        @Override
        public void run () {
            Document doc = null;
            String cs="";
            List<WTItem>rateList=new ArrayList<WTItem>();
            try {
                Thread.sleep(3000);
                doc = (Document) Jsoup.connect("http://time.123cha.com/full.html").get();
                Elements ul =  doc.getElementsByTag("a");
                Element div2[]=new Element[609];
                String city[]=new String[609];
                for(int i=48;i<1873;i=i+3){
                    div2[(i-48)/3]=ul.get(i);
                    city[(i-48)/3]=div2[(i-48)/3].text();
                    Log.i(TAG,"run+"+city[(i-48)/3]);
                    cs= city[(i-48)/3].toString();
                    retList.add(cs);
                    rateList.add(new WTItem(cs,cs));
                    }
                    WTManger manger =new WTManger(this);
                    manger.deleteAll();
                    manger.addAll(rateList);
                Log.i(TAG,"添加成功");

                /* int i=0;
                for( Element a :ul){
                Log.i(TAG,"run+"+i+a);
                i++;}*/
               } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage(7);
            msg.obj=retList;
            handler.sendMessage(msg);

        }


    }


