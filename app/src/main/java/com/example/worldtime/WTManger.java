package com.example.worldtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class WTManger {
    private DBHelper dbHelper;
    private String TBNAME;

    public WTManger(Context context){
        dbHelper=new DBHelper(context);
        TBNAME=DBHelper.TB_NAME;
    }
    public void add(WTItem item){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("curCity",item.getCurCity());
        values.put("curTime",item.getCurTime());
        db.insert(TBNAME,null,values);

    }
    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
          }





    public void addAll(List<WTItem>list){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        for (WTItem item:list){
            ContentValues values =new ContentValues();
            values.put("curname",item.getCurCity());
            values.put("currate",item.getCurTime());
            db.insert(TBNAME,null,values);

        }
        db.close();
    }
    public List<WTItem>listAll(){
        List<WTItem>rateList=null;
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TBNAME,null,null,null,null,null,null);
        if(cursor!=null){
            rateList=new ArrayList<WTItem>();
            while (cursor.moveToNext()){
                WTItem item=new WTItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurCity(cursor.getString(cursor.getColumnIndex("CURCITY")));
                item.setCurTime(cursor.getString(cursor.getColumnIndex("CURTIME")));
                rateList.add(item);
            }
            cursor.close();
        }
        return rateList;
    }




}
