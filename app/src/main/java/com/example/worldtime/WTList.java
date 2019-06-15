package com.example.worldtime;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WTList extends ListActivity implements Runnable {
    private final String TAG = "Rate";
    String data[] = {"one", "two", "three"};
    Handler handler;
    private String logDate = "";
    private final String DATE_SP_KEY = "lastRateDateStr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_rate_list);
        SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
        logDate = sp.getString(DATE_SP_KEY, "");
        Log.i("List","lastRateDateStr=" + logDate);



        final List<String> list1 =new ArrayList<String>();
        for(int i=1;i<609;i++){
            list1.add("item"+i);
        }
        ListAdapter adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
        setListAdapter(adapter);

        Thread thread =new Thread(this);
        thread.start();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==7){
                    List<String> list2 =(List<String>)msg.obj;
                    ListAdapter adapter =new ArrayAdapter<String>(WTList.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
    }
    public void run () {
        List<String> retList =new ArrayList<String>();
        Document doc = null;
        String cs="";
        try {
            Thread.sleep(0);
            doc = (Document) Jsoup.connect("http://time.123cha.com/full.html").get();
            Elements ul =  doc.getElementsByTag("a");
            Element div2[]=new Element[609];
            String city[]=new String[609];
            for(int i=48;i<1873;i=i+3){
                div2[(i-48)/3]=ul.get(i);
                city[(i-48)/3]=div2[(i-48)/3].text();
                cs= city[(i-48)/3].toString();
                Log.i(TAG,"run+"+city[(i-48)/3]);
                retList.add(cs);
            }

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
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.change, menu);
        return true;
    }
    //打开下一个画面
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId() == R.id.menu_set2) {
            Intent hello = new Intent(this, MainActivity.class);
            startActivity(hello);
        }
        return super.onOptionsItemSelected(item);
    }




}
