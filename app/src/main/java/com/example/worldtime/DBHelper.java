package com.example.worldtime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PrivateKey;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String DB_NAME ="city.db";
    public static final String TB_NAME ="tb_city";


    public DBHelper(Context context ,String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    public DBHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
      //  String sql_message = "CREATE TABLE "+TB_NAME+"(ID INTE PRIMARY KEY AUTOINCREMENT,CURNAME TEXT,CURRATE TEXT)";
        //Log.i("List","创建成功");
        //db.execSQL(sql_message);
        db.execSQL("CREATE TABLE "+TB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURNAME TEXT,CURRATE TEXT)");
        Log.i("run","日期相等，从数据库中获取数据");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        //TODO Auto-generated method stub
    }


}
